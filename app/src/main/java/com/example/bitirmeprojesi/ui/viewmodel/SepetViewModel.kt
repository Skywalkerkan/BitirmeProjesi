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


  /*  init {
        sepetiYukle()
    }*/

    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetListesi.value
                sepetListesi.value = yrepo.sepetYukle(kullanici_adi = "10")
                Log.e("Yemek", "${sepetListesi.value}")
            }catch (e: Exception){}
        }
    }





}