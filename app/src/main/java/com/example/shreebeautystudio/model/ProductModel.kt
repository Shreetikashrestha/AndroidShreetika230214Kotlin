package com.example.shreebeautystudio.model

data class ProductModel (
    var productId:String="",
    var productName:String="",
    var productDes:String="",
    var price:Int =0,
    var url:String ="",

){

    val imageUrl: Any
}