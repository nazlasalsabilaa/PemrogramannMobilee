package com.nazlasalsabila.recyclerview

data class ItemModel(
    val id: Int,
    val title: String,
    val description: String,
    val imageResId: Int,
    var isSwitchOn: Boolean = false
)