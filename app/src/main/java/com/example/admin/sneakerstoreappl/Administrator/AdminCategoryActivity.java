package com.example.admin.sneakerstoreappl.Administrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admin.sneakerstoreappl.Activities.HomeActivity2;
import com.example.admin.sneakerstoreappl.MainActivity;
import com.example.admin.sneakerstoreappl.R;

public class AdminCategoryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private ImageView new_collection, top_sales, sport,
            select, collections, outlet,
            women, men, kids;

    private Button logoutBtn, checkOrdersBtn, maintainProductsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category_dl);
        //setContentView(R.layout.activity_admin_category);


        logoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        checkOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);


        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        AdminCategoryActivity.this, HomeActivity2.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        AdminCategoryActivity.this, AdminNewOrderActivity.class);

                startActivity(intent);

            }
        });


        drawerLayout = findViewById(R.id.drawer_layout_ac);


        new_collection = (ImageView) findViewById(R.id.new_collection);
        top_sales = (ImageView) findViewById(R.id.top_sales);
        sport = (ImageView) findViewById(R.id.sport);
        select = (ImageView) findViewById(R.id.select);
        collections = (ImageView) findViewById(R.id.collections);
        outlet = (ImageView) findViewById(R.id.outlet);
        women = (ImageView) findViewById(R.id.women);
        men = (ImageView) findViewById(R.id.men);
        kids = (ImageView) findViewById(R.id.kids);


        new_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "new_collection");
                startActivity(intent);
            }
        });

        top_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "top_sales");
                startActivity(intent);
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "sport");
                startActivity(intent);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "select");
                startActivity(intent);
            }
        });

        collections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "collections");
                startActivity(intent);
            }
        });

        outlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "outlet");
                startActivity(intent);
            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "women");
                startActivity(intent);
            }
        });

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "men");
                startActivity(intent);
            }
        });

        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this,
                        AdminAddNewProductActivity.class);
                intent.putExtra("category", "kids");
                startActivity(intent);
            }
        });


    }


    public static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**/
    public void ClickMenu(View view) {

        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //############################################################


    /**/
    public void ClickCart(View view) {

        recreate();
    }


    /**/
    public void ClickSearch(View view) {

        recreate();
    }


    /**/
    public void ClickSettings(View view) {

        recreate();
    }

    //############################################################
    /**/
    public void ClickAboutUs(View view) {

        recreate();
    }

    public void ClickLogout(View view) {
        HomeActivity2.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        HomeActivity2.closeDrawer(drawerLayout);
    }


}