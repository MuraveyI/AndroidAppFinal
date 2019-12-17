package com.murav.coffeeapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.murav.coffeeapp.model.CoffeeModel;
import com.murav.coffeeapp.service.CoffeeService;
import com.murav.coffeeapp.service.CoffeeServiceImpl;

public class CoffeeItemActivity extends AppCompatActivity {

    int coffeeModelId;
    CoffeeModel coffeeModel;
    ImageView generalImage;
    TextView generalText, coffeeName;
    CoffeeService coffeeService = new CoffeeServiceImpl();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_item);

        generalImage = findViewById(R.id.coffee_image);
        coffeeName = findViewById(R.id.coffee_name);
        generalText = findViewById(R.id.coffee_item_text);

        coffeeModelId = getIntent().getIntExtra("coffeeModelId", 0);
        coffeeModel = coffeeService.getById(coffeeModelId);

        if (coffeeModel != null) {
            String imageName = (coffeeModel.getImage() == null) ? "allcoffee" : coffeeModel.getImage();
            int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            generalImage.setImageResource(imageResourceId);
            generalText.setText(coffeeModel.getDescription());
            coffeeName.setText(coffeeModel.getName());
        }
    }

}
