package com.example.admin.sneakerstoreappl.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.sneakerstoreappl.Administrator.AdminMaintainProductsActivity;
import com.example.admin.sneakerstoreappl.Model.Products;
import com.example.admin.sneakerstoreappl.Prevalent.Prevalent;
import com.example.admin.sneakerstoreappl.R;
import com.example.admin.sneakerstoreappl.ViewHolder.ProductViewHolder;
import com.example.admin.sneakerstoreappl.other.CategoriesActivity;
import com.example.admin.sneakerstoreappl.other.Dashboard;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import io.paperdb.Paper;

public class HomeActivity2 extends AppCompatActivity {

    DrawerLayout drawerLayout;

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home3);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            type = getIntent().getExtras().get("Admin").toString();
        }


        FloatingActionButton productCartFab = findViewById(R.id.product_cart_fab);
        productCartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HomeActivity2.this, CartActivity3.class);
                startActivity(intent);
            }
        });


        drawerLayout = findViewById(R.id.drawer_layout_3);


        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");


        Paper.init(this);


        TextView userNameTextView = findViewById(R.id.user_profile_name_2);
        ImageView profileImageView = findViewById(R.id.user_profile_image_2);

        if (!type.equals("Admin")) {
            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage())
                    .placeholder(R.drawable.profile)
                    .into(profileImageView);
        }


        recyclerView = findViewById(R.id.recycler_menu_3);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductsRef, Products.class)
                        .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder,
                                                    int position, @NonNull Products model) {

                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Price: " + model.getPrice() + "€");
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (type.equals("Admin")) {
                                    Intent intent = new Intent(HomeActivity2.this,
                                            AdminMaintainProductsActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(HomeActivity2.this,
                                            ProductDetailsActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                int viewType) {

                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };

        recyclerView.setAdapter(adapter);
        adapter.startListening();


    }


    public void ClickMenu(View view) {
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);

    }

    /*  */

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    public void ClickHome(View view) {
        recreate();
    }

    //############################################################################################
//
    /*   */
    public void ClickDashboard(View view) {
        redirectActivity(this, Dashboard.class);
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


    public void ClickCategories(View view) {
        if (!type.equals("Admin")) {
            HomeActivity2.redirectActivity(this,
                    CategoriesActivity.class);
        }
    }

    public void ClickSettings(View view) {
        if (!type.equals("Admin")) {
            HomeActivity2.redirectActivity(this,
                    SettingsActivity3.class);
        }
    }

    //
//############################################################################################
    /*  */
    public void ClickAboutUs(View view) {
        redirectActivity(this, AboutUs.class);
    }

    public void ClickLogout(View view) {
        //if (!type.equals("Admin")) {
        logout(this);
        //}
    }


    public static void logout(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("Logout");

        builder.setMessage("Are you sure you want to logout?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    /*    */
    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }


    //
    //
    //

}