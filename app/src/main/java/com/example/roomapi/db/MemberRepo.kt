package com.example.roomapi.db

class MemberRepo(private val dao: MemberDao)
{
    val members = dao.getAllMember()

    suspend fun insertMember(member: Member)
    {
    dao.insertMember(member )
    }

    suspend fun deletemember(member: Member)
    {
        dao.deleteMember(member)
    }

    suspend fun updateMember(member: Member)
    {
        dao.updateMember(member)
    }
    suspend fun deleteAll()
    {
        dao.deleteAll()
    }
}