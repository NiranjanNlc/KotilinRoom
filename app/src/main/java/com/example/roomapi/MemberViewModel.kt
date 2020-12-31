package com.example.roomapi

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapi.db.Member
import com.example.roomapi.db.SubscriberRepo
import kotlinx.coroutines.launch

class MemberViewNodel(private  val repo: SubscriberRepo): ViewModel()
{
    val members = repo.members
    @Bindable
    val inputName = MutableLiveData<String>()
    @Bindable
    val inputPhone = MutableLiveData<String>()

    @Bindable
    val submitutton =MutableLiveData<String>()

    @Bindable
    val clearButton =MutableLiveData<String>()
     init {
         submitutton.value = "Insert"
         clearButton.value ="Clear"
     }

    fun saveOrUpdate()
    {
        val name = inputName.value!!
        val phone=inputPhone.value!!
        insert(Member(0,name,phone))
        inputName.value=null
        inputPhone.value=null
    }
    fun deleteOrClear()
    {
       clearAll()
    }
    fun insert(member: Member)=  viewModelScope.launch {
            repo.insertMember(member)
        }

    fun update(member: Member)=  viewModelScope.launch {
        repo.updateMember(member)
    }
    fun delete(member: Member)=  viewModelScope.launch {
        repo.deletemember(member)
    }
    fun clearAll()=  viewModelScope.launch {
        repo.deleteAll()
    }
}