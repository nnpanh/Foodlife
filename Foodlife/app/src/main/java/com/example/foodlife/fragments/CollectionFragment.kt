package com.example.foodlife.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionAdapter
import com.example.foodlife.databinding.FragmentDashboardBinding
import com.example.foodlife.dialog.BottomSheetCollection
import com.example.foodlife.models.Collection
import com.example.foodlife.view_models.CollectionViewModel

class CollectionFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val collectionViewModel =
            ViewModelProvider(this)[CollectionViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arraySpinner = arrayOf("Alphabetical", "Latest")
        val filterAdapter = ArrayAdapter(requireActivity(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item, arraySpinner)
        val SPFilter = binding.SPCol
        SPFilter.adapter = filterAdapter
        val RVCol = binding.RVCol
        val collectionAdapter = CollectionAdapter(arrayListOf(Collection("", "Meat Lover", 1), Collection("", "Healthy", 4),Collection("", "Diet", 4),Collection("", "Protein Food", 0)))
        RVCol.adapter = collectionAdapter
        RVCol.layoutManager = LinearLayoutManager(requireActivity())

        val ACTVSearch = binding.ACTVColSearch
        ACTVSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                collectionAdapter.filter.filter(p0)
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
        val IVAdd = binding.IVColAdd
        IVAdd.setOnClickListener {
            val bottomSheetCollection = BottomSheetCollection()
            bottomSheetCollection.show(requireActivity().supportFragmentManager, "addBottomSheet")
            bottomSheetCollection.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppBottomSheetDialogTheme)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}