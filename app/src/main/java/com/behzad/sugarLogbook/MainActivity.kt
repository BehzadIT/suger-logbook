package com.behzad.sugarLogbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.behzad.sugarLogbook.theme.SugarLogbookTheme

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