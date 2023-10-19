package com.example.bitirmeprojesi.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bitirmeprojesi.data.entity.YemeklerRoom


@Dao
interface YemeklerDaoRoom {
    @Query("SELECT * FROM yemekler")
    suspend fun yemekleriYukle(): List<YemeklerRoom>


    @Insert
    suspend fun kaydetYemek(yemekler: YemeklerRoom)


    /*@Update
    suspend fun guncelle(yemek: Kisiler)*/

    @Delete
    suspend fun silYemek(yemek: YemeklerRoom)

    @Query("SELECT * FROM yemekler WHERE yemek_adi like '%' || :aramaKelimesi || '%'")
    suspend fun araYemek(aramaKelimesi: String): List<YemeklerRoom>



}