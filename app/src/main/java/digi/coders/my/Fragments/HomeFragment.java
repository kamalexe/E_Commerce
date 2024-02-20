package digi.coders.my.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import digi.coders.my.Activity.ShowAllActivity;
import digi.coders.my.Adapter.CategoryAdapter;
import digi.coders.my.Adapter.NewProductsAdapter;
import digi.coders.my.Adapter.PopularProductAdapter;
import digi.coders.my.Models.CategoryModel;
import digi.coders.my.Models.NewProductsModel;
import digi.coders.my.Models.PopularProductModel;
import digi.coders.my.R;


public class HomeFragment extends Fragment {

    TextView catShowAll, popularShoewAll, newProductShoewAll;
    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    RecyclerView catRecyclerView, newProductsRecyclerView, popularRecyclerView;
    // Category recyclerView
    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;

    // New Products RecyclerView
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    // Popular Products RecyclerView
    PopularProductAdapter popularProductAdapter;
    List<PopularProductModel> popularProductModelList;
    FirebaseFirestore db;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog = new ProgressDialog(getActivity());
        catRecyclerView = root.findViewById(R.id.rec_category);
        newProductsRecyclerView = root.findViewById(R.id.new_product_rec);
        popularRecyclerView = root.findViewById(R.id.popular_rec);

        catShowAll = root.findViewById(R.id.category_see_all);
        popularShoewAll = root.findViewById(R.id.popular_see_all);
        newProductShoewAll = root.findViewById(R.id.newProducts_see_all);

        db = FirebaseFirestore.getInstance();
        linearLayout = root.findViewById(R.id.home_layout);
        linearLayout.setVisibility(View.GONE);


        catShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowAllActivity.class));
            }
        });
        popularShoewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowAllActivity.class));
            }
        });
        newProductShoewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ShowAllActivity.class));
            }
        });


        // image sloder
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.shoes1, "Discount On Shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.sneakers, "70% Off", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.shoes2, "Discount On Shoes Item", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner1jpg, "70% Off", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.shoes3, "Discount On Shoes Item", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);
        progressDialog.setTitle("Welcome To Ecommerce App");
        progressDialog.setMessage("please wail...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        // Category
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(), categoryModelList);
        catRecyclerView.setAdapter(categoryAdapter);

        db.collection("Category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility(View.VISIBLE);
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        //  New Products
        newProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter(getActivity(), newProductsModelList);
        newProductsRecyclerView.setAdapter(newProductsAdapter);

        db.collection("NewProducts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                NewProductsModel newProductsModel = document.toObject(NewProductsModel.class);
                                newProductsModelList.add(newProductsModel);
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        // Popular Product
        popularRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        popularProductModelList = new ArrayList<>();
        popularProductAdapter = new PopularProductAdapter(getActivity(), popularProductModelList);
        popularRecyclerView.setAdapter(popularProductAdapter);

        db.collection("AllProducts")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularProductModel popularProductModel = document.toObject(PopularProductModel.class);
                                popularProductModelList.add(popularProductModel);
                                popularProductAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}