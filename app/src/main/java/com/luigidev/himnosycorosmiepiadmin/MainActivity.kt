package com.luigidev.himnosycorosmiepiadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.luigidev.himnosycorosmiepiadmin.navigation.SetupNavGraph
import com.luigidev.himnosycorosmiepiadmin.theme.HimnosYCorosMIEPIAdminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HimnosYCorosMIEPIAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navigationController = rememberNavController()
                    SetupNavGraph(navigationController)
                }
            }
        }
    }
}