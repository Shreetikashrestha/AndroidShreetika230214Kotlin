package com.example.shreebeautystudio.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.adapter.ProductAdapter
import com.example.shreebeautystudio.databinding.ActivityProductDashboardBinding
import com.example.shreebeautystudio.repository.ProductRepositoryImplement
import com.example.shreebeautystudio.viewmodel.ProductViewModel
import java.util.ArrayList

class ProductDashboardActivity : AppCompatActivity() {


    lateinit var binding: ActivityProductDashboardBinding
    lateinit var  productViewModel: ProductViewModel
    lateinit var adapter: ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityProductDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo=ProductRepositoryImplement()
        productViewModel= ProductViewModel(repo)

        adapter= ProductAdapter(this@ProductDashboardActivity, ArrayList())


        productViewModel.getAllProduct()
        productViewModel.allProducts.observe(this){
            it?.let {
                adapter.updateData(it)
            }

        }
        productViewModel.loading.observe(this){loading->
            if(loading) {//true
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility=View.GONE

            }
        }
        binding.recyclerView.adapter=adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this) //GridLayoutManager(row ma kati data halni


        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(
                this@ProductDashboardActivity,
                AddProductActivity::class.java
            )
            startActivity(intent)

        }
        ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var productId=adapter.getProductId(viewHolder.adapterPosition)
                productViewModel.deleteProduct(productId){
                    success,message->
                    if(success){
                        Toast.makeText(this@ProductDashboardActivity,message,Toast.LENGTH_SHORT)
                    }else{
                        Toast.makeText(this@ProductDashboardActivity,message,Toast.LENGTH_SHORT)
                    }
                }
            }

        }).attachToRecyclerView(binding.recyclerView)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}