package com.example.shreebeautystudio.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.shreebeautystudio.model.ProductModel
import com.example.shreebeautystudio.repository.ProductRepository

class ProductViewModel(val repo:ProductRepository) {
    fun addProduct(productModel: ProductModel,
                   callback: (Boolean,String)->Unit){
        repo.addProduct(productModel,callback)
    }

    fun updateProduct(productId:String,
                      data:MutableMap<String,Any>,
                      callback: (Boolean,String)->Unit){
        repo.updateProduct(productId,data,callback)
    }

    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit){
        repo.deleteProduct(productId,callback)
    }
    //underscore vayesi private
var _products=MutableLiveData<ProductModel>()//private and to acces data  encapsulating by getter
    var products =MutableLiveData<ProductModel>()
        get()=_products  //getter added

    var _allProducts=MutableLiveData<List<ProductModel>>()
    var allProducts=MutableLiveData<List<ProductModel>>()
        get()=_allProducts


    fun getProductById(productId: String){
        repo.getProductById(productId){
            products,success,message->
            if(success){
                _products.value=products
            }
        }
    }
var _loading =MutableLiveData<Boolean>()
var loading =MutableLiveData<Boolean>()
    get() = _loading

    fun getAllProduct() {
        _loading.value=true

        repo.getAllProduct { products, success, message ->
            if (success){
                _allProducts.value=products
                _loading.value=false
            }

        }
    }
    fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit) {
        repo.uploadImage(context, imageUri, callback)
    }

}