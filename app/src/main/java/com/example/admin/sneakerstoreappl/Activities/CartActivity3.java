package com.example.admin.sneakerstoreappl.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.sneakerstoreappl.Model.Cart;
import com.example.admin.sneakerstoreappl.Prevalent.Prevalent;
import com.example.admin.sneakerstoreappl.R;
import com.example.admin.sneakerstoreappl.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CartActivity3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Button nextProcessBtn;
    private TextView textTotalAmount, txtConfirmationMsg;

    private String overTotalPrice = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart3);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        nextProcessBtn = (Button) findViewById(R.id.next_process_btn);
        textTotalAmount = (TextView) findViewById(R.id.total_price);
        txtConfirmationMsg = (TextView) findViewById(R.id.confirmation_msg);


        nextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textTotalAmount.setText("Total Price = " + overTotalPrice + "€");
                //textTotalAmount.setText("Total Price = " + String.valueOf(overTotalPrice) + "€");

                Intent intent = new Intent(CartActivity3.this,
                        ConfirmFinalOrderActivity.class);
                intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                startActivity(intent);
                finish();
            }
        });
        //
        //
        //
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkOrderState();


        final DatabaseReference cartListRef = FirebaseDatabase
                .getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(cartListRef.child("User View")
                                .child(Prevalent.currentOnlineUser.getPhone())
                                .child("Products"), Cart.class)
                        .build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder,
                                            int position, @NonNull Cart model) {

                holder.txtProductQuantity.setText("Quantity = " + model.getQuantity());
                holder.txtProductPrice.setText("Price: " + model.getPrice() + "€");
                holder.txtProductSize.setText("Size: " + model.getSize());
                holder.txtProductName.setText(model.getPname());
                holder.txtProductColor.setText("Color: " + model.getColor());

                int oneTypeProductTPrice =
                        ((Integer.parseInt(model.getPrice()))) * Integer.parseInt(model.getQuantity());
                //((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());

                int otp = Integer.parseInt(overTotalPrice);
                //int otp = Integer.valueOf(overTotalPrice);

                overTotalPrice = String.valueOf(otp + oneTypeProductTPrice);
                //overTotalPrice = overTotalPrice + oneTypeProductTPrice     ;

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]{
                                "Edit",
                                "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                CartActivity3.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (i == 0) {
                                    Intent intent = new Intent(CartActivity3.this,
                                            ProductDetailsActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                                }
                                if (i == 1) {
                                    cartListRef.child("User View")
                                            .child(Prevalent.currentOnlineUser.getPhone())
                                            .child("Products")
                                            .child(model.getPid())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {

                                                        Toast.makeText(CartActivity3.this,
                                                                "Item deleted successfully",
                                                                Toast.LENGTH_LONG).show();

                                                        Intent intent = new Intent(
                                                                CartActivity3.this,
                                                                HomeActivity2.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_item_layout, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        //
        //
        //

    }


    private void checkOrderState() {

        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String shipingState = dataSnapshot.child("state").getValue().toString();
                    String userName = dataSnapshot.child("name").getValue().toString();

                    if (shipingState.equals("shipped")) {
                        textTotalAmount.setText("Dear " + userName + ", your \n order was shipped successfully");

                        recyclerView.setVisibility(View.GONE);
                        txtConfirmationMsg.setVisibility(View.VISIBLE);
                        txtConfirmationMsg.setText("Congratulations, your order was shipped successfully. You will receive it in a few days at the desired address.");
                        nextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity3.this,
                                "After you receive your first order, you are allowed to buy more products",
                                Toast.LENGTH_LONG).show();
                    } else if (shipingState.equals("not shipped")) {

                        textTotalAmount.setText("Your order has not been shipped yet");

                        recyclerView.setVisibility(View.GONE);
                        txtConfirmationMsg.setVisibility(View.VISIBLE);
                        nextProcessBtn.setVisibility(View.GONE);

                        Toast.makeText(CartActivity3.this,
                                "After you receive your first order, you are allowed to buy more products",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    //
    //
    //
}