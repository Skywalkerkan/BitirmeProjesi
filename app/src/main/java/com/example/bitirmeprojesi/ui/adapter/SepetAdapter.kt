package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.SepetcardTasarimBinding
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel

class SepetAdapter(var mContext: Context, var sepetListesi: List<SepetYemekler>, var viewModel: SepetViewModel)
    :RecyclerView.Adapter<SepetAdapter.CardTasarimTutucuSepet>(){


    inner class CardTasarimTutucuSepet(var tasarimSepet: SepetcardTasarimBinding)
        : RecyclerView.ViewHolder(tasarimSepet.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucuSepet {
        val binding = SepetcardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)

        return  CardTasarimTutucuSepet(binding)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucuSepet, position: Int) {
        val yemek = sepetListesi.get(position)
        val t = holder.tasarimSepet

        t.yemekAd.text = "${yemek.yemek_adi}"
        t.yemekFiyat.text = "${yemek.yemek_fiyat}"
        t.yemekAdet.text = "${yemek.yemek_siparis_adet}"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(t.imageView2).load(url).override(300, 300).into(t.imageView2)


    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }


}

