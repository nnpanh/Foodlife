package com.example.foodlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlife.R
import com.example.foodlife.adapters.PlanTextAdapter
import com.example.foodlife.databinding.FragmentPlanBinding
import com.example.foodlife.models.UserModel
import com.example.foodlife.roomdb.FoodlifeDB
import com.example.foodlife.roomdb.entities.UserEntity
import com.example.foodlife.view_models.PlanViewModel


class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: PlanTextAdapter? = null
    private var adapterLunch: PlanTextAdapter? = null
    private var adapterSnack: PlanTextAdapter? = null

    lateinit var planViewModel: PlanViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        planViewModel = ViewModelProvider(this)[PlanViewModel::class.java]

        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Test if roomDB work
        val testUser = UserModel(0, "PA", R.drawable.catcool, 23, null, null)
        val directUser = UserEntity( "Hehe", R.drawable.catcool, 23, null, null)
        val database = activity?.let { FoodlifeDB.getInstance(it.applicationContext) }
        database?.clearAllTables()
        database?.userDAO()?.insert(directUser)

        //Adapters
        initAdapters()

    }

    private fun initAdapters(){
        //Create adapter
        adapterBreakfast = PlanTextAdapter(0)
        adapterLunch = PlanTextAdapter(1)
        adapterSnack = PlanTextAdapter(3)

        //Check if recyclerView is not null
        if (adapterBreakfast!=null){
            setAdapter(adapterBreakfast!!,binding.rvPlanTextBreakfast)
            //Load data
            planViewModel.loadBreakFast()
            planViewModel.breakfastList.value?.let { adapterBreakfast!!.updateData(it) }

        }
        if (adapterLunch!=null){
            setAdapter(adapterLunch!!,binding.rvPlanTextLunch)
            planViewModel.loadLunch()
            planViewModel.lunchList.value?.let { adapterLunch!!.updateData(it) }

        }
        if (adapterSnack!=null){
            setAdapter(adapterSnack!!,binding.rvPlanTextSnack)
            planViewModel.loadSnack()
            planViewModel.snackList.value?.let { adapterSnack!!.updateData(it) }

        }


    }

    private fun setAdapter(_adapter: PlanTextAdapter, _recyclerView: RecyclerView){
        //Set adapter
        _adapter.setHasStableIds(true)
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}