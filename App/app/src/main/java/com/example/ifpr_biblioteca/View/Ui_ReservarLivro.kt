package com.example.ifpr_biblioteca.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Thinking in Java"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Thinking in Java"
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Categoria"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Programing in java"
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "ISBN"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "4479895645"
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Country"
                    )

                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                    )

                    Text(
                        modifier = Modifier
                            .width(180.dp),
                        text = "Brazil"
                    )
                }
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