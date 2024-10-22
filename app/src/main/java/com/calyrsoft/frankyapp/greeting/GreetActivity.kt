package com.calyrsoft.frankyapp.greeting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calyrsoft.frankyapp.R
import com.calyrsoft.frankyapp.ui.theme.AppTheme

class GreetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                GreetUI()
            }
        }
    }
}

@Composable
fun GreetUI() {
    val name = remember { mutableStateOf("") }
    val nameEntered = remember { mutableStateOf(false) }

    Scaffold {
        paddingContent ->
            Column( modifier = Modifier.padding(paddingContent).fillMaxSize()) {
                Text(
                    text = stringResource(id = R.string.greeting_welcome)
                )
                TextAndButton(name, nameEntered)
            }
    }
}

@Composable
fun TextAndButton(name: MutableState<String>,
                  namedEntered: MutableState<Boolean>) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        TextField(
            modifier = Modifier
                .alignByBaseline().weight(1.0F),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(
                onAny = { namedEntered.value = true}
            ),
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.greeting_hint)
                )
            },

        )
        Button(modifier = Modifier
            .alignByBaseline().padding(8.dp), onClick = {
                namedEntered.value = true
        }) {
            Text(text = stringResource(id = R.string.greeting_btn))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    AppTheme {
        GreetUI()
    }
}