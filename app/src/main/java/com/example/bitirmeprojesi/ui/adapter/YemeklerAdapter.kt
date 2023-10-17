package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragment
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>,var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {

    inner  class CardTasarimTutucu(var tasarim: CardTasarimBinding): RecyclerView.ViewHolder(tasarim.root)
    var siparisAdet = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {

        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return  CardTasarimTutucu(binding)
    }



    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim

        t.yemekTextView.text = "${yemek.yemek_adi}"
        t.fiyatTextView.text = "${yemek.yemek_fiyat}"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(t.imageView).load(url).override(300, 300).into(t.imageView)


        t.buttonEkle.setOnClickListener {
            Log.e("Evet", "EVET")
         /*   Snackbar.make(it,"${yemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET"){*/

                    siparisAdet += 1
                    sepeteEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat, siparisAdet, "son1")
              //  }.show()
        }


        t.cardView.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yemekler = yemek)
            Navigation.findNavController(it).navigate(gecis)
        }

    }



    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    fun sepeteEkle(yemek_adi: String, yemek_resim_adi: String,
                           yemek_fiyat: Int, yemek_siparis_adet: Int,
                           kullanici_adi: String){

        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)

    }

}