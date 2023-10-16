package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var yrepo: YemeklerRepository): ViewModel() {


    var yemeklerListesi = MutableLiveData<List<Yemekler>>()


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

            yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }

    /*fun yemekEkle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {

            }catch (e:Exception){}
        }
    }*/





}