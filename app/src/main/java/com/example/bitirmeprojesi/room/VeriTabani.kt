package com.example.bitirmeprojesi.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitirmeprojesi.data.entity.YemeklerRoom


@Database(entities = [YemeklerRoom::class], version = 1)
abstract class VeriTabani: RoomDatabase() {

    abstract fun getYemeklerDao(): YemeklerDaoRoom

}