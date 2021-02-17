package com.example.admin.sneakerstoreappl.other;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.admin.sneakerstoreappl.Activities.AboutUs;
import com.example.admin.sneakerstoreappl.Activities.CartActivity3;
import com.example.admin.sneakerstoreappl.Activities.HomeActivity2;
import com.example.admin.sneakerstoreappl.Activities.SearchProductsActivity;
import com.example.admin.sneakerstoreappl.Activities.SettingsActivity3;
import com.example.admin.sneakerstoreappl.R;

public class CategoriesActivity extends AppCompatActivity {
/*
    private String type = "";

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            type = getIntent().getExtras().get("Admin").toString();
        }

        drawerLayout = findViewById(R.id.drawer_layout_cat);
    }
*/
/*
    public void ClickMenu(View view) {
        HomeActivity2.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        HomeActivity2.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view) {
        HomeActivity2.redirectActivity(this,
                HomeActivity2.class);
    }

//############################################################

    public void ClickDashboard(View view) {
        HomeActivity2.redirectActivity(this,
                Dashboard.class);
    }

    public void ClickCart(View view) {
        if (!type.equals("Admin")) {
            HomeActivity2.redirectActivity(this,
                    CartActivity3.class);
        }
    }

    public void ClickSearch(View view) {
        if (!type.equals("Admin")) {
            HomeActivity2.redirectActivity(this,
                    SearchProductsActivity.class);
        }
    }

    public void ClickOrders(View view) {
        HomeActivity2.redirectActivity(this,
                OrdersActivity.class);
    }

    public void ClickCategories(View view) {
        recreate();
    }

    public void ClickSettings(View view) {
        if (!type.equals("Admin")) {
            HomeActivity2.redirectActivity(this,
                    SettingsActivity3.class);
        }
    }

//############################################################

    public void ClickAboutUs(View view) {
        HomeActivity2.redirectActivity(this,
                AboutUs.class);
    }

    public void ClickLogout(View view) {
        HomeActivity2.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        HomeActivity2.closeDrawer(drawerLayout);
    }


*/
}