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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.foodlife.R
import com.example.foodlife.adapters.DetailAdapter
import com.example.foodlife.databinding.FragmentDetailBinding
import com.example.foodlife.dialog.AddToCollectionDialog
import com.example.foodlife.dialog.AddToPlanBottomDialog
import com.example.foodlife.dialog.BottomSheetCollection
import com.example.foodlife.dialog.OptionBottomDialog
import com.example.foodlife.models.Collection
import com.example.foodlife.models.Recipe
import com.example.foodlife.view_models.CollectionViewModel
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

    private lateinit var collectionViewModel: CollectionViewModel

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
        val store = navController.getViewModelStoreOwner(R.id.mobile_navigation)
        collectionViewModel = ViewModelProvider(store)[CollectionViewModel::class.java]
        if (collectionViewModel.colList.value!!.isEmpty())
            collectionViewModel.loadCollection()
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
//        val mediaController = MediaController(videoView.context)
        val mediaController = MediaController(context)
        mediaController.setAnchorView(binding.flVideo)
        videoView.setMediaController(mediaController)




        initListener()
    


        if (arguments!=null){
            val getTitle = arguments?.getString("Title")
            //val getDes = arguments?.getString("Description")
            val getTime = arguments?.getInt("Time")
            val getDiff = arguments?.getString("Diff")
            val getScore = arguments?.getInt("Score")
            val getNumScore = arguments?.getInt("NumScore")
            val getName = arguments?.getString("ProfileName")
            val getProfile = arguments?.getInt("ProfileImg")
            val getVideoUrl = arguments?.getString("VideoUrl")?:"https://vid.tasty.co/output/246029/landscape_480/1657534926"
            binding.recipeTitle.text = getTitle

            binding.detailAvatar.setBackgroundResource(getProfile!!)
            //binding.tvDetailDes.text = getDes
            binding.authorName.text = getName
            //binding.detailRating.rating = getScore!!.toFloat()
            binding.detailNumScore.text=getNumScore.toString()
            binding.tvDetailTime.text = getTime.toString()+" mins"
            binding.tvDetailLevel.text = getDiff!!.toString()
            if (getVideoUrl != null)(
                videoView.setVideoURI(Uri.parse(getVideoUrl)))
            else videoView.setVideoURI(Uri.parse("https://vid.tasty.co/output/246029/landscape_480/1657534926"))
            if (getScore != null) {
                binding.detailRating.rating = getScore!!.toFloat()
            }
        }



        //videoView.requestFocus()

