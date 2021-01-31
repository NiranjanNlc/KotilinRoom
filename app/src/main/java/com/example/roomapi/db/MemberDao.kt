package com.example.roomapi.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemberDao {

    @Insert
    suspend fun  insertMember(member: Member):Long

    @Update
    suspend fun updateMember(member: Member):Int

    @Delete
    suspend fun deleteMember(member: Member):Int

    @Query("delete  from member_table")
    suspend fun  deleteAll():Int

    @Query("Select * from member_table")
    fun getAllMember():LiveData<List<Member>>
}