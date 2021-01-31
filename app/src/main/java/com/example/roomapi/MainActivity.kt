package com.example.roomapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapi.adapter.MemberAdapter
import com.example.roomapi.databinding.ActivityMainBinding
import com.example.roomapi.db.Member
import com.example.roomapi.db.MemberDatabase
import com.example.roomapi.db.MemberRepo
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var memberViewModel: MemberViewModel
    private lateinit var adapter: MemberAdapter
//    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
    binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    val dao = MemberDatabase.getInstance(application).MemberDao
    val repository = MemberRepo(dao)
    val factory = MemberViewModelFactory(repository)
    memberViewModel = ViewModelProvider(this,factory).get(MemberViewModel::class.java)
    binding.myViewModel = memberViewModel
    binding.lifecycleOwner = this

    initRecyclerView()
    memberViewModel.message.observe(this, Observer {
        it.getContentIfNotHandled()?.let {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    })

}

    private fun initRecyclerView(){
        binding.memberRecycler.layoutManager = LinearLayoutManager(this)
        adapter = MemberAdapter { selectedItem:Member->listItemClicked(selectedItem)}
        binding.memberRecycler.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        memberViewModel.members.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(member: Member){
       Toast.makeText(this,"selected name is ${member.name}",Toast.LENGTH_LONG).show()
        memberViewModel.initUpdateAndDelete(member)

       // memberViewModel.update(member)
    }
}

