package com.murav.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.murav.coffeeapp.model.CoffeeModel;
import com.murav.coffeeapp.service.CoffeeService;
import com.murav.coffeeapp.service.CoffeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.murav.coffeeapp.ProfileActivity.GOOGLE_ACCOUNT;

public class CoffeeListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<CoffeeModel> coffeeModels = new ArrayList<>();
    CoffeeService coffeeService = new CoffeeServiceImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager rvLiLayoutManager;
        rvLiLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLiLayoutManager);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        coffeeModels = coffeeService.getAllCoffee();

        CoffeeAdapter adapter = new CoffeeAdapter(coffeeModels);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(new CoffeeAdapter.ClickListener() {
            @Override
            public void onClick(int position) {
                CoffeeModel coffeeModel = coffeeModels.get(position);
                Intent intent = new Intent(CoffeeListActivity.this, CoffeeItemActivity.class);
                System.out.println("Id ++++ " + coffeeModel.getId());
                intent.putExtra("coffeeModelId", coffeeModel.getId());
                startActivity(intent);
            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.list_of_coffee:
                    return true;
                case R.id.profile:
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, getIntent().getParcelableExtra(GOOGLE_ACCOUNT));
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

}

