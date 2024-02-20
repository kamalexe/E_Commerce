package digi.coders.my.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import digi.coders.my.Fragments.HomeFragment;
import digi.coders.my.R;

public class MainActivity extends AppCompatActivity {

    Fragment homefragment;
    FirebaseAuth auth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_calendar_view_day_24);

        auth = FirebaseAuth.getInstance();

        homefragment = new HomeFragment();
        loadfragment(homefragment);
    }

    private void loadfragment(Fragment homefragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_container, homefragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_logout) {
            auth.signOut();
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
        } else if (id == R.id.menu_my_cart) {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        }
        return true;
    }
}