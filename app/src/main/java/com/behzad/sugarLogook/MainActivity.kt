package com.behzad.sugarLogook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.behzad.sugarLogook.theme.SugarLogbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SugarLogbookTheme {
                SugarLogbookAppCompose()
            }
        }
    }
}