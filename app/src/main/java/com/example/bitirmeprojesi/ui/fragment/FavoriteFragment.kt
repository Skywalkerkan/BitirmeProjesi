package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.databinding.FragmentAnasayfaBinding
import com.example.bitirmeprojesi.databinding.FragmentFavoriteBinding
import com.example.bitirmeprojesi.ui.adapter.FavoriteAdapter
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter
import com.example.bitirmeprojesi.ui.viewmodel.FavoriteViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint



/**
 * A simple [Fragment] subclass.
 * Use the [FavoriteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.favRv.layoutManager = LinearLayoutManager(requireContext())


        viewModel.favoriteList.observe(viewLifecycleOwner){
            val favoritesAdapter = FavoriteAdapter(requireContext(), it, viewModel)
            binding.favRv.adapter = favoritesAdapter
            Log.e("Mesaj", "${viewModel.favoriteList.value}")
            var toplamFiyat = 0
           /* for(para in viewModel.favoriteList.value!!){
                toplamFiyat += para.yemek_fiyat * para.yemek_siparis_adet
            }
            Log.e("Mesaj", "${toplamFiyat}")*/


        }



        return binding.root


    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoriteViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.yemekleriYukle()
    }



}