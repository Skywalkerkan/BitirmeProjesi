package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentDetayBinding
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.example.bitirmeprojesi.ui.viewmodel.DetayViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class DetayFragment : Fragment() {

    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetayBinding.inflate(inflater, container, false)

        val bundle:DetayFragmentArgs by navArgs()

        val gelenYemek = bundle.yemekler

        var sayi = 0

        Log.e("gelen", "$gelenYemek")


        binding.textViewFiyat.text = "${gelenYemek.yemek_fiyat} ₺"
        binding.textViewYemekAd.text = "${gelenYemek.yemek_adi}"


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Glide.with(binding.yemekImage).load(url).override(300, 300).into(binding.yemekImage)



        binding.buttonSepeteEkle.setOnClickListener {
            //viewModel.sepeteEkle(gelenYemek.yemek_adi, gelenYemek.yemek_resim_adi, gelenYemek.yemek_fiyat, 5, "son1")
           // viewModel
            Log.e("gelen", "$gelenYemek")
            if (sayi > 0){ // Ekleyebilirsin hata
                    viewModel.sepeteEkle(gelenYemek.yemek_adi, gelenYemek.yemek_resim_adi, gelenYemek.yemek_fiyat, sayi,"erkan_cosar")
                }
            else{
                Toast.makeText(context, "Sipariş Adeti 0'dan büyük olmalı", Toast.LENGTH_SHORT).show()
            }
        }


        binding.artiButton.setOnClickListener {
            sayi+= 1

            binding.textViewAdet.text = "$sayi"
        }

        binding.eksiButton.setOnClickListener {

            if (sayi != 0){
                sayi-= 1

                binding.textViewAdet.text = "$sayi"
            }

        }

        return  binding.root



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel
    }

}