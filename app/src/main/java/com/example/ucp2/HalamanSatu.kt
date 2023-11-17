package com.example.ucp2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.data.SumberData.dosen
import com.example.ucp2.ui.theme.UCP2Theme
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.flow.internal.NoOpContinuation.context

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanSatu(

) {
    var namaText by remember {
        mutableStateOf("")
    }

    var NIMText by remember {
        mutableStateOf("")
    }
    var konsenText by remember {
        mutableStateOf("")
    }
    var JudulText by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Formulir Pengajuan Skripsi", fontWeight = FontWeight.Bold, fontSize = 25.sp)
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = namaText,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.name)) },
                onValueChange = { namaText = it }
            )
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = NIMText,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.nim)) },
                onValueChange = { NIMText = it }
            )
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = konsenText,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.kons)) },
                onValueChange = { konsenText = it }
            )
            Spacer(modifier = Modifier.padding(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = JudulText,
                shape = MaterialTheme.shapes.large,
                label = { Text(text = stringResource(id = R.string.judul)) },
                onValueChange = { JudulText = it }
            )
            Spacer(modifier = Modifier.padding(10.dp))

        }
    }
    Column {
        SelectDSN(
            options = dosen.map{id -> context.resources.getString(id)},
            onSelectionChanged = {ViewModel.setJenisK(it)})
    }

}
@Composable
fun SelectDSN(
    options: List<String>,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("")}
    Column {
        Text(text = "Dosen Pembimbing 1")
        Row(modifier = Modifier.padding(16.dp)) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun SatuPreview() {
    UCP2Theme {
        HalamanSatu()
    }
}