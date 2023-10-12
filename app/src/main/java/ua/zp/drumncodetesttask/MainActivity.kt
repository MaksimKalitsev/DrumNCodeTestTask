package ua.zp.drumncodetesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import ua.zp.drumncodetesttask.presentation.Navigation
import ua.zp.drumncodetesttask.presentation.ui.theme.DrumNCodeTestTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrumNCodeTestTaskTheme {
                Surface {
                    Navigation()
                }
            }
        }
    }
}

