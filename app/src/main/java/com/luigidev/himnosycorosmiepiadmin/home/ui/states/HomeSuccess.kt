package com.luigidev.himnosycorosmiepiadmin.home.ui.states

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.ui.HomeViewModel

@Composable
fun HomeSuccess(
    navigationController: NavHostController,
    homeViewModel: HomeViewModel,
    choirs: List<Choir?>
){
    Column{
        Text(text = "Home screen")
        
        LazyColumn(){
            items(choirs) { choir ->
                if (choir != null) {
                    ChoirItem(choir)
                }
            }
        }


        FloatingActionButton(onClick = { navigationController.navigate(Routes.FormScreen.route) }) {
            Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "add icon")
        }
        Button(onClick = { homeViewModel.getChoirs() }) {
            Text(text = "Get Choirs")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoirItem(choirData: Choir) {
    ListItem(
        headlineText = { Text(text = choirData.title) },
        supportingText = { Text(text = choirData.lyrics) },
        modifier = Modifier.background(Color.Red),
        leadingContent = {
            Icon( Icons.Default.Share, contentDescription = "")
        }
    )
    Divider()
}

