package com.example.therapet.app.nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.therapet.R
import com.example.therapet.app.data.util.sounds.SoundManager
import com.example.therapet.app.ui.theme.TheraPetTheme

/**
 * @author: Chloe Edwards
 * @date: 24/12/2025
 *
 * Main activity utilising single activity architecture. Uses NavGraph and Routes to navigate through screens
 */

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Loading all sounds
        SoundManager.loadSound(this, "click", R.raw.click)

        setContent {
            TheraPetTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

