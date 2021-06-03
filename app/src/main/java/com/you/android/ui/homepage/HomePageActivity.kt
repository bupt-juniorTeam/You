package com.you.android.ui.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.you.android.R

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var toolbar=findViewById<Toolbar>(R.id.toolbar_person_center)

        val drawer=findViewById<DrawerLayout>(R.id.drawerLayout_person_center)

        var navigationView=findViewById<NavigationView>(R.id.navigationView_person_center)




    }
}