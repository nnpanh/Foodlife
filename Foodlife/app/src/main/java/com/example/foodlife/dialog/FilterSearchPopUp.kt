package com.example.foodlife.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.foodlife.databinding.DialogFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.core.view.children
import com.google.android.material.chip.Chip

class FilterSearchPopUp:  BottomSheetDialogFragment() {
    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var timeCondition =""
        var rateCondition =""
        val catCondition: MutableList<String> = mutableListOf()

        binding.flowTime.children.forEach {
            (it as Chip).setOnCheckedChangeListener { buttonView, isChecked ->
                handleClearButton()
                    if (isChecked){
                        timeCondition= buttonView.text.toString()
                    }
                    else{
                        timeCondition=""
                    }
            }
        }

        binding.flowRate.children.forEach {
            (it as Chip).setOnCheckedChangeListener { buttonView, isChecked ->
                handleClearButton()
                if (isChecked){
                    rateCondition += buttonView.text.toString()
                }
                else{
                    rateCondition=rateCondition.replace(buttonView.text.toString(),"")
                }
            }
        }
        binding.flowCategory.children.forEach {
            (it as Chip).setOnCheckedChangeListener { buttonView, isChecked ->
                handleClearButton()
                if (isChecked){
                   catCondition.add(buttonView.text.toString())
                }
                else{
                    catCondition.remove(buttonView.text.toString())
                }
            }
        }
        binding.tvFilterClear.setOnClickListener(){
            binding.flowTime.clearCheck()
            binding.flowRate.clearCheck()
            binding.flowCategory.clearCheck()
            timeCondition=""
            rateCondition=""
            catCondition.clear()
            binding.tvFilterClear.visibility=View.GONE
        }

        binding.tvFilterButton.setOnClickListener(){

            if(timeCondition=="") {
                timeCondition = "false"
            }

            if(rateCondition=="") {
                rateCondition = "false"
            }

            catCondition.add(0,timeCondition)
            catCondition.add(1,rateCondition)

            //binding.test.text=catCondition.toString()

            setFragmentResult("request_filter", bundleOf("filterResult" to catCondition))
            dismiss()
        }

    }
    private fun handleClearButton(){
        if (binding.flowTime.checkedChipIds.size==0 &&
            binding.flowRate.checkedChipIds.size==0 &&
            binding.flowCategory.checkedChipIds.size==0)
        {
            binding.tvFilterClear.visibility=View.GONE
        }
        else{
            binding.tvFilterClear.visibility=View.VISIBLE
        }
    }
}