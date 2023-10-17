package com.example.bitirmeprojesi.data.datasource

import android.util.Log
import com.example.bitirmeprojesi.data.entity.CRUDCevap
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class YemeklerDataSource(var ydao: YemeklerDao) {

   /* suspend fun sepeteEkle(@Field("yemek_adi") yemek_adi: String,
                           @Field("yemek_resim_adi") yemek_resim_adi: String,
                           @Field("yemek_fiyat") yemek_fiyat: Int,
                           @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                           @Field("kullanici_adi") kullanici_adi: String): CRUDCevap*/




    suspend fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String,
                           yemek_fiyat: Int, yemek_siparis_adet: Int, kullanici_adi: String){
        val cevap = ydao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        Log.e("Yemek Kaydet","Başarı: ${cevap.success} - Mesaj : ${cevap.message}")
    }


    suspend fun yemekleriYukle(): List<Yemekler> = withContext(Dispatchers.IO) {

        return@withContext ydao.yemekleriYukle().yemekler
    }

    suspend fun seepetiYukle(kullanici_adi: String) : List<SepetYemekler> = withContext(Dispatchers.IO){
        /*val liste = ArrayList<Kisiler>()
        val k1 = Kisiler(1,"Ahmet","1111")
        liste.add(k1)*/
        val cevap = ydao.sepetiYukle(kullanici_adi)

        return@withContext cevap.sepet_yemekler
    }


    suspend fun sil (sepet_yemek_id: Int, kullanici_adi: String) {
        val cevap = ydao.sepettenSil(sepet_yemek_id, kullanici_adi)
        Log.e("kesbe" , "Başaro: ${cevap.success} - Mesaj ${cevap.message}")
    }


}