package com.example.foodlife.fragments

import android.content.Intent.getIntent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.MediaController
import android.widget.ScrollView
import android.widget.VideoView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.adapters.DetailAdapter
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.dialog.AddToCollectionDialog
import com.example.foodlife.dialog.AddToPlanBottomDialog
import com.example.foodlife.dialog.OptionBottomDialog
import com.example.foodlife.models.Recipe
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator


class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController
    private var _binding: FragmentDetailBinding? = null
    private var getRecipe: Recipe? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var isSpinnerInitial = true
    private var contextView: View? = null

    private var bottomDialog = OptionBottomDialog()

    var tabTitle = arrayOf("Ingredients", "Directions", "Review")

    companion object {
        fun newInstance(): DetailFragment {
            return DetailFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        contextView = view

        //Viewpager & TabLayout
        val pager = binding.viewPager2
        val tl = binding.tabLayout
        pager.adapter = DetailAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(tl,pager){
            tab, position ->
                tab.text = tabTitle[position]
        }.attach()

        //VideoView
        val videoView: VideoView = binding.videoView
        val mediaController = MediaController(videoView.context)
        mediaController.setAnchorView(binding.flVideo)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(Uri.parse("https://vid.tasty.co/output/246029/landscape_480/1657534926"))
        videoView.requestFocus()
        videoView.start()

        binding.nsView.viewTreeObserver.addOnScrollChangedListener {
            mediaController.hide()
        }


        initListener()

        if (arguments!=null){
            val getTitle = arguments?.getString("Title")
            val getDes = arguments?.getString("Description")
            val getTime = arguments?.getInt("Time")
            val getDiff = arguments?.getString("Diff")
            val getScore = arguments?.getInt("Score")
            val getName = arguments?.getString("ProfileName")
            val getProfile = arguments?.getInt("ProfileImg")
            binding.recipeTitle.text = getTitle

            binding.detailAvatar.setBackgroundResource(getProfile!!)
            binding.tvDetailDes.text = getDes
            binding.authorName.text = getName
            binding.currentRatingNum.text = getScore.toString()
            binding.tvDetailTime.text = getTime.toString()+" mins"
            binding.tvDetailLevel.text = getDiff

        }



    }

    private fun initListener(){
        binding.detailMenu.setOnClickListener(this)
        binding.imgToolbarBtnFav.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
            R.id.detail_menu -> {
//                DetailMoreMenu().show(childFragmentManager, DetailMoreMenu.TAG)
                bottomDialog.show(parentFragmentManager, OptionBottomDialog.TAG)
                bottomDialog.setFragmentResultListener("key") { _ ,bundle ->
                    bundle.getString("Action")?.let {
                        when(it){
                            "Save to collection" -> {
                                saveToCollection()
                            }
                            "Share to other platforms" -> {

                            }
                            "Add to my plan" -> {
                                addToPlan()

                            }
                        }
                    }
                }
            }
            R.id.imgToolbarBtnFav -> {
                navController.navigateUp()
            }
        }
    }
    private fun addToPlan(){
        val addToPlanBottomDialog = AddToPlanBottomDialog()
        addToPlanBottomDialog.show(parentFragmentManager, AddToPlanBottomDialog.TAG)
        addToPlanBottomDialog.setFragmentResultListener("result"){ _,bundle ->
            /**
             * Put current dish info in this bundle
             */

            val getTitle = arguments?.getString("Title")?:"Stir-fried beef with broccoli and Rice"
            val getDiff = arguments?.getString("Diff")?:"Medium"
            val getName = arguments?.getString("ProfileName")?:"John Doe"

            bundle.putString("Title",getTitle)
            bundle.putString("Time","35 mins")
            bundle.putString("Level",getDiff)
            bundle.putString("Author",getName)
            bundle.putString("Rate","4.5")
            bundle.putInt("Image",R.drawable.recommend_1)
            navController.navigate(R.id.returnPlan,bundle)
        }
    }

    private fun saveToCollection() {
        val addToCollectionBottomDialog = AddToCollectionDialog()
        addToCollectionBottomDialog.show(parentFragmentManager, AddToCollectionDialog.TAG)
        addToCollectionBottomDialog.setFragmentResultListener("request_key") { _, bundle ->
            val addNewCollection = bundle.getBoolean("add",false)
            if (addNewCollection){
                navController.navigate(R.id.returnCollection,Bundle().apply {
                    putBoolean("add",true)
                })}
                else{
                    navController.navigate(R.id.returnCollection,bundle)
                Snackbar.make(contextView!!, "Saved successfully", Snackbar.LENGTH_LONG)
                    .show()
                }


        }
    }
}
