package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifpr_biblioteca.Data.RetornaMenu

@Preview
@Composable
fun RenderizaMenu() {
    var expanded by remember { mutableStateOf(false) }
    val menuopcoes = RetornaMenu()

    IconButton(
        modifier = Modifier
            .padding(top = 15.dp)
            .width(70.dp),
        onClick = { expanded = !expanded }) {
        Icon(Icons.Default.Menu, contentDescription = "Mais opções")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        menuopcoes.forEach {
            DropdownMenuItem(
                text = { Text(it.descricao) },
                onClick = { /* Do something... */ }
            )
        }
    }
}