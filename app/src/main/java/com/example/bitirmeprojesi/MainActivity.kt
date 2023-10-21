package com.example.bitirmeprojesi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.bitirmeprojesi.databinding.ActivityMainBinding
import com.example.bitirmeprojesi.ui.fragment.AnasayfaFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    var sayi = 0
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      /*  val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.navController)

        val fragmentManager = supportFragmentManager // MainActivity içinde kullanıyorsanız*/

        val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController

        Log.e("Görüntü", "Girdi")


        navController.addOnDestinationChangedListener{_,destination,_->
            when(destination.id) {
                R.id.anasayfaFragment2-> {
                    binding.button.visibility = View.VISIBLE
                    binding.button2.visibility = View.VISIBLE
                    binding.imageButton2.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
                R.id.splashScreenFragment

                -> {
                    binding.button.visibility = View.GONE
                    binding.button2.visibility = View.GONE
                    binding.imageButton2.visibility = View.GONE
                    binding.toolbar.visibility = View.GONE

                }
            }
        }




        binding.button.setOnClickListener {
            val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
            if (navController.currentDestination != null && navController.currentDestination!!.id != R.id.anasayfaFragment2) {

               // navController.popBackStack()
                //   binding.button.setBackgroundColor(resources.getColor(R.color.purple, null))
                //   binding.button2.setBackgroundColor(resources.getColor(R.color.orange, null))
              //  sayi = 0

                val navController = findNavController(R.id.navHostFragment)


                val currentDestination = navController.currentDestination

                if (currentDestination?.id != R.id.anasayfaFragment2) {
                    // Geçerli fragment, sepetFragment değilse, sepetFragment'a git
                    navController.navigate(R.id.anasayfaFragment2)




                    val yeniIkon = resources.getDrawable(R.drawable.kalp_bos_menu)
                    val boyutDP = 36
                    val boyutPX = (boyutDP * resources.displayMetrics.density).toInt()
                    yeniIkon.setBounds(-6, 4, boyutPX, boyutPX)
                    binding.button2.setCompoundDrawables(null, yeniIkon, null, null)


                    val anasayfaIkon = resources.getDrawable(R.drawable.anasayfa_resim)

                    anasayfaIkon.setBounds(-6, 4, boyutPX, boyutPX)
                    binding.button.setCompoundDrawables(null, anasayfaIkon, null, null)
                    binding.imageButton2.setColorFilter(Color.WHITE)

                }



            }
        }


        binding.button2.setOnClickListener {
          /*  val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
            if (navController.currentDestination != null && navController.currentDestination!!.id != R.id.anasayfaFragment2) {

                navController.popBackStack()
             //   binding.button.setBackgroundColor(resources.getColor(R.color.purple, null))
             //   binding.button2.setBackgroundColor(resources.getColor(R.color.orange, null))
                sayi = 0
            }*/

            val navController = findNavController(R.id.navHostFragment)


            val currentDestination = navController.currentDestination

            if (currentDestination?.id != R.id.favoriteFragment) {
                // Geçerli fragment, sepetFragment değilse, sepetFragment'a git
                navController.navigate(R.id.favoriteFragment)
                val kalpIkon = resources.getDrawable(R.drawable.kalp_menu)
                val boyutDP = 36
                val boyutPX = (boyutDP * resources.displayMetrics.density).toInt()
                kalpIkon.setBounds(-6, 4, boyutPX, boyutPX)
                binding.button2.setCompoundDrawables(null, kalpIkon, null, null)

                val anasayfaIkon = resources.getDrawable(R.drawable.anasayfa_resim_bos)

                anasayfaIkon.setBounds(-6, 4, boyutPX, boyutPX)
                binding.button.setCompoundDrawables(null, anasayfaIkon, null, null)
                binding.imageButton2.setColorFilter(Color.WHITE)



            }

        }





        binding.imageButton2.setOnClickListener {

            val navController = findNavController(R.id.navHostFragment)


            val currentDestination = navController.currentDestination

            if (currentDestination?.id != R.id.sepetFragment) {
                // Geçerli fragment, sepetFragment değilse, sepetFragment'a git
                navController.navigate(R.id.sepetFragment)

                binding.imageButton2.setColorFilter(Color.BLACK)


                val kalpIkon = resources.getDrawable(R.drawable.kalp_bos_menu)
                val boyutDP = 36
                val boyutPX = (boyutDP * resources.displayMetrics.density).toInt()
                kalpIkon.setBounds(-6, 4, boyutPX, boyutPX)
                binding.button2.setCompoundDrawables(null, kalpIkon, null, null)

                val anasayfaIkon = resources.getDrawable(R.drawable.anasayfa_resim_bos)

                anasayfaIkon.setBounds(-6, 4, boyutPX, boyutPX)
                binding.button.setCompoundDrawables(null, anasayfaIkon, null, null)
                binding.imageButton2.setColorFilter(Color.BLACK)

            }
        }








    }

}