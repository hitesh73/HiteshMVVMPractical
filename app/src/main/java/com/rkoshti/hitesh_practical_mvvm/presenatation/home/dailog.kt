package com.rkoshti.hitesh_practical_mvvm.presenatation.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.databinding.DialogUserBinding

class UserDialogFragment(private val user: UserResponseItem) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialogView = DialogUserBinding.inflate(layoutInflater)

        dialogView.user = user

        return AlertDialog.Builder(requireContext())
            .setView(dialogView.root)
            .setPositiveButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}