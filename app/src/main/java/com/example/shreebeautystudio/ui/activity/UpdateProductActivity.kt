package com.example.shreebeautystudio.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.databinding.ActivityUpdateProductBinding
import com.example.shreebeautystudio.repository.ProductRepositoryImplement
import com.example.shreebeautystudio.viewmodel.ProductViewModel

class UpdateProductActivity : AppCompatActivity() {

    lateinit var  binding: ActivityUpdateProductBinding
   lateinit var productViewModel: ProductViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var repo=ProductRepositoryImplement()
        productViewModel=ProductViewModel(repo)

        var productId:String =intent.getStringExtra(("productId")).toString()


        productViewModel.getProductById(productId)
        productViewModel.products.observe(this) {
            binding.updateProductName.setText(it?.productName.toString())

            binding.editProductPrice.setText(it?.price.toString())
            binding.updateProductDesc.setText(it?.productDes.toString())

        }
        binding.btnUpdateProduct.setOnClickListener{
            var name = binding.updateProductName.text.toString()
            var price = binding.editProductPrice.text.toString().toInt()
            var desc = binding.updateProductDesc.text.toString()
            var updatedMap = mutableMapOf<String,Any>()
            updatedMap["productName"] = name
            updatedMap["productDes"] = desc
            updatedMap["price"] = price

            productViewModel.updateProduct(productId,updatedMap) { success, message ->
                if (success) {
                    Toast.makeText(this@UpdateProductActivity,message,Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@UpdateProductActivity,message,Toast.LENGTH_SHORT).show()

                }
            }


                }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}