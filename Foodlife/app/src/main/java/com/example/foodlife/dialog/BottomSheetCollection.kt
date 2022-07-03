package com.example.foodlife.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.foodlife.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetCollection : BottomSheetDialogFragment() {
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
        return view
    }
}