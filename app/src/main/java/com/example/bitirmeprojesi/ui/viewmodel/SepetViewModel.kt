package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class SepetViewModel @Inject constructor(var yrepo: YemeklerRepository): ViewModel() {


    var sepetListesi = MutableLiveData<List<SepetYemekler>>()


    init {
        sepetiYukle()
    }

    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {

                sepetListesi.value = yrepo.sepetYukle(kullanici_adi = "erkan_cosar")
                Log.e("SepetSilindi", "${sepetListesi.value}")
            }catch (e: Exception){
                Log.e("SepetSilindi", "keke")
                sepetListesi.value = emptyList() // Boş bir liste ile başlat

            }
        }
    }

    fun sepettenSil(sepet_yemek_id: Int, kullanici_adi: String){

        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sil(sepet_yemek_id, kullanici_adi)

            sepetiYukle()
        }

    }





}