package com.luigidev.himnosycorosmiepiadmin.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.home.domain.state.HomeUIState
import com.luigidev.himnosycorosmiepiadmin.home.ui.states.HomeSuccess

@Composable
fun HomeScreen(navigationController: NavHostController) {

    val homeViewModel: HomeViewModel = hiltViewModel()

    when (homeViewModel.homeUIState) {
        is HomeUIState.Error -> {
            Text(text = "Error")
        }

        HomeUIState.Loading -> {
            Text(text = "Loading")
        }

        is HomeUIState.Success -> {
            HomeSuccess(
                navigationController = navigationController,
                homeViewModel
            )
        }
    }


}