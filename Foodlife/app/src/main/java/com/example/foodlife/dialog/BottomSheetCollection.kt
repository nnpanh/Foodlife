package com.example.foodlife.dialog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.foodlife.R
import com.example.foodlife.models.Collection
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BottomSheetCollection : BottomSheetDialogFragment() {
    private val GALLERY_REQ_CODE = 1000
    private var IVImage: ImageView? = null
    private var imgPath: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.bottom_sheet_collection, container, false)
        val IVClose = view.findViewById<ImageView>(R.id.IVColClose)
        IVClose.setOnClickListener {
            dismiss()
        }
        val FABImage = view.findViewById<FloatingActionButton>(R.id.FABColA)
        FABImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_REQ_CODE)
        }
        val ETName = view.findViewById<EditText>(R.id.ETColName)
        IVImage = view.findViewById(R.id.IVColA)
        val BTNConfirm = view.findViewById<Button>(R.id.BTNColA)
        BTNConfirm.setOnClickListener {
            if (IVImage!!.drawable != null && ETName.text.toString() != "") {
                val collection = Collection(null,imgPath.toString(), ETName.text.toString(), 0)
                setFragmentResult("request_key", bundleOf("newCollection" to collection))
                dismiss()
            }
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQ_CODE) {
                IVImage!!.setImageURI(data!!.data)
                imgPath = data.data
            }
        }
    }
}