package com.okan.ecommerce.domain.models

import java.io.Serializable

data class ProductItem(
    val name : String,
    val id : Int,
    val price : Double,
    val currency : String,
    val imageId : Int
):Serializable
{
    var isFavorite : Boolean = false
}
