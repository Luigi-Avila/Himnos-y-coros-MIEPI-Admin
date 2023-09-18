package com.luigidev.himnosycorosmiepiadmin.home.ui.states

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.luigidev.himnosycorosmiepiadmin.core.Routes
import com.luigidev.himnosycorosmiepiadmin.core.Title
import com.luigidev.himnosycorosmiepiadmin.home.domain.models.Choir

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSuccess(
    navigationController: NavHostController,
    choirs: List<Choir?>
) {
    val listState = rememberLazyListState()
    val expandedFab by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex == 0
        }
    }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navigationController.navigate(Routes.FormScreen.route) },
                expanded = expandedFab,
                icon = { Icon(Icons.Filled.Add, "Localized Description") },
                text = { Text(text = "Add Choir") },
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        LazyColumn(
            state = listState, modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item { Title(textTitle = "Choirs") }
            items(choirs) { choir ->
                if (choir != null) {
                    ChoirItem(choir)
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoirItem(choirData: Choir) {
//    ListItem(
//        headlineText = { Text(text = choirData.title) },
//        supportingText = { Text(text = choirData.lyrics, maxLines = 2, overflow = TextOverflow.Ellipsis) },
//        modifier = Modifier.background(Color.Red),
//        leadingContent = {
//            Icon(Icons.Outlined.Description, contentDescription = "")
//        }
//    )
//    Divider()
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
            AsyncImage(
                model = "https://i3.ytimg.com/vi/Yt_SPQFbHj4/maxresdefault.jpg",
                contentDescription = "",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
        },
        trailingContent = {
            TextButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
            }
        }
    )
}

