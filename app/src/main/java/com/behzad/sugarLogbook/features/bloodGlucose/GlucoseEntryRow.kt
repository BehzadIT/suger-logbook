package com.behzad.sugarLogbook.features.bloodGlucose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogbook.features.bloodGlucose.data.entries.toReadableDate

@Composable
internal fun GlucoseEntryRow(
    entry: BloodGlucoseEntry, modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            Text(text = stringResource(entry.unit.unitValueTextFormatRes, entry.level))
            entry.createdTime.toReadableDate()
                ?.let { Text(modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.bodySmall, text = it) }
        }
    }
}