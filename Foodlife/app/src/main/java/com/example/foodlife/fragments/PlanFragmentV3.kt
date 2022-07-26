package com.example.foodlife.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.foodlife.R
import com.example.foodlife.adapters.CollectionHomeAdapterV2
import com.example.foodlife.databinding.FragmentPlanV3Binding
import com.example.foodlife.dialog.SelectCollectionDialog
import com.example.foodlife.dialog.CalendarPopUp
import com.example.foodlife.dialog.SelectCollectionDialogV2
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Ingredient
import com.example.foodlife.models.PlanItemModel
import com.example.foodlife.models.Recipe
import com.example.foodlife.view_models.PlanViewModel
import com.google.android.material.snackbar.Snackbar
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
    private var cartMode = false
    private var viewMode = true //to show or hide deleteMode
    private var weekMode = 1 //0 = last week, 1 = this week, 2 = next week

    private var currentTab = 0
    private var contextView: View? = null
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

        contextView=view
        //Initialize view
        if (arguments != null) {
            /**
             * Fragment started from addToPlan
             */
            val newDish = Recipe(
            arguments!!.getInt("Picture")?:R.drawable.recommend_1,
                arguments!!.getString("Title")?:"Stir-fried beef with broccoli and Rice",
                arguments!!.getInt("Score"),
                arguments!!.getString("Diff")?:"Medium",
                arguments!!.getInt("Time"),
                arguments!!.getString("Description")?:"",
                arguments!!.getInt("ProfileImg"),
                arguments!!.getString("ProfileName")?:"",
                arguments!!.getString("VideoUrl")?:"",
            )
            if (arguments!!.getBoolean("Breakfast", false)) {
                planViewModel.morning.add(newDish)
            }
            if (arguments!!.getBoolean("Lunch", false)) {
                planViewModel.lunch.add(newDish)
            }
            if (arguments!!.getBoolean("Dinner", false)) {
                planViewModel.dinner.add(newDish)
            }
            if (arguments!!.getBoolean("Snack", false)) {
                planViewModel.snack.add(newDish)
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
                currentTab = tab.position
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
        binding.btnContinue.setOnClickListener(this)
        binding.ivShopping.setOnClickListener(this)
        binding.ivViewMode.setOnClickListener(this)
        binding.ivBack.setOnClickListener(this)
        binding.ivNext.setOnClickListener(this)
        binding.cbMon.isActivated = false
        binding.cbTue.isActivated = false
        binding.cbWed.isActivated = false
        binding.cbThu.isActivated = false
        binding.cbFri.isActivated = false
        binding.cbSat.isActivated = false
        binding.cbSun.isActivated = false
        binding.cbMon.setOnClickListener (this)
        binding.cbTue.setOnClickListener (this)
        binding.cbWed.setOnClickListener (this)
        binding.cbThu.setOnClickListener (this)
        binding.cbFri.setOnClickListener (this)
        binding.cbSat.setOnClickListener (this)
        binding.cbSun.setOnClickListener (this)
       binding.icDinnerTitle.setOnClickListener(this)
        binding.icLunchTitle.setOnClickListener(this)
        binding.icBreakfastTitle.setOnClickListener(this)
        binding.icSnackTitle.setOnClickListener(this)
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
        if (planViewModel.morning.size == 0) binding.tvBreakfastDescription.visibility = View.VISIBLE
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
            R.id.btnContinue -> {
                navController.navigate(R.id.planToCalculate)
            }
            R.id.cbMon,R.id.cbTue,R.id.cbSun,R.id.cbFri,R.id.cbSat,R.id.cbThu,R.id.cbWed -> {
                p0.isActivated = !p0.isActivated
            }
            R.id.ivShopping -> {
                val selectList = listOf(binding.cbMon,binding.cbTue,binding.cbWed,binding.cbThu,binding.cbFri,binding.cbSat,binding.cbSun)
                if (cartMode) { //click to disable
                    binding.ivLinebreak0.visibility = View.GONE
                    binding.btnContinue.visibility = View.GONE
                    binding.groupDay.visibility = View.GONE
                    selectList.forEach {
                        it.isActivated = false
                    }

                } else {
                    binding.ivLinebreak0.visibility = View.VISIBLE
                    binding.btnContinue.visibility = View.VISIBLE
                    binding.groupDay.visibility = View.VISIBLE
                    selectList[currentTab].isActivated = true

                }
                cartMode = cartMode.not()
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
                planViewModel.morning.shuffle()
                adapterBreakfast?.updateData(planViewModel.morning)
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
                planViewModel.morning.shuffle()
                adapterBreakfast?.updateData(planViewModel.morning)
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
            R.id.icBreakfastTitle -> {
                selectCollection(1)
            }

            R.id.icLunchTitle -> {
                selectCollection(2)
            }

            R.id.icDinnerTitle -> {
                selectCollection(3)
            }

            R.id.icSnackTitle -> {
                selectCollection(4)
            }
        }
    }
    private fun selectCollection(type:Int) {
        /*val selectCollection = SelectCollectionDialog()
        selectCollection.show(parentFragmentManager, SelectCollectionDialog.TAG)
        selectCollection.setFragmentResultListener("request_key") { _, bundle ->
            val collectionName = bundle.getString("collection")
            Log.d("collectionName", "$collectionName")
        }*/

        val selectCollection = SelectCollectionDialogV2()
        selectCollection.show(parentFragmentManager, SelectCollectionDialogV2.TAG)
        selectCollection.setFragmentResultListener("request_new") { _, bundle ->
            val result = bundle.getSerializable("newRecipe") as Recipe
                if (type==1) {
                    planViewModel.morning.add(result)
                    adapterBreakfast!!.updateData(planViewModel.morning)
                    if (planViewModel.morning.size == 0) binding.tvBreakfastDescription.visibility = View.VISIBLE
                    else binding.tvBreakfastDescription.visibility = View.GONE
                }
                if (type==2) {
                    planViewModel.lunch.add(result)
                    adapterLunch!!.updateData(planViewModel.lunch)
                    if (planViewModel.lunch.size == 0) binding.tvLunchDescription.visibility = View.VISIBLE
                    else binding.tvLunchDescription.visibility = View.GONE
                }
                if (type==3) {
                    planViewModel.dinner.add(result)
                    adapterDinner!!.updateData(planViewModel.dinner)
                    if (planViewModel.dinner.size == 0) binding.tvDinnerDescription.visibility = View.VISIBLE
                    else binding.tvDinnerDescription.visibility = View.GONE
                }
                if (type==4) {
                    planViewModel.snack.add(result)
                    adapterSnack!!.updateData(planViewModel.snack)
                    if (planViewModel.snack.size == 0) binding.tvSnackDescription.visibility = View.VISIBLE
                    else binding.tvSnackDescription.visibility = View.GONE
                }

                planViewModel.ingredientList.add(Ingredient("added ingredients", "8"))
                planViewModel.ingredientList2.add(Ingredient("added ingredients", "16"))


        }
    }

}
