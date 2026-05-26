package com.nazlasalsabila.lazylist.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

data class ItemModelCompose(
    val title: String,
    val description: String,
    var isSwitchOn: Boolean
)

@Composable
fun LatihanLazyListScreen() {
    val itemList = remember {
        mutableStateListOf(
            ItemModelCompose("Item Pertama", "Deskripsi item kesatu", false),
            ItemModelCompose("Item Kedua", "Deskripsi item kedua", true),
            ItemModelCompose("Item Ketiga", "Deskripsi item ketiga", false),
            ItemModelCompose("Item Keempat", "Deskripsi item keempat", false),
            ItemModelCompose("Item Kelima", "Deskripsi item kelima", true)
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = Dp(8f)),
        verticalArrangement = Arrangement.spacedBy(Dp(8f))
    ) {
        itemsIndexed(itemList) { position, item ->
            ItemRowCompose(position = position, item = item)
        }
    }
}

@Composable
fun ItemRowCompose(position: Int, item: ItemModelCompose) {
    val context = LocalContext.current
    val itemNumber = position + 1
    val itemTitleLower = item.title.lowercase()

    val warnaHijauMantanXML = Color(android.graphics.Color.parseColor("#EAEFDB"))
    val warnaTombolUngu = Color(android.graphics.Color.parseColor("#5E35B1"))
    val backgroundColor = if (position == 1 || position == 3) warnaHijauMantanXML else Color.White

    var switchState by remember { mutableStateOf(item.isSwitchOn) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dp(16f)),
        shape = RoundedCornerShape(Dp(16f)),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = Dp(0f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dp(16f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(Dp(60f))
                    .background(warnaHijauMantanXML, shape = RoundedCornerShape(Dp(12f))),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Gambar\n$itemNumber",
                    fontSize = TextUnit(11f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#333333")),
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Dp(16f))
            ) {
                Text(
                    text = item.title,
                    fontSize = TextUnit(15f, TextUnitType.Sp),
                    fontWeight = FontWeight.Bold,
                    color = Color(android.graphics.Color.parseColor("#222222"))
                )
                Text(
                    text = "Deskripsi item ${itemTitleLower.substringAfter(" ")}",
                    fontSize = TextUnit(13f, TextUnitType.Sp),
                    color = Color(android.graphics.Color.parseColor("#666666")),
                    modifier = Modifier.padding(top = Dp(4f))
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Switch(
                    checked = switchState,
                    onCheckedChange = { isChecked ->
                        switchState = isChecked
                        item.isSwitchOn = isChecked
                        if (isChecked) {
                            Toast.makeText(
                                context,
                                "Switch hidup pada $itemTitleLower",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.padding(bottom = Dp(4f))
                )

                Button(
                    onClick = {
                        Toast.makeText(
                            context,
                            "Tombol telah ditekan untuk tombol $itemTitleLower",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    modifier = Modifier
                        .width(Dp(64f))
                        .height(Dp(36f)),
                    contentPadding = PaddingValues(Dp(0f)),
                    colors = ButtonDefaults.buttonColors(containerColor = warnaTombolUngu),
                    shape = RoundedCornerShape(Dp(14f)),
                ) {
                    Text(
                        text = "Aksi",
                        fontSize = TextUnit(14f, TextUnitType.Sp),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}