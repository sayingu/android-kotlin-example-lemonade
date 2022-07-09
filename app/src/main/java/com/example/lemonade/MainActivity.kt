package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    app()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun app() {
    var step by remember { mutableStateOf(1) }
    var lemonTapped by remember { mutableStateOf(0) }
    Step(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        step = step, onStepChange = { step = it },
        lemonTapped = lemonTapped, onLemonTappedChange = { lemonTapped = it },
    )
}

@Composable
fun Step(
    modifier: Modifier = Modifier,
    step: Int,
    onStepChange: (Int) -> Unit,
    lemonTapped: Int,
    onLemonTappedChange: (Int) -> Unit,
) {
    fun getLemonString(): Int {
        return when (step) {
            1 -> R.string.lemon_tree
            2 -> R.string.lemon_sueeze
            3 -> R.string.lemon_drink
            4 -> R.string.lemon_restart
            else -> R.string.lemon_tree
        }
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = stringResource(getLemonString()),
            fontSize = 18.sp,
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        Image(
            painter = painterResource(
                id = when (step) {
                    1 -> R.drawable.lemon_tree
                    2 -> R.drawable.lemon_squeeze
                    3 -> R.drawable.lemon_drink
                    4 -> R.drawable.lemon_restart
                    else -> R.drawable.lemon_tree
                }
            ),
            contentDescription = stringResource(getLemonString()),
            modifier = Modifier
                .border(2.dp, Color(105, 205, 216))
                .wrapContentSize()
                .clickable {
                    when (step) {
                        1 -> {
                            onLemonTappedChange((2..4).random())
                            onStepChange(2)
                        }
                        2 -> {
                            onLemonTappedChange(lemonTapped - 1)
                            if (lemonTapped == 1)
                                onStepChange(3)
                        }
                        4 -> {
                            onStepChange(1)
                        }
                        else -> onStepChange(step + 1)
                    }
                },
        )
    }
}