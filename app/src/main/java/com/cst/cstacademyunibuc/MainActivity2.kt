package com.cst.cstacademyunibuc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.cst.cstacademyunibuc.managers.SharedPrefsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {

    private lateinit var splashScreen: SplashScreen
    private var isAppInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setupSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            setupNavigation()
        }
    }

    private fun setupNavigation() {
        SharedPrefsManager.readToken()?.let {
            navigateToProducts()
        }

        isAppInit = true
    }

    private fun navigateToProducts() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.productListFragment)
    }

    private fun setupSplashScreen() {
        splashScreen = installSplashScreen().apply {
            // Behaves like observable, used to check if splash screen should be keep or not
            setKeepOnScreenCondition {
                !isAppInit
            }
            setOnExitAnimationListener { sp ->
                sp.remove() // Remove splash screen
            }
        }
    }
}