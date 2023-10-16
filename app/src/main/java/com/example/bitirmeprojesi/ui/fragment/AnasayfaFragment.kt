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

        binding.yemeklerRv.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val yemeklerAdapter = YemeklerAdapter(requireContext(), it, viewModel)
            binding.yemeklerRv.adapter = yemeklerAdapter
        }




        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {//Harf girdikçe ve sildikçe
                ara(newText)
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

   /* override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }*/



}