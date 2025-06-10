package com.example.afinal
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import com.example.afinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val TAG_CYKL_ZYCIA = "MainActivityCyklZycia"

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        Log.d(TAG_CYKL_ZYCIA, "onCreate wywołane")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.menuFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_CYKL_ZYCIA, "onStart wywołane")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_CYKL_ZYCIA, "onResume wywołane")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_CYKL_ZYCIA, "onPause wywołane")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_CYKL_ZYCIA, "onStop wywołane")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG_CYKL_ZYCIA, "onRestart wywołane")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_CYKL_ZYCIA, "onDestroy wywołane")
    }
}