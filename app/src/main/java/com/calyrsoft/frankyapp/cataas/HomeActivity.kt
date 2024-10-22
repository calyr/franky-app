package com.calyrsoft.frankyapp.cataas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Observer
import com.calyr.core.model.Cat
import com.calyrsoft.frankyapp.ui.theme.AppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                ContentUI()
            }
        }
    }
}

@Composable
fun ContentUI() {
    var cats by remember { mutableStateOf(listOf<Cat>()) }

    val catsViewModel = HomeViewModel()

    catsViewModel.loadCats()

    fun updateUI(homeUIState: HomeViewModel.HomeUIState?) {
        when ( homeUIState) {
            is HomeViewModel.HomeUIState.Loaded -> {
                cats = homeUIState.list
            }
            else -> {
                ///nothing yet
            }
        }
    }
    catsViewModel.state.observe(LocalLifecycleOwner.current, Observer(::updateUI))

    Scaffold {
        paddingValues -> LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
        ) { items(cats.size) {
                Text(
                    text = cats[it].id
                )
            }

        }
    }
}

