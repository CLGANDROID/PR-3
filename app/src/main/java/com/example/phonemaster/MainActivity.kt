package com.example.phonemaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonemaster.ui.theme.PhoneMasterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PhoneMasterTheme() {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Dashboard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }

            }
        }
    }
}

@Composable
fun Dashboard(modifier: Modifier = Modifier) {

    val stage = remember { mutableIntStateOf(1) }

    val stageText = when (stage.intValue) {
        1 -> stringResource(R.string.stage_body)
        2 -> stringResource(R.string.stage_screen)
        3 -> stringResource(R.string.stage_battery)
        4 -> stringResource(R.string.stage_camera)
        5 -> stringResource(R.string.stage_complete)
        else -> stringResource(R.string.stage_body)
    }

    val imageResource = when (stage.intValue) {
        1 -> R.drawable.phone_body
        2 -> R.drawable.phone_screen
        3 -> R.drawable.phone_battery
        4 -> R.drawable.phone_camera
        5 -> R.drawable.phone_complete
        else -> R.drawable.phone_body
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.phone_master_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = stageText,
            fontSize = 18.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stageText,
            modifier = Modifier.size(250.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {
                if (stage.intValue < 5) {
                    stage.intValue += 1
                } else {
                    stage.intValue = 1
                }
            },
            modifier = Modifier.size(width = 300.dp, height = 50.dp)
        ) {
            Text(
                text = when (stage.intValue) {
                    1 -> stringResource(R.string.button_install_screen)
                    2 -> stringResource(R.string.button_install_battery)
                    3 -> stringResource(R.string.button_install_camera)
                    4 -> stringResource(R.string.button_build_phone)
                    5 -> stringResource(R.string.button_restart)
                    else -> stringResource(R.string.button_install_screen)
                },
                fontSize = 16.sp
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    PhoneMasterTheme {
        Dashboard()
    }
}