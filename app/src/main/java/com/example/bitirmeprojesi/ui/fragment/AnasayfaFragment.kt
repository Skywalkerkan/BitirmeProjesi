package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.Yemekler
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.ui.adapter.YemeklerAdapter
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {

    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

            binding.yemeklerRv.layoutManager = GridLayoutManager(requireContext(), 2)
    //StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            viewModel.yemeklerListesi.observe(viewLifecycleOwner){
                val yemeklerAdapter = YemeklerAdapter(requireContext(), it, viewModel)
                binding.yemeklerRv.adapter = yemeklerAdapter
            }




            binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe
                    val yemeklerListesi = viewModel.yemeklerListesi.value

                    val filteredYemekler = yemeklerListesi?.filter { yemek ->
                        yemek.yemek_adi.contains(newText, ignoreCase = true)

                    }


                   /* Log.e("Mesaj", "${yemeklerListesi}")
                    if (yemeklerListesi == null) {
                        Log.e("Mesaj", "${yemeklerListesi}")
                    }*/

                    /*if (yemeklerListesi != null) {
                        if (yemeklerListesi.size > 0) {
                            // Liste dolu
                            Log.e("Mesajd", "${yemeklerListesi}")
                        } else {
                            // Liste boş
                            Log.e("Mesajb", "${yemeklerListesi}")
                            viewModel.yemekleriYukle()
                            viewModel.yemeklerListesi.value = filteredYemekler
                        }
                    }*/


                    viewModel.yemeklerListesi.value = filteredYemekler



               //     Log.e("Mesaj", "${viewModel.yemeklerListesi.value?.size}")

                    if (newText == "") {
                        Log.e("Mesaj", "${newText}")
                        viewModel.yemekleriYukle()
                    }


                    //Log.e("Mesaj", "$yemeklerListesi")

                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {//Arama iconuna bastığında
                    ara(query)
                    return false
                }
            })


            return  binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun ara(aramaKelimesi: String){
        Log.e("Ara", "$aramaKelimesi")
    }

    /*override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }*/



}