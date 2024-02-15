package com.rkoshti.hitesh_practical_mvvm.utils


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import com.rkoshti.hitesh_practical_mvvm.R
import com.rkoshti.hitesh_practical_mvvm.databinding.ProgressLayoutBinding

class CustomProgressDialog(context: Context) {
    private var dialog: CustomDialog
    private var cpTitle: TextView
    private var cpCardView: CardView
    private var progressBar: ProgressBar

    fun start(title: String) {
        cpTitle.text = title
        dialog.show()
    }

    fun show(): Boolean {
        return dialog.isShowing
    }

    fun stop() {
        dialog.dismiss()
    }

    init {
        val inflater = (context as Activity).layoutInflater
        //val view = inflater.inflate(R.layout.progress_layout, null)

        val binding = ProgressLayoutBinding.inflate(inflater)

        cpTitle = binding.cpTitle
        cpCardView = binding.cpCardview
        progressBar = binding.cpPbar

        // Card Color
        cpCardView.setCardBackgroundColor(Color.parseColor("#70000000"))

        // Progress Bar Color
        setColorFilter(progressBar.indeterminateDrawable,
            ResourcesCompat.getColor(context.resources, R.color.black, null))

        // Text Color
        cpTitle.setTextColor(Color.WHITE)

        // Custom Dialog initialization
        dialog = CustomDialog(context)
        dialog.setContentView(binding.root)
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            @Suppress("DEPRECATION")
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
        init {
            // Set Semi-Transparent Color for Dialog Background
            window?.decorView?.rootView?.setBackgroundResource(android.R.color.transparent)
            window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
                insets.consumeSystemWindowInsets()
            }
        }
    }
}