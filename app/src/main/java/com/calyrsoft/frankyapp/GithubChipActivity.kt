package com.calyrsoft.frankyapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.calyr.data.GihubRepository
import com.calyr.network.GithubRemoteDataSource
import com.calyr.network.RetrofitBuilder
import com.calyrsoft.frankyapp.ui.theme.FrankyAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubChipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrankyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GitUiwithChip(
                        modifier = Modifier.padding(innerPadding),
                        context = LocalContext.current
                    )
                }
            }
        }
    }

}

@Composable
fun GitUiwithChip(modifier: Modifier = Modifier, context: Context) {

    val dataSource: GithubRemoteDataSource = GithubRemoteDataSource(
        RetrofitBuilder
    )
    val accounts = GihubRepository().getUsers()
    var urlImage by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.github_ui_title)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = userId, onValueChange = {
                userId = it

            })
        Button(onClick = {
            urlImage = ""
            val show = Toast.makeText(context, userId, Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.IO).launch {
                val response = dataSource.getAvatarInfo(userId)
                urlImage = response.url
            }

        }) {
            Text(text = stringResource(id = R.string.github_ui_button))
        }
        LazyRow{
            items(accounts.size) {
                SuggestionChip(onClick = {
                                         userId = accounts[it]
                }, label = {
                    Text(text = accounts[it])
                })
            }
        }
        AsyncImage(
            model = urlImage,
            contentDescription = null
        )
    }
}

