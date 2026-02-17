package com.example.therapet.app.data.model

import com.example.therapet.app.data.entity.UserEntity

/**
 * @author: Chloe Edwards
 * @date: 08/02/2026
 *
 * The appointment database table
 */

data class AccountUIModel(
    val userid: String,
    val fullName: String,
    val role: UserRole,
)

fun UserEntity.toAccountUIModel(): AccountUIModel {
    return AccountUIModel(
        userid = userid,
        fullName = "$firstname $surname",
        role = role,
    )
}