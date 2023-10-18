package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.SepetcardTasarimBinding
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var mContext: Context, var sepetListesi: List<SepetYemekler>, var viewModel: SepetViewModel)
    :RecyclerView.Adapter<SepetAdapter.CardTasarimTutucuSepet>(){


    inner class CardTasarimTutucuSepet(var tasarimSepet: SepetcardTasarimBinding)
        : RecyclerView.ViewHolder(tasarimSepet.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucuSepet {
        val binding = SepetcardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)


        return  CardTasarimTutucuSepet(binding)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucuSepet, position: Int) {

        Log.e("Erkan", "${sepetListesi.size}")

            val yemek = sepetListesi.get(position)
            val t = holder.tasarimSepet

            t.yemekAd.text = "${yemek.yemek_adi}"
            t.yemekFiyat.text = "${yemek.yemek_fiyat} tl"
            t.yemekAdet.text = "${yemek.yemek_siparis_adet} Adet"
            t.yemekToplamFiyat.text = "${yemek.yemek_fiyat * yemek.yemek_siparis_adet} tl"


            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
            Glide.with(t.imageView2).load(url).override(300, 300).into(t.imageView2)

            t.imageViewSil.setOnClickListener {
                Log.e("SepetSilindi", "${yemek.yemek_siparis_adet}")
                Snackbar.make(it, "${yemek.sepet_yemek_id}  ${yemek.yemek_adi} Silinsin mi", Snackbar.LENGTH_SHORT)
                    .setAction("Evet"){
                        sepettenSil(sepet_yemek_id = yemek.sepet_yemek_id, kullanici_adi = yemek.kullanici_adi)

                        if (sepetListesi.size == 1){
                            sepetListesi = emptyList() // SepetListesi'ni boş bir liste ile güncelle
                            notifyDataSetChanged()

                        }

                    }.show()
            }






    }

    override fun getItemCount(): Int {
      //  Log.e("SepetSilindi", "${sepetListesi.size}")

        return sepetListesi.size
    }


    fun sepettenSil(sepet_yemek_id: Int,
                   kullanici_adi: String){

        viewModel.sepettenSil(sepet_yemek_id, kullanici_adi)



    }


}

