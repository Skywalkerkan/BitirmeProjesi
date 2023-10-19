package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.datasource.YemeklerRoomDataSource
import com.example.bitirmeprojesi.data.entity.YemeklerRoom

class YemeklerRoomRepository(var yds: YemeklerRoomDataSource) {


    suspend fun kaydet(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int) = yds.kaydet(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)

  //  suspend fun guncelle(kisi_id:Int, kisi_ad:String, kisi_tel:String) = kds.guncelle(kisi_id,kisi_ad,kisi_tel)

    suspend fun sil(yemek_asil_id:Int) = yds.sil(yemek_asil_id)

    suspend fun yemekleriYukle(): List<YemeklerRoom> = yds.yemekleriYukle()

  //  suspend fun ara(aramaKelimesi: String): List<Kisiler> = kds.ara(aramaKelimesi)


}