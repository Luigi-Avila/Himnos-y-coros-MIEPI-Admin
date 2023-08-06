package com.luigidev.himnosycorosmiepiadmin.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.domain.state.HomeUIState
import com.luigidev.himnosycorosmiepiadmin.home.ui.states.HomeSuccess

@Composable
fun HomeScreen(navigationController: NavHostController) {

    val homeViewModel: HomeViewModel = viewModel()

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
                homeViewModel = homeViewModel,
                (homeViewModel.homeUIState as HomeUIState.Success<List<Choir?>>).data
            )
        }
    }


}