package com.example.roomapi.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member_table")
data class Member (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="memeber_id")
    val id: Int,
    @ColumnInfo(name="member_name")
    var name:String,
    @ColumnInfo(name = "member_phone")
    var phone:String
)