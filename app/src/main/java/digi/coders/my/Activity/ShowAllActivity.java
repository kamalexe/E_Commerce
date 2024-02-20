package digi.coders.my.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import digi.coders.my.Adapter.ShowAllAdapter;
import digi.coders.my.Models.CategoryModel;
import digi.coders.my.Models.ShowAllModel;
import digi.coders.my.R;

public class ShowAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ShowAllAdapter showAllAdapter;
    List<ShowAllModel> showAllModelList;
    FirebaseFirestore firestore;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        String type = getIntent().getStringExtra("type");


        recyclerView = findViewById(R.id.show_all_rec);
        firestore = FirebaseFirestore.getInstance();
        toolbar = findViewById(R.id.show_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        showAllModelList = new ArrayList<>();
        showAllAdapter = new ShowAllAdapter(this,showAllModelList);
        recyclerView.setAdapter(showAllAdapter);



        if (type == null || type.isEmpty()){
            firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("man")){
            firestore.collection("ShowAll").whereEqualTo("type","man").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("woman")){
            firestore.collection("ShowAll").whereEqualTo("type","woman").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("watch")){
            firestore.collection("ShowAll").whereEqualTo("type","watch").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("camera")){
            firestore.collection("ShowAll").whereEqualTo("type","camera").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("kids")){
            firestore.collection("ShowAll").whereEqualTo("type","kids").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("shoes")){
            firestore.collection("ShowAll").whereEqualTo("type","shoes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for (DocumentSnapshot doc : task.getResult().getDocuments()){
                            ShowAllModel showAllModel = doc.toObject(ShowAllModel.class);
                            showAllModelList.add(showAllModel);
                            showAllAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });
        }

//        firestore.collection("ShowAll").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                ShowAllModel showAllModel = document.toObject(ShowAllModel.class);
//                                showAllModelList.add(showAllModel);
//                                showAllAdapter.notifyDataSetChanged();
//
//                            }
//                        } else {
//                            Toast.makeText(ShowAllActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });


    }
}