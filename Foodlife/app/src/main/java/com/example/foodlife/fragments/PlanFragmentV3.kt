package com.example.foodlife.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionHomeAdapterV2
import com.example.foodlife.databinding.FragmentPlanV3Binding
import com.example.foodlife.dialog.CalendarPopUp
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.view_models.PlanViewModel
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class PlanFragmentV3 : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    private var _binding: FragmentPlanV3Binding? = null
    private val binding get() = _binding!!

    private var adapterBreakfast: CollectionHomeAdapterV2? = null
    private var adapterLunch: CollectionHomeAdapterV2? = null
    private var adapterDinner: CollectionHomeAdapterV2? = null
    private var adapterSnack: CollectionHomeAdapterV2? = null
    private var viewMode = true //to show or hide deleteMode
    private var weekMode = 1 //0 = last week, 1 = this week, 2 = next week

    private lateinit var planViewModel: PlanViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanV3Binding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textPlan
//        planViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        planViewModel = ViewModelProvider(store)[PlanViewModel::class.java]


        //Initialize view
        if (arguments != null) {
            /**
             * Fragment started from addToPlan
             */
            val newDish = PlanItemModel(
                arguments!!.getString("Title", "Some random dish"),
                arguments!!.getString("Rate", "4.5"),
                arguments!!.getInt("Image", R.drawable.recommend_1),
                arguments!!.getString("Time", "15 mins"),
                arguments!!.getString("Level", "Hard"),
                arguments!!.getString("Author", "NKTTNga")
            )
            if (arguments!!.getBoolean("Breakfast", false)) {
                planViewModel.breakfastList.add(newDish)
            }
            if (arguments!!.getBoolean("Lunch", false)) {
                planViewModel.lunchList.add(newDish)
            }
            if (arguments!!.getBoolean("Dinner", false)) {
                planViewModel.dinnerList.add(newDish)
            }
            if (arguments!!.getBoolean("Snack", false)) {
                planViewModel.snackList.add(newDish)
            }
            planViewModel.ingredientList.add(Ingredient("added ingredients", "8"))
            planViewModel.ingredientList2.add(Ingredient("added ingredients", "16"))
            arguments = null
        }
        initListener()
        initAdaptersText()
        initTab()

    }

    data class DayOfWeek(val day: String, val date: String)
    private fun initTab(){
        val tabList = mapOf<Int,DayOfWeek>(
            0 to DayOfWeek("Monday", "11"),
            1 to DayOfWeek("Tuesday", "12"),
            2 to DayOfWeek("Wednesday", "13"),
            3 to DayOfWeek("Thursday", "14"),
            4 to DayOfWeek("Friday", "15"),
            5 to DayOfWeek("Saturday", "16"),
            6 to DayOfWeek("Sunday", "17")
        )


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val day = tabList[tab.position]!!.day
                val date = tabList[tab.position]!!.date
                binding.tvDate.text = day
                when(date){
                    "11" -> binding.tvDateInfo.text = "${date}st June, 2022"
                    "12" -> binding.tvDateInfo.text = "${date}nd June, 2022"
                    "13" -> binding.tvDateInfo.text = "${date}rd June, 2022"
                    else -> binding.tvDateInfo.text = "${date}th June, 2022"
                }
                planViewModel.morning.shuffle()
                adapterBreakfast?.updateData(planViewModel.morning)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    private fun initListener() {
//        binding.ivCalender.setOnClickListener(this)
        binding.ivShopping.setOnClickListener(this)
        binding.ivViewMode.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.ivNext.setOnClickListener(this)
    }

    private fun initAdaptersText() {
        //Create adapter
        if (adapterBreakfast == null) {
            adapterBreakfast = CollectionHomeAdapterV2(
                { itemClicked ->
                    val bundle = Bundle()
                    bundle.putString("Title", itemClicked.title)
                    bundle.putString("Description", itemClicked.description)
                    bundle.putInt("Score", itemClicked.score)
                    bundle.putString("Diff", itemClicked.diff)
                    bundle.putInt("Time", itemClicked.time)
                    bundle.putString("ProfileName", itemClicked.profile_name)
                    bundle.putInt("ProfileImg", itemClicked.profile_img)
                    bundle.putInt("Picture", itemClicked.img)
                    navController.navigate(R.id.planToDetail, bundle)
                },
                { clickedItem ->
                    val updateList = planViewModel.morning
                    if (updateList.isNotEmpty()) {
                        updateList.remove(clickedItem)
                        planViewModel.morning = updateList
                        adapterBreakfast?.updateData(updateList)
                    }
                    if (updateList.size == 0) binding.tvBreakfastDescription.visibility =
                        View.VISIBLE
                    val size = planViewModel.morning.size
                    binding.tvBreakfastCv.text = "$size out of 4"
                }
            )
        }
        if (planViewModel.morning.size == 0) binding.tvBreakfastDescription.visibility =
            View.VISIBLE
        var temp = planViewModel.morning.size
        binding.tvBreakfastCv.text = "$temp out of 4"

        if (adapterLunch == null) {
            adapterLunch = CollectionHomeAdapterV2(
                { itemClicked ->
                    val bundle = Bundle()
                    bundle.putString("Title", itemClicked.title)
                    bundle.putString("Description", itemClicked.description)
                    bundle.putInt("Score", itemClicked.score)
                    bundle.putString("Diff", itemClicked.diff)
                    bundle.putInt("Time", itemClicked.time)
                    bundle.putString("ProfileName", itemClicked.profile_name)
                    bundle.putInt("ProfileImg", itemClicked.profile_img)
                    bundle.putInt("Picture", itemClicked.img)
                    navController.navigate(R.id.planToDetail, bundle)
                },
                { clickedItem ->
                    val updateList = planViewModel.lunch
                    if (updateList.isNotEmpty()) {
                        updateList.remove(clickedItem)
                        planViewModel.lunch = updateList
                        adapterLunch?.updateData(updateList)
                    }
                    if (updateList.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE
                    val size = planViewModel.lunch.size
                    binding.tvLunchCv.text = "$size out of 4"
                })
        }
        if (planViewModel.lunch.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE
        temp = planViewModel.lunch.size
        binding.tvLunchCv.text = "$temp out of 4"


        if (adapterDinner == null) {
            adapterDinner = CollectionHomeAdapterV2(
                { itemClicked ->
                    val bundle = Bundle()
                    bundle.putString("Title", itemClicked.title)
                    bundle.putString("Description", itemClicked.description)
                    bundle.putInt("Score", itemClicked.score)
                    bundle.putString("Diff", itemClicked.diff)
                    bundle.putInt("Time", itemClicked.time)
                    bundle.putString("ProfileName", itemClicked.profile_name)
                    bundle.putInt("ProfileImg", itemClicked.profile_img)
                    bundle.putInt("Picture", itemClicked.img)
                    navController.navigate(R.id.planToDetail, bundle)
                },
                { clickedItem ->
                    val updateList = planViewModel.dinner
                    if (updateList.isNotEmpty()) {
                        updateList.remove(clickedItem)
                        planViewModel.dinner = updateList
                        adapterDinner?.updateData(updateList)
                    }
                    if (updateList.size == 0) binding.tvDinnerDescription.visibility =
                        View.VISIBLE
                    val size = planViewModel.dinner.size
                    binding.tvDinnerCv.text = "$size out of 4"
                })
        }
        if (planViewModel.dinner.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
        temp = planViewModel.dinner.size
        binding.tvDinnerCv.text = "$temp out of 4"


        if (adapterSnack == null) {
            adapterSnack = CollectionHomeAdapterV2(
                { itemClicked ->
                    val bundle = Bundle()
                    bundle.putString("Title", itemClicked.title)
                    bundle.putString("Description", itemClicked.description)
                    bundle.putInt("Score", itemClicked.score)
                    bundle.putString("Diff", itemClicked.diff)
                    bundle.putInt("Time", itemClicked.time)
                    bundle.putString("ProfileName", itemClicked.profile_name)
                    bundle.putInt("ProfileImg", itemClicked.profile_img)
                    bundle.putInt("Picture", itemClicked.img)
                    navController.navigate(R.id.planToDetail, bundle)
                },
                { clickedItem ->
                    val updateList = planViewModel.snack
                    if (updateList.isNotEmpty()) {
                        updateList.remove(clickedItem)
                        planViewModel.snack = updateList
                        adapterSnack?.updateData(updateList)
                    }
                    if (updateList.size == 0) binding.tvSnackDescription.visibility =
                        View.VISIBLE
                    val size = planViewModel.snack.size
                    binding.tvSnackCv.text = "$size out of 4"
                })
        }
        if (planViewModel.snack.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE
        temp = planViewModel.snack.size
        binding.tvSnackCv.text = "$temp out of 4"

        //Check if recyclerView is not null
        setAdapterText(adapterBreakfast!!, binding.rvPlanTextBreakfast)
        planViewModel.morning.let { adapterBreakfast!!.updateData(it) }

        setAdapterText(adapterLunch!!, binding.rvPlanTextLunch)
        planViewModel.lunch.let { adapterLunch!!.updateData(it) }

        setAdapterText(adapterDinner!!, binding.rvPlanTextDinner)
        planViewModel.dinner.let { adapterDinner!!.updateData(it) }

        setAdapterText(adapterSnack!!, binding.rvPlanTextSnack)
        planViewModel.snack.let { adapterSnack!!.updateData(it) }
    }

    private fun setAdapterText(_adapter: CollectionHomeAdapterV2, _recyclerView: RecyclerView) {
        //Set adapter
        _recyclerView.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivCalender -> {
                CalendarPopUp().show(childFragmentManager, CalendarPopUp.TAG)
            }
            R.id.ivShopping -> {
                navController.navigate(R.id.planToCalculate)
                //TODO
            }
            R.id.ivViewMode -> {
                adapterBreakfast?.updateDeleteMode(adapterBreakfast?.deleteMode!!.not())
                adapterLunch?.updateDeleteMode(adapterLunch?.deleteMode!!.not())
                adapterDinner?.updateDeleteMode(adapterDinner?.deleteMode!!.not())
                adapterSnack?.updateDeleteMode(adapterSnack?.deleteMode!!.not())
                if (viewMode){ //viewMode = deleteMode
                    binding.icBreakfastTitle.visibility = View.VISIBLE
                    binding.icLunchTitle.visibility = View.VISIBLE
                    binding.icDinnerTitle.visibility = View.VISIBLE
                    binding.icSnackTitle.visibility = View.VISIBLE
                }
                else
                {
                    binding.icBreakfastTitle.visibility = View.GONE
                    binding.icLunchTitle.visibility = View.GONE
                    binding.icDinnerTitle.visibility = View.GONE
                    binding.icSnackTitle.visibility = View.GONE
                }
                viewMode = viewMode.not()
            }

            R.id.ivBack ->{
                if (weekMode == 1) {
                    weekMode = 0
                    binding.tvTimeline.text = "Last week"
                    binding.ivBack.isEnabled = false
                    binding.ivNext.isEnabled = true
                } else { //weekMode ==2
                    weekMode = 1
                    binding.tvTimeline.text = "This week"
                    binding.ivBack.isEnabled = true
                    binding.ivNext.isEnabled = true
                }
            }
            R.id.ivNext ->{
                if (weekMode == 1) {
                    weekMode = 2
                    binding.tvTimeline.text = "Next week"
                    binding.ivBack.isEnabled = true
                    binding.ivNext.isEnabled = false
                } else { //weekMode ==0
                    weekMode = 1
                    binding.tvTimeline.text = "This week"
                    binding.ivBack.isEnabled = true
                    binding.ivNext.isEnabled = true
                }
            }
        }
    }

}
