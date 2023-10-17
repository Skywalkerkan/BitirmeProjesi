package com.example.bitirmeprojesi

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

      //  val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController





        binding.button2.setOnClickListener {
            val navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
            if (navController.currentDestination != null && navController.currentDestination!!.id != R.id.anasayfaFragment2) {

                navController.popBackStack()
             //   binding.button.setBackgroundColor(resources.getColor(R.color.purple, null))
             //   binding.button2.setBackgroundColor(resources.getColor(R.color.orange, null))
                sayi = 0
            }
        }

           /* binding.button.setOnClickListener {
                if (sayi != 1) {


                    val navController =
                        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
                    val currentDestination = navController.currentDestination
                    if (currentDestination != null && currentDestination.id != R.id.sepetGecis) {
                        navController.navigate(R.id.sepetGecis)
                        //    binding.button.setBackgroundColor(resources.getColor(R.color.orange, null))
                        //     binding.button2.setBackgroundColor(resources.getColor(R.color.purple, null))

                        Log.e("Mesaj", "$sayi")
                        sayi = 1
                    }
                }
            }*/


        binding.imageButton2.setOnClickListener {
           /* if (sayi != 1) {


                val navController =
                    (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).navController
                val currentDestination = navController.currentDestination
                if (currentDestination != null && currentDestination.id != R.id.sepetGecis) {
                    navController.navigate(R.id.sepetGecis)
                    //    binding.button.setBackgroundColor(resources.getColor(R.color.orange, null))
                    //     binding.button2.setBackgroundColor(resources.getColor(R.color.purple, null))

                    Log.e("Mesaj", "$sayi")
                    sayi = 1
                }


                else if (currentDestination != null && currentDestination.id != R.id.detay_Gecis) {
                    navController.navigate(R.id.sepetGecis2)
                    //    binding.button.setBackgroundColor(resources.getColor(R.color.orange, null))
                    //     binding.button2.setBackgroundColor(resources.getColor(R.color.purple, null))

                    Log.e("Mesaj", "$sayi")
                    sayi = 1
                }
            }*/
            val navController = findNavController(R.id.navHostFragment)


            val currentDestination = navController.currentDestination

            if (currentDestination?.id != R.id.sepetFragment) {
                // Geçerli fragment, sepetFragment değilse, sepetFragment'a git
                navController.navigate(R.id.sepetFragment)
            }
        }








    }

}