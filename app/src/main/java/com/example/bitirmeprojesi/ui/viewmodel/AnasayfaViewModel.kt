package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.entity.YemeklerRoom
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import com.example.bitirmeprojesi.data.repo.YemeklerRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yrepo: YemeklerRepository, var yrepoRoom: YemeklerRoomRepository): ViewModel() {


    var yemeklerListesiRoom = MutableLiveData<List<YemeklerRoom>>()



    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    var sepetListesi = MutableLiveData<List<SepetYemekler>>()
    var sepetListesiAsil = MutableLiveData<List<SepetYemekler>>()
    var siparisAdet = 0

    init {
        yemekleriYukle()
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {

            try {

                yemeklerListesi.value = yrepo.yemekleriYukle()
                Log.e("Yemek", "${yemeklerListesi.value}")
            }catch (e:Exception){}
        }
    }

    fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String,
                   yemek_fiyat: Int, yemek_siparis_adet: Int,
                   kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch {
            //krepo.kaydet(kisi_ad,kisi_tel)

            // sepetiYukle()
            try {

                sepetListesiAsil.value = yrepo.sepetYukle(kullanici_adi = "erkan_cosar")
                Log.e("Sepet   Silindi", "${sepetListesi.value}")
            } catch (e: Exception) {
                Log.e("catch", "${sepetListesi.value}")

                sepetListesiAsil.value = emptyList()
                Log.e("catch", "${sepetListesiAsil.value}")
            }

            val mevcutSepetListesi = sepetListesiAsil.value

            if (mevcutSepetListesi != null && mevcutSepetListesi.isNotEmpty()) {
                // Aynı yemek adına sahip olanları gruplamak için bir Map kullanalım.
                val gruplanmisYemekler = mutableMapOf<String, SepetYemekler>()

                Log.e("catch", "${sepetListesiAsil.value}")


                // Mevcut sepeti gruplayın
                if (mevcutSepetListesi != null) {
                    for (yemek in mevcutSepetListesi) {
                        val yemekAdi = yemek.yemek_adi
                        siparisAdet = 0
                        if (gruplanmisYemekler.containsKey(yemekAdi)) {
                            // Aynı yemek adına sahip olanları gruplayın.
                            siparisAdet = gruplanmisYemekler[yemekAdi]!!.yemek_siparis_adet
                            //if (yemek.yemek_siparis_adet > mevcutSiparisAdet) {
                            // Daha büyük sipariş adeti olanı güncelleyin.
                            gruplanmisYemekler[yemekAdi]!!.yemek_siparis_adet =
                                siparisAdet + yemek.yemek_siparis_adet
                            //   }
                            sepettenSil(
                                sepet_yemek_id = yemek.sepet_yemek_id,
                                kullanici_adi = "erkan_cosar"
                            )
                        } else {
                            // İlk defa bu yemek adı ile karşılaşıldı, ekleyin.
                            gruplanmisYemekler[yemekAdi] = yemek
                            sepettenSil(
                                sepet_yemek_id = yemek.sepet_yemek_id,
                                kullanici_adi = "erkan_cosar"
                            )

                        }
                    }
                }

                Log.e("Evet1", "${sepetListesiAsil.value}")

                // gruplanmisYemekler[yemek_adi]!!.yemek_siparis_adet += yemek_siparis_adet

                val hedefYemek = gruplanmisYemekler[yemek_adi]

                if (hedefYemek != null) {
                    hedefYemek.yemek_siparis_adet += yemek_siparis_adet
                } else {
                    // Eşleşen yemek adına sahip öğe bulunamadı, burada bir işlem yapılabilir.
                    yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
                }

                // Yeni sepet listesini güncelleyin
                Log.e("Evet2", "${sepetListesiAsil.value}")

                sepetListesiAsil.value = gruplanmisYemekler.values.toList()

                Log.e("Evet", "${sepetListesiAsil.value}")

                // Yeni sepet listesine siparişi ekleyin
                // val yeniSiparis = SepetYemekler(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
                //  val yeniListe = ArrayList(sepetListesiAsil.value)
                //   yeniListe.add(yeniSiparis)
                //   sepetListesiAsil.value = yeniListe
                val siparisAdetler = gruplanmisYemekler.values.map { it.yemek_siparis_adet }.sum()
                val siparisAdi = gruplanmisYemekler.values.map { it.yemek_adi }
                val siparisFiyat = gruplanmisYemekler.values.map { it.yemek_fiyat }.sum()
                val siparisResimAdi = gruplanmisYemekler.values.map { it.yemek_resim_adi }


                Log.e("Contains", "${siparisAdi}")
                Log.e("Contains", "${siparisFiyat}")
                Log.e("Contains", "${siparisResimAdi}")
                Log.e("Contains", "${siparisAdetler}")



                for (ekle in sepetListesiAsil.value!!){
                    yrepo.sepeteEkle(
                        ekle.yemek_adi,
                        ekle.yemek_resim_adi,
                        ekle.yemek_fiyat,
                        ekle.yemek_siparis_adet,
                        "erkan_cosar"
                    )
                }



            }
            else{
                yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
            }


            //   yrepo.sepeteEkle()

            //  yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }


    fun sepetiYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            try {

                sepetListesi.value = yrepo.sepetYukle(kullanici_adi = "erkan_cosar")
                //   Log.e("SepetSilindi", "${sepetListesi.value}")
            } catch (e: Exception) {

            }
        }
    }

    fun sepettenSil(sepet_yemek_id: Int, kullanici_adi: String) {

        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sil(sepet_yemek_id, kullanici_adi)

            sepetiYukle()
        }

    }



    fun roomKaydet(yemek_id: Int, yemek_adi: String, yemek_resim_adi: String, yemek_fiyat: Int){
        CoroutineScope(Dispatchers.Main).launch {
            yrepoRoom.kaydet(yemek_id, yemek_adi, yemek_resim_adi, yemek_fiyat)
        }
    }

    fun yemekleriYukleRoom(){
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesiRoom.value = yrepoRoom.yemekleriYukle()
            Log.e("room", "${yemeklerListesiRoom.value}")
        }
    }


    fun sepettenSilRoom(yemek_asil_id: Int){

        CoroutineScope(Dispatchers.Main).launch {
            yrepoRoom.sil(yemek_asil_id)

          //  yemekleriYukle()
        }

    }


    /*fun yemekEkle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {

            }catch (e:Exception){}
        }
    }*/





}