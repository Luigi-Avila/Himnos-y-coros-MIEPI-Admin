package com.luigidev.himnosycorosmiepiadmin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormScreen
import com.luigidev.himnosycorosmiepiadmin.form.ui.FormViewModel
import com.luigidev.himnosycorosmiepiadmin.theme.HimnosYCorosMIEPIAdminTheme

class MainActivity : ComponentActivity() {

    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HimnosYCorosMIEPIAdminTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   FormScreen(formViewModel)
                }
            }
        }
    }
}