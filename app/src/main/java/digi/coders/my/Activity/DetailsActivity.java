package digi.coders.my.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

import digi.coders.my.Models.NewProductsModel;
import digi.coders.my.Models.PopularProductModel;
import digi.coders.my.Models.ShowAllModel;
import digi.coders.my.R;

public class DetailsActivity extends AppCompatActivity {

    ImageView detailedImg, addItems, removeItems;
    TextView rating, name, description, price, quantity;
    Button addToCart, buyNow;
    int totalQuantity = 1;
    int totalPrice = 0;
    Toolbar toolbar;
    // New Products
    NewProductsModel newProductsModel = null;
    // Popular Products
    PopularProductModel popularProductModel = null;
    // Show All
    ShowAllModel showAllModel = null;
    private FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra("detailed");

        if (obj instanceof NewProductsModel) {
            newProductsModel = (NewProductsModel) obj;
        } else if (obj instanceof PopularProductModel) {
            popularProductModel = (PopularProductModel) obj;
        } else if (obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;
        }


        detailedImg = findViewById(R.id.detailes_img);
        addItems = findViewById(R.id.add_item);
        removeItems = findViewById(R.id.revome_item);

        name = findViewById(R.id.detailes_name);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.detailes_desc);
        price = findViewById(R.id.detailes_price);
        quantity = findViewById(R.id.quantity);

        toolbar = findViewById(R.id.detailed_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addToCart = findViewById(R.id.add_to_cart);
        buyNow = findViewById(R.id.buy_now);

        // New Products
        if (newProductsModel != null) {
            Glide.with(getApplicationContext()).load(newProductsModel.getImg_uri()).into(detailedImg);
            name.setText(newProductsModel.getName());
            rating.setText(newProductsModel.getRating());
            description.setText(newProductsModel.getDescription());
            price.setText(String.valueOf(newProductsModel.getPrice()));

            totalPrice = newProductsModel.getPrice() * totalQuantity;
        }

        // Popular Products
        if (popularProductModel != null) {
            Glide.with(getApplicationContext()).load(popularProductModel.getImg_uri()).into(detailedImg);
            name.setText(popularProductModel.getName());
            rating.setText(popularProductModel.getRating());
            description.setText(popularProductModel.getDescription());
            price.setText(String.valueOf(popularProductModel.getPrice()));

            totalPrice = popularProductModel.getPrice() * totalQuantity;
        }

        // Show All Products
        if (showAllModel != null) {
            Glide.with(getApplicationContext()).load(showAllModel.getImg_uri()).into(detailedImg);
            name.setText(showAllModel.getName());
            rating.setText(showAllModel.getRating());
            description.setText(showAllModel.getDescription());
            price.setText(String.valueOf(showAllModel.getPrice()));

            totalPrice = showAllModel.getPrice() * totalQuantity;
        }

        // Buy Now

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this,AddressActivity.class);
                if (newProductsModel != null){
                    intent.putExtra("item",newProductsModel);
                }
                if (popularProductModel != null){
                    intent.putExtra("item",popularProductModel);
                }
                if (showAllModel != null){
                    intent.putExtra("item",showAllModel);
                }

                startActivity(intent);
            }
        });

        // Add to Cart
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });

        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));

                    if (newProductsModel != null) {
                        totalPrice = newProductsModel.getPrice() * totalQuantity;
                    }
                    if (popularProductModel != null) {
                        totalPrice = popularProductModel.getPrice() * totalQuantity;
                    }
                    if (showAllModel != null) {
                        totalPrice = showAllModel.getPrice() * totalQuantity;
                    }


                }
            }
        });

        removeItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 1) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                }
            }
        });

    }

    private void addToCart() {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM,dd,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", name.getText().toString());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);

        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalprice",totalPrice);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(DetailsActivity.this, "Added To a Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}