//        videoView.requestFocus()


        binding.nsView.viewTreeObserver.addOnScrollChangedListener {
            mediaController.hide()
        }

        videoView.setOnPreparedListener{
            videoView.requestFocus()
        }
        /**
         * Chỉnh lại chỗ nãy nha
         */
        if (videoView.isPlaying){
            binding.ivPlayButton.visibility = View.GONE
        }

        binding.ivPlayButton.setOnClickListener(){
            if (!videoView.isPlaying) {
                videoView.start()
                // show the media controls
                mediaController.show();
                // hide button once playback starts
                binding.ivPlayButton.visibility = View.GONE
            }
        }

    }

    private fun initListener(){
        binding.ivPlanIcon.setOnClickListener(this)
        binding.ivMarkIcon.setOnClickListener(this)
        binding.imgToolbarBtnFav.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0?.id){
           /* R.id.detail_menu -> {
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
            }*/
            R.id.ivPlanIcon->{
                addToPlan()
            }
            R.id.ivMarkIcon->{
                saveToCollection()
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
            val getPicture = arguments?.getInt("Picture")?:R.drawable.recommend_1
            val getDes = arguments?.getString("Description")
            val getTime = arguments?.getInt("Time")
            val getDiff = arguments?.getString("Diff")
            val getScore = arguments?.getInt("Score")
            val getNumScore = arguments?.getInt("NumScore")
            val getName = arguments?.getString("ProfileName")
            val getProfile = arguments?.getInt("ProfileImg")
            val getVideoUrl = arguments?.getString("VideoUrl")?:""

            bundle.putString("Title", getTitle)
            bundle.putString("Description", getDes)
            bundle.putInt("Score", getScore!!)
            bundle.putInt("NumScore", getNumScore!!)
            bundle.putString("Diff", getDiff)
            bundle.putInt("Time", getTime!!)
            bundle.putString("ProfileName", getName)
            bundle.putInt("ProfileImg", getProfile!!)
            bundle.putInt("Picture", getPicture)
            bundle.putString("VideoUrl", getVideoUrl)
            navController.navigate(R.id.returnPlan,bundle)
        }
    }

    private fun saveToCollection() {
        val addToCollectionBottomDialog = AddToCollectionDialog()
        addToCollectionBottomDialog.show(parentFragmentManager, AddToCollectionDialog.TAG)
        addToCollectionBottomDialog.setFragmentResultListener("request_key") { _, bundle ->
            val addNewCollection = bundle.getBoolean("add",false)
            if (addNewCollection){
                /*navController.navigate(R.id.detailToCollection,Bundle().apply {
                    putBoolean("add", true)
                    val getTitle = arguments?.getString("Title")
                    val getDiff = arguments?.getString("Diff")
                    val getPicture = arguments?.getInt("Picture")
                    val getScore = arguments?.getInt("Score")
                    val getTime = arguments?.getInt("Time")
                    putString("Title", getTitle)
                    putInt("Time", getTime!!)
                    putString("Diff", getDiff)
                    putInt("Score", getScore!!)
                    putInt("Picture", getPicture!!)
                })*/

                val bottomSheetCollection = BottomSheetCollection()
                bottomSheetCollection.show(requireActivity().supportFragmentManager, "addBottomSheet")
                bottomSheetCollection.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.FilterBottomSheetDialogTheme)
                bottomSheetCollection.setFragmentResultListener("request_key") { requestKey, bundle ->
                    val result = bundle.getSerializable("newCollection") as Collection
                    result.quantity = 0
                    collectionViewModel.addCollection(result)

                    val newList = collectionViewModel.colList.value
                    val getTitle = arguments?.getString("Title")
                    val getDiff = arguments?.getString("Diff")
                    val getPicture = arguments!!.getInt("Picture")
                    val getScore = arguments!!.getInt("Score")
                    val getNumScore = arguments!!.getInt("NumScore")
                    val getTime = arguments!!.getInt("Time")
                    val getDes = arguments?.getString("Description")
                    val getName = arguments?.getString("ProfileName")
                    val getProfile = arguments!!.getInt("ProfileImg")
                    val getVideoUrl = arguments?.getString("VideoUrl")?:""
                    val recipe = Recipe(getPicture, getTitle!!, getScore,getNumScore, getDiff!!, getTime,getDes!!,getProfile,getName!!,getVideoUrl!!)
                    newList!!.forEachIndexed { index, collection ->
                        if (result.title==collection.title) {
                            collectionViewModel.addRecipe(index, recipe)
                        }
                    }
                    navController.navigate(R.id.detailToCollection,Bundle().apply {
                        putBoolean("add", true)})
                    Snackbar.make(contextView!!, "Saved successfully", Snackbar.LENGTH_SHORT).show()
            }}
            else{
                navController.navigate(R.id.detailToCollection,Bundle().apply {
                    val getTitle = arguments?.getString("Title")
                    val getDiff = arguments?.getString("Diff")
                    val getPicture = arguments?.getInt("Picture")
                    val getScore = arguments?.getInt("Score")
                    val getNumScore = arguments?.getInt("NumScore")
                    val getTime = arguments?.getInt("Time")
                    val getDes = arguments?.getString("Description")
                    val getName = arguments?.getString("ProfileName")
                    val getProfile = arguments?.getInt("ProfileImg")
                    val getVideoUrl = arguments?.getString("VideoUrl")?:""
                    putString("Title", getTitle)
                    putInt("Time", getTime!!)
                    putString("Diff", getDiff)
                    putInt("Score", getScore!!)
                    putInt("NumScore", getNumScore!!)
                    putInt("Picture", getPicture!!)
                    putString("Description", getDes)
                    putString("ProfileName", getName)
                    putInt("ProfileImg", getProfile!!)
                    putString("VideoUrl", getVideoUrl)
                    putBundle("Bundle", bundle)
                })
                Snackbar.make(contextView!!, "Saved successfully", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
