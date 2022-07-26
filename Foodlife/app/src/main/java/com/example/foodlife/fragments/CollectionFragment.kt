package com.example.foodlife.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodlife.CollectionDetail
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionAdapter
import com.example.foodlife.databinding.FragmentCollectionBinding
import com.example.foodlife.dialog.BottomSheetCollection
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Recipe
import com.example.foodlife.view_models.CollectionViewModel
import com.example.foodlife.view_models.HomeViewModel

class CollectionFragment : Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private lateinit var navController: NavController

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isSpinnerInitial = true
    private lateinit var collectionViewModel: CollectionViewModel
    private var addNewQuantity = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val collectionViewModel =
            ViewModelProvider(this)[CollectionViewModel::class.java]

        _binding = FragmentCollectionBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * Inflate view and set controller
         */
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        collectionViewModel = ViewModelProvider(store)[CollectionViewModel::class.java]
        if (collectionViewModel.colList.value!!.isEmpty())
            collectionViewModel.loadCollection()

        /**
         * Dropdown for filter
         */
        val arraySpinner = arrayOf("Alphabetical", "Latest")
        val filterAdapter = ArrayAdapter(requireActivity(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
        binding.SPCol.adapter = filterAdapter

        /**
         * Collection adapter and layout
         */
        val collectionArray = collectionViewModel.colList.value!!
        val collectionAdapter = CollectionAdapter(collectionArray)
        binding.RVCol.adapter = collectionAdapter
        binding.RVCol.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        /**
         * Edit-text search listener for filter
         */
        binding.ACTVColSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            @SuppressLint("ClickableViewAccessibility")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0!!.isEmpty()) {
                    binding.ACTVColSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.search, 0, 0, 0)
                }
                else {
                    binding.ACTVColSearch.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_close, 0)
                    binding.ACTVColSearch.setPadding(50, 0, 50, 0)
                    binding.ACTVColSearch.setOnTouchListener(View.OnTouchListener {_, event ->
                        if (event.action == MotionEvent.ACTION_UP) {
                            if (event.rawX >= (binding.ACTVColSearch.right - binding.ACTVColSearch.compoundPaddingRight)) {
                                binding.ACTVColSearch.setText("")
                                return@OnTouchListener true
                            }
                        }
                        return@OnTouchListener false
                    })
                }
                collectionAdapter.filter.filter(p0)
//                if (collectionAdapter.itemCount == 0)
//                    binding.TVColNotification.visibility = View.VISIBLE
//                else
//                    binding.TVColNotification.visibility = View.GONE
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        /**
         * Add button and pop up dialog
         */
        binding.IVColAdd.setOnClickListener {
            val bottomSheetCollection = BottomSheetCollection()
            bottomSheetCollection.show(requireActivity().supportFragmentManager, "addBottomSheet")
            bottomSheetCollection.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FilterBottomSheetDialogTheme)
            bottomSheetCollection.setFragmentResultListener("request_key") { requestKey, bundle ->
                val result = bundle.getSerializable("newCollection") as Collection
                result.quantity = addNewQuantity
                collectionViewModel.addCollection(result)
                collectionAdapter.notifyDataSetChanged()
            }
        }

        /**
         * Implement listener for collection item
         */
        binding.SPCol.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(isSpinnerInitial)
                    isSpinnerInitial = false
                else {
                    if (p2 != 0) {
                        collectionArray.shuffle()
                        binding.RVCol.adapter = collectionAdapter
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        /**
         * Pass listener to adapter
         */
        collectionAdapter.onItemClick = {collection ->
            /*
            val intent = Intent(requireActivity(), CollectionDetail::class.java)
            intent.putExtra("Collection", collection)
            startActivity(intent)
            */
            navController.navigate(R.id.action_navigation_collections_to_collectionDetail, bundleOf("Collection" to collection))
        }

        if (arguments != null){
            //addNewQuantity = 0
            if (arguments!!.getBoolean("add",false)) {
                //addNewQuantity = 1
                binding.IVColAdd.callOnClick()
            } else
            {
                val getTitle = arguments!!.getString("Title")
                val getDiff = arguments!!.getString("Diff")
                val getPicture = arguments!!.getInt("Picture")
                val getScore = arguments!!.getInt("Score")
                val getTime = arguments!!.getInt("Time")
                val recipe = Recipe(getPicture, getTitle!!, getScore, getDiff!!, getTime,"",0,"","")
                val bundle = arguments?.getBundle("Bundle")
                val newList = collectionViewModel.colList.value
                newList!!.forEachIndexed { index, collection ->
                    val selectedCollection = bundle!!.getBoolean(collection.title,false)
                    if (selectedCollection) {
                        collectionViewModel.addRecipe(index, recipe)
                    }
                }
                //newList = collectionViewModel.colList.value
                //collectionAdapter.updateData(newList!!)
            }
            navController.navigateUp()
            arguments!!.clear()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}