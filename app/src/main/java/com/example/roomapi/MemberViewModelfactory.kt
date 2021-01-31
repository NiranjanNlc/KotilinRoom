package com.example.roomapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomapi.db.MemberRepo
import java.lang.IllegalArgumentException

class MemberViewModelFactory(private val repository: MemberRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MemberViewModel::class.java)){
            return MemberViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}