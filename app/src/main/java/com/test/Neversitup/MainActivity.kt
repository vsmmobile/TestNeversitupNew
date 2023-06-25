package com.test.Neversitup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.test.Neversitup.common.Logger
import com.test.Neversitup.connectApi.ModelResponce.currentprice.Bpi
import com.test.Neversitup.connectApi.ModelResponce.currentprice.ResponceCurrentprice
import com.test.Neversitup.connectApi.ModelResponce.currentprice.USD
import com.test.Neversitup.connectApi.NetworkClientViewModel
import com.test.Neversitup.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import or.th.mobile.librarymobile.common.GobalValue

class MainActivity : FunMainActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModelNetworkClient: NetworkClientViewModel
    private lateinit var viewModelMain: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        GobalValue.connectDb!!.createDatabase()

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_fibonacci, R.id.navigation_primeNumber,R.id.navigation_filterArray
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewModelNetworkClient = ViewModelProvider(this).get(NetworkClientViewModel::class.java)
        viewModelMain = ViewModelProvider(this).get(MainViewModel::class.java)

    }



}