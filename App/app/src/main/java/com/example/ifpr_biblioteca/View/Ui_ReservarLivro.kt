package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ifpr_biblioteca.R

@Preview
@Composable
fun ReservarLivro() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
        ) {
            item {
                Row {
                    Image(
                        modifier = Modifier.size(150.dp),
                        painter = painterResource(id = R.drawable.cover_empty_), contentDescription = ""
                    )
                    Column {
                        Text(text = "O que é ética", fontWeight = FontWeight.W700)
                        Text(text = "Alvaro L. M. valls", fontWeight = FontWeight.W400)
                        Text(text = "Data 25 Julho de 2005", fontWeight = FontWeight.W400)
                    }
                }
            }
            
            item {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 15.dp),
                    text = "Descrição:", fontWeight = FontWeight.W600
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    fontWeight = FontWeight.W400,
                    text = "Não existe povo ou lugar que não tenha noções de bem e mal, de certo e errado. Da Grécia Antiga aos nossos dias, a ética é um conceito que sempre esteve presente em todas as sociedades. Mas apesar disso, as dúvidas são muitas. Seria a ética apenas um conjunto de convenções sociais? Teria ela um princípio supremo que atravessa toda a história da humanidade? E numa sociedade capitalista, qual a relação entre ética e lucro?"
                )
            }
        }

        Button(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter)
                .width(180.dp),
            onClick = {}
        ) {
            Text("Reservar:", fontWeight = FontWeight.W700)
        }
    }
}