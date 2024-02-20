package digi.coders.my.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.N;

import java.util.ArrayList;
import java.util.List;

import digi.coders.my.Adapter.AddressAdapter;
import digi.coders.my.Models.AddressModel;
import digi.coders.my.Models.MyCartModel;
import digi.coders.my.Models.NewProductsModel;
import digi.coders.my.Models.PopularProductModel;
import digi.coders.my.Models.ShowAllModel;
import digi.coders.my.R;

public class AddressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {
    Button addAddress,paymentBtn;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String mAddress;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get Data from detailed activity
         Object obj = getIntent().getSerializableExtra("item");

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        addressModelList = new ArrayList<>();

        addAddress = findViewById(R.id.add_address_btn);
        paymentBtn = findViewById(R.id.payment_btn);
        recyclerView = findViewById(R.id.address_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        addressAdapter = new AddressAdapter(getApplicationContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);


        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Address").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                AddressModel addressModel = doc.toObject(AddressModel.class);
                                addressModelList.add(addressModel);
                                addressAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });


        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(AddressActivity.this, PaymentActivity.class));
                double amount = 0.0;
                if (obj instanceof NewProductsModel){
                    NewProductsModel newProductsModel = (NewProductsModel) obj;
                    amount = newProductsModel.getPrice();
                }
                if (obj instanceof PopularProductModel){
                    PopularProductModel popularProductModel = (PopularProductModel) obj;
                    amount = popularProductModel.getPrice();
                }
                if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount = showAllModel.getPrice();
                }
                Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount",amount);
                startActivity(intent);

            }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this, AddAddressActivity.class));
            }
        });

    }

    @Override
    public void setAddress(String address) {
        mAddress = address;

    }
}