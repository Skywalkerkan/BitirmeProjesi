package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel() {


    var sepetListesi = MutableLiveData<List<SepetYemekler>>()
    var sepetListesiAsil = MutableLiveData<List<SepetYemekler>>()
    var siparisAdet = 0

    fun sepeteEkle(
        yemek_adi: String, yemek_resim_adi: String,
        yemek_fiyat: Int, yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            //krepo.kaydet(kisi_ad,kisi_tel)

            // sepetiYukle()
            try {

                sepetListesiAsil.value = yrepo.sepetYukle(kullanici_adi = "son2")
                Log.e("Sepet   Silindi", "${sepetListesi.value}")
            } catch (e: Exception) {
                Log.e("catch", "${sepetListesi.value}")

            }

            val mevcutSepetListesi = sepetListesiAsil.value

            if (mevcutSepetListesi != null && mevcutSepetListesi.isNotEmpty()) {
                // hashmap
                val gruplanmisYemekler = mutableMapOf<String, SepetYemekler>()

                Log.e("catch", "${sepetListesiAsil.value}")


                // Mevcut sepeti gruplayın
                if (mevcutSepetListesi != null) {
                    for (yemek in mevcutSepetListesi) {
                        val yemekAdi = yemek.yemek_adi
                        siparisAdet = 0
                        if (gruplanmisYemekler.containsKey(yemekAdi)) {
                            // yemek adı gruplamaası
                            siparisAdet = gruplanmisYemekler[yemekAdi]!!.yemek_siparis_adet
                            //if (yemek.yemek_siparis_adet > mevcutSiparisAdet) {

                            gruplanmisYemekler[yemekAdi]!!.yemek_siparis_adet =
                                siparisAdet + yemek.yemek_siparis_adet
                            //   }
                            sepettenSil(
                                sepet_yemek_id = yemek.sepet_yemek_id,
                                kullanici_adi = "son2"
                            )
                        } else {
                            // İlk yemek
                            gruplanmisYemekler[yemekAdi] = yemek
                            sepettenSil(
                                sepet_yemek_id = yemek.sepet_yemek_id,
                                kullanici_adi = "son2"
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

                // Yeni sepet listesini güncelle
                Log.e("Evet2", "${sepetListesiAsil.value}")

                sepetListesiAsil.value = gruplanmisYemekler.values.toList()

                Log.e("Evet", "${sepetListesiAsil.value}")

                // Yeni sepet listesine siparişi ekle
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
                        "son2"
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

                sepetListesi.value = yrepo.sepetYukle(kullanici_adi = "son2")
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
}






