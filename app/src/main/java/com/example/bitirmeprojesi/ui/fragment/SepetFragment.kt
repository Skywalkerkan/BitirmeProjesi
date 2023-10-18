package com.example.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bitirmeprojesi.R
import com.example.bitirmeprojesi.data.entity.SepetYemekler
import com.example.bitirmeprojesi.databinding.FragmentSepetBinding
import com.example.bitirmeprojesi.ui.adapter.SepetAdapter
import com.example.bitirmeprojesi.ui.adapter.YemeklerAdapter
import com.example.bitirmeprojesi.ui.viewmodel.AnasayfaViewModel
import com.example.bitirmeprojesi.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class SepetFragment : Fragment() {

    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSepetBinding.inflate(inflater, container, false)

        binding.sepetRv.layoutManager = LinearLayoutManager(requireContext())

        viewModel.sepetListesi.observe(viewLifecycleOwner){
            val sepetlerAdapter = SepetAdapter(requireContext(), it, viewModel)
            binding.sepetRv.adapter = sepetlerAdapter
            Log.e("Mesaj", "${viewModel.sepetListesi.value}")
            var toplamFiyat = 0
            for(para in viewModel.sepetListesi.value!!){
                toplamFiyat += para.yemek_fiyat * para.yemek_siparis_adet
            }
            Log.e("Mesaj", "${toplamFiyat}")


        }




        return  binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel

     //   Log.e("Mesaj", "${viewModel.sepetListesi.value}")

    }

    override fun onResume() {
        super.onResume()
       // Log.e("Sepet", "${viewModel.sepetListesi.value}")
        //viewModel.sepetiYukle()

        Log.e("On Resume", "girildi")
    }




}