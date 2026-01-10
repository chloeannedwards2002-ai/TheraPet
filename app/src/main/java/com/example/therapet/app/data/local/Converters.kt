package com.example.therapet.app.data.local

import androidx.room.TypeConverter
import com.example.therapet.app.data.model.UserRole

/**
 * @author: Chloe Edwards
 * @date: 10/01/2026
 *
 * Type converters for converting enums to database friendly types
 */

class Converters {
    @TypeConverter
    fun fromRole(role: UserRole): String = role.name

    @TypeConverter
    fun toRole(role: String): UserRole = UserRole.valueOf(role)
}