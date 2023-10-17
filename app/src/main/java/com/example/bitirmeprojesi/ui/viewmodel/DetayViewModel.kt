package com.example.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var yrepo: YemeklerRepository) : ViewModel(){


    fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String,
                   yemek_fiyat: Int, yemek_siparis_adet: Int,
                   kullanici_adi: String){
        CoroutineScope(Dispatchers.Main).launch {
            //krepo.kaydet(kisi_ad,kisi_tel)

            yrepo.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
        }
    }




}