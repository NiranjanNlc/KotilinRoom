package com.example.roomapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapi.R
import com.example.roomapi.databinding.ListItemBinding
import com.example.roomapi.db.Member

class MemberAdapter (private val clickListener:(Member)->Unit)
    : RecyclerView.Adapter<MyViewHolder>()
{
    private val MembersList = ArrayList<Member>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //getting size of the items
        return MembersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(MembersList[position],clickListener)
    }

    fun setList(Members: List<Member>) {
        MembersList.clear()
        MembersList.addAll(Members)

    }


}

class MyViewHolder(val binding: ListItemBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(Member: Member,clickListener:(Member)->Unit){
        binding.nameTextView.text = Member.name
        binding.emailTextView.text = Member.phone
        binding.listItemLayout.setOnClickListener{
           clickListener(Member)
        }
    }
}