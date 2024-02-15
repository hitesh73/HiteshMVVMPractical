package com.rkoshti.hitesh_practical_mvvm.presenatation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rkoshti.hitesh_practical_mvvm.data.model.UserResponseItem
import com.rkoshti.hitesh_practical_mvvm.databinding.ItemUserDataBinding

class UserDataAdapter : RecyclerView.Adapter<UserDataAdapter.ViewHolder>() {

    private var mList: List<UserResponseItem> = ArrayList()

    inner class ViewHolder(val binding: ItemUserDataBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataAdapter.ViewHolder {
        val binding =
            ItemUserDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDataAdapter.ViewHolder, position: Int) {
        holder.binding.userData = mList[position]
        holder.itemView.setOnClickListener {
            val user = mList[position]
            val dialogFragment = UserDialogFragment(user)
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            dialogFragment.show(fragmentManager, "UserDialogFragment")
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun updateData(updatedUserDataList: List<UserResponseItem>?) {
        if (updatedUserDataList != null) {
            mList = updatedUserDataList
        }
        notifyDataSetChanged()
    }
}