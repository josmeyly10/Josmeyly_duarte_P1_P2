package com.example.josmeyly_duarte_p1_p2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.josmeyly_duarte_p1_p2.data.local.dao.HuacalesDao
import com.example.josmeyly_duarte_p1_p2.data.local.entities.HuacalesEntity


    @Database(
        entities = [
            HuacalesEntity::class
        ],
        version = 4,
        exportSchema = false
    )
    abstract class HuacalesDb : RoomDatabase(){
        abstract fun huacalesDao(): HuacalesDao
    }
