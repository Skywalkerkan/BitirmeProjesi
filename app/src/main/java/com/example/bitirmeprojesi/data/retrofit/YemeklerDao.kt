package com.example.bitirmeprojesi.data.retrofit

import com.example.bitirmeprojesi.data.entity.CRUDCevap
import com.example.bitirmeprojesi.data.entity.SepetCevap
import com.example.bitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php




    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle(): YemeklerCevap


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteEkle(@Field("yemek_adi") yemek_adi: String,
                         @Field("yemek_resim_adi") yemek_resim_adi: String,
                         @Field("yemek_fiyat") yemek_fiyat: Int,
                         @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                         @Field("kullanici_adi") kullanici_adi: String): CRUDCevap



    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetiYukle(@Field("kullanici_adi") kullanici_adi: String): SepetCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenSil(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                            @Field("kullanici_adi") kullanici_adi: String): CRUDCevap

}