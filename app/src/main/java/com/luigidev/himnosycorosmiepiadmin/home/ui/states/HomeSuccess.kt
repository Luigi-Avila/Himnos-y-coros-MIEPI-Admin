package com.luigidev.himnosycorosmiepiadmin.home.ui.states

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.luigidev.himnosycorosmiepiadmin.R
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir
import com.luigidev.himnosycorosmiepiadmin.home.ui.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuccess(
    navigationController: NavHostController,
    homeViewModel: HomeViewModel
) {
    val choirs by homeViewModel.choirs.collectAsState()
    val searchText by homeViewModel.searchText.collectAsState()

    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }
    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarScope = rememberCoroutineScope()

    val scrollBehavior = TopAppBarDefaults
        .exitUntilCollapsedScrollBehavior(rememberTopAppBarState())


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Choirs", style = MaterialTheme.typography.headlineLarge) },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navigationController.navigate(Routes.FormScreen.route) },
                expanded = expandedFab,
                icon = { Icon(Icons.Filled.Add, "Localized Description") },
                text = { Text(text = "Add Choir") },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->

        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (choirs.isEmpty() && searchText.isBlank()) {
                Text(text = "There is no choirs")
            } else {
                ChoirList(
                    homeViewModel,
                    searchText,
                    choirs,
                    listState,
                    navigationController,
                    snackBarScope,
                    snackBarHostState
                )
            }
        }


//        LazyColumn(
//            state = listState,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            if (choirs.isEmpty() && searchText.isBlank()) {
//                item {
//                    Column(Modifier.fillMaxSize().background(Color.Blue)) {
//                        Text(text = "Not choirs added")
//                        Image(
//                            painter = painterResource(id = R.drawable.without_image),
//                            contentDescription = ""
//                        )
//                    }
//                }
//            } else {
//                item { MySearchBar(homeViewModel, searchText) }
//                if (searchText.isNotBlank() && choirs.isEmpty()) {
//                    item { ResultsNotFound(searchText) }
//                } else {
//                    items(choirs) { choir ->
//                        ChoirItem(
//                            choir,
//                            navigationController,
//                            homeViewModel,
//                            snackBarScope,
//                            snackBarHostState
//                        )
//                    }
//                }
//            }
//
//        }
    }

}

@Composable
fun ChoirList(
    homeViewModel: HomeViewModel,
    searchText: String,
    choirs: List<Choir>,
    listState: LazyListState,
    navigationController: NavHostController,
    snackBarScope: CoroutineScope,
    snackBarHostState: SnackbarHostState,
) {
    MySearchBar(homeViewModel, searchText)
    if (searchText.isNotBlank() && choirs.isEmpty()) {
        ResultsNotFound(searchText)
    } else {
        LazyColumn(state = listState) {
            items(choirs) { choir ->
                ChoirItem(
                    choirData = choir,
                    navigationController = navigationController,
                    homeViewModel = homeViewModel,
                    snackBarScope = snackBarScope,
                    snackBarHostState = snackBarHostState
                )
            }
        }
    }
}


@Composable
fun ResultsNotFound(searchText: String) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.common_default_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No results for $searchText")
        Image(
            painter = painterResource(id = R.drawable.without_image),
            contentDescription = ""
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    homeViewModel: HomeViewModel,
    searchText: String
) {
    OutlinedTextField(
        value = searchText,
        onValueChange = homeViewModel::onSearchTextChanged,
        label = { Text(text = "Search Choirs") },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "") },
        trailingIcon = {
            if (searchText.length > 3) {
                IconButton(onClick = { homeViewModel.clearSearchText() }) {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoirItem(
    choirData: Choir,
    navigationController: NavHostController,
    homeViewModel: HomeViewModel,
    snackBarScope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    ListItem(
        headlineText = {
            Text(
                text = choirData.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        supportingText = {
            Text(
                text = choirData.lyrics,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingContent = {
            if (choirData.thumbnail != null) {
                AsyncImage(
                    model = choirData.thumbnail,
                    contentDescription = "",
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    modifier = Modifier.size(80.dp),
                    painter = painterResource(id = R.drawable.without_image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

            }

        },
        trailingContent = {
            Menu(navigationController, choirData, homeViewModel, snackBarScope, snackBarHostState)
        }
    )
}

@Composable
fun Menu(
    navigationController: NavHostController,
    choirData: Choir,
    homeViewModel: HomeViewModel,
    snackBarScope: CoroutineScope,
    snackBarHostState: SnackbarHostState
) {
    var expanded by remember { mutableStateOf(false) }
    var showAlert by remember { mutableStateOf(false) }
    TextButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            text = { Text(text = "View") },
            onClick = { /*TODO*/ },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.ViewAgenda,
                    contentDescription = ""
                )
            })
        DropdownMenuItem(
            text = { Text(text = "Edit") },
            onClick = { navigationController.navigate(Routes.FormScreen.createRoute(choirData.id)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = ""
                )
            })
        DropdownMenuItem(
            text = { Text(text = "Delete") },
            onClick = { showAlert = true },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = ""
                )
            })
    }
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = {
                Button(onClick = {
                    showAlert = false
                    homeViewModel.deleteChoir(choirData) { result ->
                        var message = "Something went wrong"
                        if (result) {
                            message = "Choir deleted successfully"
                        }
                        snackBarScope.launch {
                            snackBarHostState.showSnackbar(
                                message
                            )
                        }

                    }
                }) {
                    Text(text = "Remove")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text(text = "Cancel")
                }
            },
            icon = { Icon(imageVector = Icons.Outlined.Delete, contentDescription = "") },
            title = { Text(text = "Permanently delete?") },
            text = { Text(text = "If you remove this you cannot see it anymore") }
        )
    }

}


