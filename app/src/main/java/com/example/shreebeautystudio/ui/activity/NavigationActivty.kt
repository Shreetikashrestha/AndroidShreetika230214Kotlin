//package com.example.shreebeautystudio.ui.activity
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.fragment.app.Fragment
//import com.example.shreebeautystudio.R
//import com.example.shreebeautystudio.ui.fragment.homeFragment
//import com.example.shreebeautystudio.ui.fragment.postFragment
//import com.example.shreebeautystudio.ui.fragment.profileFragment
//import com.example.shreebeautystudio.ui.fragment.reelFragment
//import com.example.shreebeautystudio.ui.fragment.searchFragment
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//class NavigationActivty : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_navigation_activty)
//
//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//
//        // Set up fragment navigation
//        bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            val fragment: Fragment = when (menuItem.itemId) {
//                R.id.home -> homeFragment()
//                R.id.search -> searchFragment()
//                R.id.post -> postFragment()
//                R.id.reel -> reelFragment()
//                R.id.profile -> profileFragment()
//                else -> homeFragment()
//            }
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.frameLayout, fragment)
//                .commit()
//            true
//        }
//
//        // Set default fragment
//        bottomNavigationView.selectedItemId = R.id.home
//    }
//}

package com.example.shreebeautystudio.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shreebeautystudio.R
import com.example.shreebeautystudio.ui.fragment.HomeFragment
import com.example.shreebeautystudio.ui.fragment.PostFragment
import com.example.shreebeautystudio.ui.fragment.ProfileFragment
import com.example.shreebeautystudio.ui.fragment.SearchFragment
import com.example.shreebeautystudio.ui.fragment.ReelFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_activty)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Set up fragment navigation
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val fragment: Fragment = when (menuItem.itemId) {
                R.id.home -> HomeFragment()
                R.id.search -> SearchFragment()
                R.id.post -> PostFragment()
                R.id.reel -> ReelFragment()
                R.id.profile -> ProfileFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()
            true
        }

        // Set default fragment
        bottomNavigationView.selectedItemId = R.id.home
    }
}
