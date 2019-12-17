package com.murav.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.murav.coffeeapp.service.CoffeeServiceImpl;

public class AddCoffeeActivity extends AppCompatActivity {

    private CoffeeServiceImpl coffeeService = new CoffeeServiceImpl();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coffee);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nameView = (TextView) findViewById(R.id.text_name);
                TextView descriptionView = (TextView) findViewById(R.id.text_description);

                String name = (nameView != null) ? nameView.getText().toString() : "Coffee Name";
                String description = (descriptionView != null) ? descriptionView.getText().toString() : "Coffee Description";

                coffeeService.addCoffee(name, description);
                Toast.makeText(getBaseContext(), "New Coffee Added", Toast.LENGTH_LONG).show();

                nameView.setText("");
                descriptionView.setText("");
            }
        });


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.list_of_coffee:
                    intent = new Intent(getApplicationContext(), CoffeeListActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.profile:
                    intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    return true;
            }

            return false;
        }
    };

}
