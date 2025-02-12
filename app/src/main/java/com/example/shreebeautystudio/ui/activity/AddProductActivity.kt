package com.example.shreebeautystudio.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.databinding.ActivityAddProduct1Binding
import com.example.shreebeautystudio.databinding.ActivityProductDashboardBinding
import com.example.shreebeautystudio.model.ProductModel
import com.example.shreebeautystudio.repository.ProductRepositoryImplement
import com.example.shreebeautystudio.utils.ImageUtils
import com.example.shreebeautystudio.utils.LoadingUtils
import com.example.shreebeautystudio.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class AddProductActivity : AppCompatActivity() {

lateinit var binding: ActivityAddProduct1Binding

lateinit var productViewModel: ProductViewModel
lateinit var loadingUtils: LoadingUtils

lateinit var imageUtils: ImageUtils
var imageUri:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding=ActivityAddProduct1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        imageUtils=ImageUtils(this)


        loadingUtils= LoadingUtils(this)
        var repo = ProductRepositoryImplement()
        productViewModel=ProductViewModel(repo)

        imageUtils.registerActivity { url ->
            url.let { it ->
                imageUri = it
                Picasso.get().load(it).into(binding.imageBrowse)
            }
        }
        binding.imageBrowse.setOnClickListener {
            imageUtils.launchGallery(this)
        }
        binding.btnAddProduct.setOnClickListener {
            uploadImage()

        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun uploadImage() {
        loadingUtils.show()
        imageUri?.let { uri ->
            productViewModel.uploadImage(this, uri) { imageUrl ->
                Log.d("checpoirs", imageUrl.toString())
                if (imageUrl != null) {
                    addProduct(imageUrl)
                } else {
                    Log.e("Upload Error", "Failed to upload image to Cloudinary")
                }
            }
        }

    }
        private fun addProduct(imageUrl: String) {
            val name=binding.editProductName.text.toString()
            val price=binding.editProductPrice.text.toString().toInt()
            val desc=binding.editProductDesc.text.toString()

            var model=ProductModel("",name,desc,price,imageUrl)
            //firebase ma pathaune

            productViewModel.addProduct(model){
                    success,message ->
                if(success){
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_SHORT).show()
                    loadingUtils.dismiss()
                    finish()
                }else{
                    Toast.makeText(this@AddProductActivity,
                        message,Toast.LENGTH_SHORT).show()
                    loadingUtils.dismiss()
                }
            }
        }

    }