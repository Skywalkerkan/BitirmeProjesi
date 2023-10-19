package com.example.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.YemeklerRoom
import com.example.bitirmeprojesi.data.repo.YemeklerRepository
import com.example.bitirmeprojesi.data.repo.YemeklerRoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(var yrepoRoom: YemeklerRoomRepository): ViewModel() {


    var favoriteList = MutableLiveData<List<YemeklerRoom>>()


    /*init {
        sepetiYukle()
    }*/

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            try {

                favoriteList.value = yrepoRoom.yemekleriYukle()
                Log.e("room", "${favoriteList.value}")
            }catch (e: Exception){

            }
        }
    }

    fun sepettenSil(yemek_asil_id: Int){

        CoroutineScope(Dispatchers.Main).launch {
            yrepoRoom.sil(yemek_asil_id)

            yemekleriYukle()
        }

    }





}