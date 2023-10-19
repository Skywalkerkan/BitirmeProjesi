package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.entity.YemeklerRoom
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.FavoriteCardTasarimBinding
import com.example.bitirmeprojesi.databinding.SepetcardTasarimBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.example.bitirmeprojesi.ui.fragment.FavoriteFragment
import com.example.bitirmeprojesi.ui.fragment.FavoriteFragmentDirections
import com.example.bitirmeprojesi.ui.viewmodel.FavoriteViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class FavoriteAdapter(var mContext: Context, var favoriteListesi: List<YemeklerRoom>, var viewModel: FavoriteViewModel)
    :RecyclerView.Adapter<FavoriteAdapter.CardTasarimTutucuFavorite>() {



    inner class CardTasarimTutucuFavorite(var tasarimFavorite: FavoriteCardTasarimBinding)
        : RecyclerView.ViewHolder(tasarimFavorite.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.CardTasarimTutucuFavorite {

        val binding = FavoriteCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)


        return  CardTasarimTutucuFavorite(binding)

    }



    override fun onBindViewHolder(holder: FavoriteAdapter.CardTasarimTutucuFavorite, position: Int) {
        val yemek = favoriteListesi.get(position)
        val t = holder.tasarimFavorite




        t.yemekAdiFav.text = "${yemek.yemek_adi}"
        t.yemekAdiFiyat.text = "${yemek.yemek_fiyat} tl"
      //  t.yemekAdetFav.text = "${yemek.} Adet"
        t.yemekToplamPara.text = "${yemek.yemek_fiyat} tl"


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(t.yemekFavImage).load(url).override(300, 300).into(t.yemekFavImage)

        t.kalpImage.setOnClickListener {
            Log.e("SepetSilindi", "${yemek.yemek_resim_adi}")
            Snackbar.make(it, "${yemek.yemek_id_asil}  ${yemek.yemek_adi} Silinsin mi", Snackbar.LENGTH_SHORT)
                .setAction("Evet"){

                viewModel.sepettenSil(yemek_asil_id = yemek.yemek_id_asil)


                }.show()
        }

        t.cardViewFav.setOnClickListener {
            val gecis = FavoriteFragmentDirections.favoriteToDetay(yemekler = Yemekler(yemek.yemek_id, yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat))
            Navigation.findNavController(it).navigate(gecis)
        }


    }

    override fun getItemCount(): Int {
        return favoriteListesi.size

    }

}