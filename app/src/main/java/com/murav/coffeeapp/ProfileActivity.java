package com.murav.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.murav.coffeeapp.model.User;
import com.murav.coffeeapp.service.CoffeeService;
import com.murav.coffeeapp.service.CoffeeServiceImpl;
import com.murav.coffeeapp.service.UserService;
import com.murav.coffeeapp.service.UserServiceImpl;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account";
    public static final String ADMIN_EMAIL = "muraveyisa19@gmail.com";
    private TextView profileName, profileEmail;
    private ImageView profileImage;
    private Button signOut, goToAddActivityButton, deleteAll;
    private GoogleSignInClient googleSignInClient;
    private UserService userService = new UserServiceImpl();
    private CoffeeService coffeeService = new CoffeeServiceImpl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        goToAddActivityButton = findViewById(R.id.go_to_add_activity_button);

        profileName = findViewById(R.id.profile_text);
        profileEmail = findViewById(R.id.profile_email);
        profileImage = findViewById(R.id.profile_image);
        deleteAll = findViewById(R.id.delete_all);
        signOut = findViewById(R.id.sign_out);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        setDataOnView();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        userService.deleteAll();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });

        goToAddActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCoffeeActivity.class);
                startActivity(intent);
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coffeeService.deleteAll();
                Toast.makeText(getApplicationContext(), "All data is removed!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setDataOnView() {
        String accountEmail = "admin@gmail.com";
        User user = userService.getOneUser();

        if (user == null) {
            profileName.setText("Default Name");
            profileEmail.setText("Default Email");
        } else {
            if (!user.getPhotoUrl().isEmpty()) {
                Picasso.get().load(user.getPhotoUrl());
            }
            profileName.setText(user.getName());
            profileEmail.setText(user.getEmail());
            accountEmail = user.getEmail();
        }

        if (accountEmail.equalsIgnoreCase(ADMIN_EMAIL)) {
            findViewById(R.id.go_to_add_activity_button).setVisibility(View.VISIBLE);
        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.list_of_coffee:
                    Intent intent = new Intent(getApplicationContext(), CoffeeListActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.profile:
                    return true;
            }
            return false;
        }
    };

}
