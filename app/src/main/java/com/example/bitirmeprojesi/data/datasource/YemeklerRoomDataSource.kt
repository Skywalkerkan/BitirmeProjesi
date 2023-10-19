package com.example.bitirmeprojesi.data.datasource

import android.util.Log
import com.example.bitirmeprojesi.data.entity.YemeklerRoom
import com.example.bitirmeprojesi.room.YemeklerDaoRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerRoomDataSource(var ydao: YemeklerDaoRoom) {

    suspend fun kaydet(yemek_id:Int,yemek_adi:String, yemek_resim_adi: String, yemek_fiyat: Int){
        val yeniYemek = YemeklerRoom(0,yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)
        ydao.kaydetYemek(yeniYemek)
        Log.e("Kişi Kaydet","$yemek_adi - $yemek_resim_adi")
    }


    suspend fun yemekleriYukle(): List<YemeklerRoom> = withContext(Dispatchers.IO){


        return@withContext ydao.yemekleriYukle()
    }

    suspend fun sil(yemek_asil_id:Int){
        val silinenKisi = YemeklerRoom(yemek_asil_id,0,"", "", 0)
        ydao.silYemek(silinenKisi)
        Log.e("Yemek Sil", "$silinenKisi")
    }


}