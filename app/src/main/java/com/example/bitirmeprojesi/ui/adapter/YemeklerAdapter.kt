package com.example.bitirmeprojesi.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.data.entity.YemeklerRoom
import com.example.bitirmeprojesi.data.repo.YemeklerRoomRepository
import com.example.bitirmeprojesi.databinding.CardTasarimBinding
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragment
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragmentDirections
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.google.android.material.snackbar.Snackbar

class YemeklerAdapter(var mContext: Context, var yemeklerListesi: List<Yemekler>,var favoriteListesi: MutableLiveData<List<YemeklerRoom>>, var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu>() {





    inner  class CardTasarimTutucu(var tasarim: CardTasarimBinding): RecyclerView.ViewHolder(tasarim.root)
    var siparisAdet = 0
    var i = 0
    var ids =  mutableListOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {


        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
       /* if (i == 0){
            favoriteListesi.value = viewModel.yemeklerListesiRoom.value
            Log.e("cikita", "${favoriteListesi.value}")

            for (i in favoriteListesi.value!!){
                ids.add(i.yemek_id)
            }
            i+=1
            Log.e("cikita", "${ids}")

        }*/

        return  CardTasarimTutucu(binding)
    }


    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {

        val yemek = yemeklerListesi.get(position)
        val t = holder.tasarim


        if (i == 0){
            favoriteListesi.value = viewModel.yemeklerListesiRoom.value
          //  Log.e("cikita", "${favoriteListesi.value}")

            for (i in favoriteListesi.value!!){
                ids.add(i.yemek_id)
            }
            i+=1
          //  Log.e("cikita", "${ids}")

        }

      //  favoriteList.value = viewModel.yrepoRoom.yemekleriYukle()
       // viewModel.yemekleriYukleRoom()



        for (fav in viewModel.yemeklerListesiRoom.value!!){
            val kalpResimId = mContext.resources.getIdentifier("kalp_resim", "drawable", mContext.packageName)
            val kalpResimBosId = mContext.resources.getIdentifier("kalp_resim_bos", "drawable", mContext.packageName)
            if(ids.contains(yemek.yemek_id)){

                t.imageView4.setImageResource(kalpResimId)
                t.imageView4.tag = kalpResimId

              //  Log.e("Şuandagirildi", "evet")

            }else{
              //   Log.e("Şuanda", "evet")




                t.imageView4.setImageResource(kalpResimBosId)
                t.imageView4.tag = kalpResimBosId
                //viewModel.sepettenSilRoom()
               // Log.e("cikitas", "${favoriteListesi.value[0]}")
                /*for (i in favoriteListesi.value!!){
                    if(i.yemek_id == yemek.yemek_id){
                        viewModel.sepettenSilRoom(i.yemek_id_asil)
                    }
                }*/

            }
        }

        var siparisAdet = 0
        t.yemekTextView.text = "${yemek.yemek_adi}"
        t.fiyatTextView.text = "${yemek.yemek_fiyat} tl"
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Glide.with(t.imageView).load(url).override(300, 300).into(t.imageView)


        t.buttonEkle.setOnClickListener {
        //    Log.e("Evet", "EVET")
         /*   Snackbar.make(it,"${yemek.yemek_adi} silinsin mi?", Snackbar.LENGTH_SHORT)
                .setAction("EVET"){*/

                    siparisAdet = 1
                    sepeteEkle(yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat, siparisAdet, "erkan_cosar")
              //  }.show()
             Toast.makeText(mContext, "${yemek.yemek_adi} Sepete Eklendi", Toast.LENGTH_SHORT).show()

        }


        t.cardView.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(yemekler = yemek)
            Navigation.findNavController(it).navigate(gecis)

        }

        t.imageView4.setOnClickListener {
            val kalpResimId = mContext.resources.getIdentifier("kalp_resim", "drawable", mContext.packageName)
            val kalpResimBosId = mContext.resources.getIdentifier("kalp_resim_bos", "drawable", mContext.packageName)

            if (kalpResimId != 0) {
               // Log.e("messi", "${t.imageView4.tag}")
                if (t.imageView4.tag == null || t.imageView4.tag == kalpResimBosId) {
                    t.imageView4.setImageResource(kalpResimId)
                    t.imageView4.tag = kalpResimId
                    viewModel.roomKaydet(yemek.yemek_id, yemek.yemek_adi, yemek.yemek_resim_adi, yemek.yemek_fiyat)
                    viewModel.yemekleriYukleRoom()
                  //  Log.e("Silindi mi", "hayır")
                } else {
                    t.imageView4.setImageResource(kalpResimBosId)
                    t.imageView4.tag = kalpResimBosId

                    for (i in favoriteListesi.value!!){
                        if(i.yemek_id == yemek.yemek_id){
                            viewModel.sepettenSilRoom(i.yemek_id_asil)
                            viewModel.yemekleriYukleRoom()
                            t.imageView4.setImageResource(kalpResimBosId)
                            t.imageView4.tag = kalpResimBosId
                         //   Log.e("Silindi mi", "evet")




                        }else{
                           // Log.e("keke", "${yemek.yemek_id}")
                            //ids.remove(yemek.yemek_id)
                           // viewModel.sepettenSilRoom(i.yemek_id_asil)

                          //  favoriteListesi.value = viewModel.yemeklerListesiRoom.value
                        }

                        //Log.e("Silindi mi2", "${yemek.yemek_id}")

                    }

                  //  viewModel.sepettenSilRoom(yemek)
                }
            }

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