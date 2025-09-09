package com.example.ifpr_biblioteca.View
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ifpr_biblioteca.Data.Dt_Book
import com.example.ifpr_biblioteca.Model.Viewmodel.ViewModel_Inventario
import com.example.ifpr_biblioteca.R
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Preview
@Composable
fun BookListScreen() {

    var texto by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            item {
                Row {
                    RenderizaMenu1()
                    OutlinedTextField(
                        modifier = Modifier
                            .width(320.dp),
                        value = texto,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "")
                        },
                        onValueChange = {
                            texto = it
                        },
                        label = { Text("Informe o nome do Livro:") }
                    )
                }
            }
        }

        var codigo_barra: String by remember { mutableStateOf("") }
        val launcher = rememberLauncherForActivityResult(
            contract = ScanContract(),
            onResult = { result ->
                if (result.contents != null) {
                    codigo_barra = result.contents.replace("0", "", true)
                }
            }
        )

        val books = listOf<Dt_Book>(
            Dt_Book(
                CODIGO_BARRA = codigo_barra
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxSize()
                    .padding(bottom = 30.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Inventário de Livros",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(bottom = 12.dp, start = 50.dp, end = 50.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .width(250.dp)
                            .padding(start = 30.dp, top = 20.dp)
                    ) {
                        IconButton(
                            onClick = {
                                val options = ScanOptions()
                                options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
                                options.setPrompt("Escaneie o QR Code ou código de barras")
                                options.setBeepEnabled(true)
                                options.setOrientationLocked(false)
                                launcher.launch(options)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.camera_icon),
                                contentDescription = "Favorite icon"
                            )
                        }

                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp, start = 10.dp),
                            text = "Escanear QR Code",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }

                val item = listOf<Dt_Book>(
                    Dt_Book(
                        CODIGO_BARRA = "315601"
                    )
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(item) { book ->
                        if (book.IDBOOK > 0) {
                            BookCard(books)
                        }
                    }
                }
            }

            Button(
                onClick = {

                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .width(150.dp)
                    .padding(16.dp)
            ) {
                Text("Finalizar")
            }
        }
    }
}

@Composable
fun BookCard(book: List<Dt_Book>) {
    var quantidade by remember { mutableIntStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = book.firstOrNull()?.TITULO ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(text = "Autor: ${book.firstOrNull()?.AUTOR}")
            Text(text = "ISBN: ${book.firstOrNull()?.ISBN}")
            Text(text = "Categoria: ${book.firstOrNull()?.IDCATEGORIA}")

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Quantidade:", fontWeight = FontWeight.Medium)

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { if (quantidade > 0) quantidade-- }) {
                        Icon(Icons.Default.Add, contentDescription = "Diminuir")
                    }
                    Text(
                        text = quantidade.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    IconButton(onClick = { quantidade++ }) {
                        Icon(Icons.Default.Add, contentDescription = "Aumentar")
                    }
                }
            }
        }
    }
}



