package com.behzad.sugarLogook.features.bloodGlucose

import android.widget.Toast
import androidx.activity.compose.ReportDrawn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.behzad.sugarLogook.R
import com.behzad.sugarLogook.features.bloodGlucose.data.entries.BloodGlucoseEntry
import com.behzad.sugarLogook.features.bloodGlucose.data.units.GlucoseUnit
import com.behzad.sugarLogook.features.shared.LoadableData
import com.behzad.sugarLogook.features.shared.getErrorMessage
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: UserSearchViewModel = koinViewModel(),
    navController: NavController
) {
    val level by viewModel.level.collectAsState()
    val entriesResult by viewModel.entriesResult.collectAsState()
    val unit by viewModel.unit.collectAsState()
    val averageResult by viewModel.average.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        when (val result = averageResult) {
            is LoadableData.Loaded -> {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = stringResource(
                        R.string.average_result,
                        result.data,
                        stringResource(unit.unitTextRes)
                    )
                )
                Divider(modifier = Modifier.padding(16.dp))
            }

            else -> {}
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            TextField(label = { Text(text = stringResource(id = R.string.entry_input_hint)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                suffix = { Text(stringResource(id = unit.unitTextRes)) },
                modifier = Modifier
                    .weight(2f)
                    .padding(16.dp),
                value = level?.toString().orEmpty(),
                onValueChange = { text ->
                    viewModel.setLevel(text.toFloatOrNull())
                })
            RadioButtonVertical(Modifier.weight(1f), unit) {
                viewModel.setUnit(it)
            }

        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), onClick = {
            viewModel.addNewEntry()
        }) {
            Text(text = "Save")
            Icon(
                modifier = Modifier.padding(8.dp),
                imageVector = Icons.Default.Check,
                contentDescription = "Add"
            )
        }

        if (entriesResult.data?.isEmpty() == true) InvalidSearchQuery(modifier)
        else {
            ResultsForValidSearchQuery(
                entriesResult, navController, modifier.align(CenterHorizontally)
            )
        }
    }
}

@Composable
private fun ResultsForValidSearchQuery(
    result: LoadableData<List<BloodGlucoseEntry>>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    when (result) {
        is LoadableData.Failed -> {
            val errorMessage = result.exception.getErrorMessage(LocalContext.current)
            ToastMessage(errorMessage)
        }

        is LoadableData.Loaded -> {
            if (result.data.isEmpty()) {
                EmptyResults()
            } else {
                EntriesList(result.data, modifier)
            }
        }

        is LoadableData.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .padding(top = 64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

        LoadableData.NotLoaded -> {}
    }
}

@Composable
fun ToastMessage(toastMessage: String) {
    val context = LocalContext.current
    LaunchedEffect(toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
    }
}

@Composable
private fun EntriesList(
    users: List<BloodGlucoseEntry>, modifier: Modifier = Modifier
) {
    LazyColumn {
        items(users) { user ->
            GlucoseEntryRow(user, modifier)
        }
    }
}

@Composable
private fun InvalidSearchQuery(modifier: Modifier = Modifier) {
    // Calls reportFullyDrawn when this composable is composed.
    ReportDrawn()

    Column(
        modifier, horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.initial_search_screen_placeholder),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
private fun EmptyResults(modifier: Modifier = Modifier) {
    Column(
        modifier, horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_search_results_placeholder),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun RadioButtonVertical(
    modifier: Modifier,
    selectedGlucoseUnit: GlucoseUnit,
    onOptionSelect: (glucoseUnit: GlucoseUnit) -> Unit
) {
    Column(modifier, verticalArrangement = Arrangement.Center) {
        GlucoseUnit.values().forEach { item ->
            Row(
                modifier = Modifier
                    .selectable(selected = item == selectedGlucoseUnit, onClick = {
                        onOptionSelect(item)
                    })
                    .padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(modifier = Modifier.height(35.dp),
                    selected = item == selectedGlucoseUnit,
                    onClick = { onOptionSelect(item) })
                Text(
                    text = stringResource(id = item.unitTextRes),
                    style = MaterialTheme.typography.bodySmall.merge()
                )
            }
        }
    }
}
