package com.example.oneupfarm.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.oneupfarm.R

@Composable
fun LogOutDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 80.dp)
                        .height(6.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFD9BAFF))
                )
                Image(
                    painter = painterResource(R.drawable.sadmascot),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                        .size(120.dp)
                )
                Text(
                    text = "Keluar Akun",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Apakah kamu yakin ingin keluar akun?",
                    fontSize = 13.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Button(
                        onClick = { onDismissRequest() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD0ACFF),
                            contentColor = Color(0xFF7C19B9)
                        ),
                        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 20.dp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "Batal",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Button(
                        onClick = {/*LOG OUT*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7C19B9),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(vertical = 4.dp, horizontal = 20.dp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = "Keluar",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}