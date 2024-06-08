package com.cst.cstacademyunibuc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.cst.cstacademyunibuc.managers.SharedPrefsManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.nav_graph)
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

    fun logout() {
        SharedPrefsManager.removeToken()

//        findNavController(R.id.nav_host_fragment).setGraph(R.navigation.nav_graph_authentication)

        startActivity(Intent(this, MainActivity2::class.java))
        finish()
    }
}