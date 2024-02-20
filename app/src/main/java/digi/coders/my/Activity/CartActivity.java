package digi.coders.my.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import digi.coders.my.Adapter.MyCartAdapter;
import digi.coders.my.Models.MyCartModel;
import digi.coders.my.Models.ShowAllModel;
import digi.coders.my.R;

public class CartActivity extends AppCompatActivity {

    TextView  overAllAmount;
    int overAllTotalAmount;
    MyCartAdapter myCartAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<MyCartModel> cartModelList;
    private FirebaseFirestore firestore ;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.cart_rec);
        overAllAmount = findViewById(R.id.text2);

        toolbar = findViewById(R.id.my_cart_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // get data form my cart Activity
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mMessageReceiver,new IntentFilter("MyTotalAmount"));

        cartModelList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myCartAdapter = new MyCartAdapter(this,cartModelList);
        recyclerView.setAdapter(myCartAdapter);

        firestore.collection("AddToCart").document(auth.getCurrentUser().getUid())
                .collection("User").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                MyCartModel myCartModel = doc.toObject(MyCartModel.class);
                                cartModelList.add(myCartModel);
                                myCartAdapter.notifyDataSetChanged();
                            }
                        }

                    }
                });


    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
          int totalBill =   intent.getIntExtra("totalAMount",0);
          overAllAmount.setText("Total Amount : "+totalBill+"$");
        }
    };
}