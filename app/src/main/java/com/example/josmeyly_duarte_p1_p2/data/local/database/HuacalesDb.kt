package com.example.josmeyly_duarte_p1_p2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.josmeyly_duarte_p1_p2.data.local.dao.HuacalesDao
import com.example.josmeyly_duarte_p1_p2.data.local.entities.HuacalesEntity

class HuacalesDb {
    @Database(
        entities = [
            HuacalesEntity::class
        ],
        version = 1,
        exportSchema = false
    )
    abstract class huacalesDb : RoomDatabase(){
        abstract fun huacalesDao(): HuacalesDao
    }
}