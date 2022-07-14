package com.example.foodlife.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.MainActivity
import com.example.foodlife.R
import com.example.foodlife.adapters.*
import com.example.foodlife.databinding.FragmentSearchBinding
import com.example.foodlife.dialog.FilterSearchPopUp
import com.example.foodlife.view_models.HomeViewModel


class SearchFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private var adapterSearch: SearchRecipeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        homeViewModel = ViewModelProvider(store)[HomeViewModel::class.java]

        if (homeViewModel.searchList.value!!.isEmpty())
            homeViewModel.loadSearchList()
        if (homeViewModel.meatList.value!!.isEmpty())
            homeViewModel.loadMeatList()
        if (homeViewModel.vegetableList.value!!.isEmpty())
            homeViewModel.loadVegetableList()
        if (homeViewModel.vietnamList.value!!.isEmpty())
            homeViewModel.loadVietNamList()
        if (homeViewModel.westernList.value!!.isEmpty())
            homeViewModel.loadWesternList()
        if (homeViewModel.dessertList.value!!.isEmpty())
            homeViewModel.loadDessertList()

        initAdapters()
        initListener()
    }

    private fun initListener() {
        binding.ivSearchBack.setOnClickListener(this)
        binding.ivSearchFilter.setOnClickListener(this)
        val search = binding.ACTVSearchPage
        search.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapterSearch!!.filter.filter(p0)
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun initAdapters(){
        if (homeViewModel.searchList.value!!.isEmpty())
            homeViewModel.loadSearchList()
        val arraySearch = homeViewModel.searchList.value
        if (adapterSearch == null) {
            adapterSearch=SearchRecipeAdapter(arraySearch!!){ itemClicked ->
                val bundle = Bundle()
                bundle.putString("Title", itemClicked.title)
                bundle.putString("Description", itemClicked.description)
                bundle.putInt("Score", itemClicked.score)
                bundle.putString("Diff", itemClicked.diff)
                bundle.putInt("Time", itemClicked.time)
                bundle.putString("ProfileName", itemClicked.profile_name)
                bundle.putInt("ProfileImg", itemClicked.profile_img)
                bundle.putInt("Picture", itemClicked.img)
                (activity as MainActivity).hideKeyboard()

                navController.navigate(R.id.goToDetail,bundle)

            }
        }
        //Check if recyclerView is not null
        setAdapterSearch(adapterSearch!!, binding.rvSearchRecipe)
        homeViewModel.searchList.let { adapterSearch!!.updateData(it.value!!.toList()) }
    }

    private fun setAdapterSearch(_adapter: SearchRecipeAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivSearchBack -> {
                navController.navigateUp()
            }
            R.id.ivSearchFilter->{
                val bottomFilter = FilterSearchPopUp()
                bottomFilter.show(requireActivity().supportFragmentManager, "addBottomSheet")
                bottomFilter.setStyle(DialogFragment.STYLE_NORMAL, R.style.FilterBottomSheetDialogTheme)
                bottomFilter.setFragmentResultListener("request_filter") { requestKey, bundle ->
                    val catCondition = bundle.getSerializable("filterResult") as MutableList<String>
                        homeViewModel.loadSearchList()
                        homeViewModel.FilterSearch(catCondition)
                        val arrayFilter=homeViewModel.resultFilterList.value
                        homeViewModel.searchList.value=arrayFilter

                        initAdapters()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}