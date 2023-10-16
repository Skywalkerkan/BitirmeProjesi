package com.example.bitirmeprojesi.data.repo

import com.example.bitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler

class YemeklerRepository(var yds: YemeklerDataSource) {

    suspend fun yemekleriYukle(): List<Yemekler> = yds.yemekleriYukle()

    suspend fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String,
                           yemek_fiyat: Int, yemek_siparis_adet: Int,
                           kullanici_adi: String) = yds.sepeteEkle(yemek_adi, yemek_resim_adi,
                                                        yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    suspend fun sepetYukle(kullanici_adi: String): List<SepetYemekler> = yds.seepetiYukle(kullanici_adi)

   // suspend fun ara(aramaKelimesi:String) : List<Kisiler> = kds.ara(aramaKelimesi)



}