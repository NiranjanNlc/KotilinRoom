package com.example.roomapi

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomapi.db.Member
import com.example.roomapi.db.MemberRepo
import kotlinx.coroutines.launch

class MemberViewModel(private  val repo: MemberRepo): ViewModel(), Observable
{
    val members = repo.members
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Member
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
    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    fun saveOrUpdate()
    {
        val name = inputName.value!!
        val phone=inputPhone.value!!
        if (isUpdateOrDelete) {
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.phone = inputPhone.value!!
            update(subscriberToUpdateOrDelete)
        }
        else {
            insert(Member(0, name, phone))
        }
        inputName.value=null
        inputPhone.value=null
        submitutton.value = "Insert"
        clearButton.value ="Clearis"
        isUpdateOrDelete=false

    }
    fun deleteOrClear()
    {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        }
        inputName.value=null
        inputPhone.value=null
        submitutton.value = "Insert"
        clearButton.value ="Clear"
    }
    fun insert(member: Member)=  viewModelScope.launch {
            repo.insertMember(member)
        statusMessage.value = Event("ubscriber inserted successfully")

    }

    fun update(member: Member)=  viewModelScope.launch {
        repo.updateMember(member)
        statusMessage.value = Event("ubscriber updated successfully")

    }
    fun delete(member: Member)=  viewModelScope.launch {
        repo.deletemember(member)
    }
    fun clearAll()=  viewModelScope.launch {
        repo.deleteAll()
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun initUpdateAndDelete(member: Member)
    {
        inputName.value = member.name
        inputPhone.value = member.phone
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = member
        submitutton.value = "Update"
        clearButton.value = "Delete"
        statusMessage.value = Event("ubscriber  successfully")

    }
}