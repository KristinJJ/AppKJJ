package com.ad340.appkjj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    Button loginBtn;
    EditText usernameText;
    EditText emailText;
    EditText passwordText;
    FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // login
        usernameText = findViewById(R.id.username);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);

        sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        authentication = FirebaseAuth.getInstance();

        usernameText.setText(getEntry("username"));
        emailText.setText(getEntry("email"));
        passwordText.setText(getEntry("password"));

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        // Get grid
        GridView mainGrid = findViewById(R.id.mainGrid);

        // Create buttons to add to grid
        ArrayList<Button> buttonList = new ArrayList<>();
        String[] buttonNames = {"Movies", "Cameras", "Map", "Recipes", "Groceries", "Songs" };
        for(String buttonName : buttonNames) {
            Button newBtn = new Button(this);
            newBtn.setText(buttonName);
            buttonList.add(newBtn);
        }

        // Add buttons to grid by using custom adapter
        ArrayAdapter<Button> buttonArrayAdapter = new CustomButtonAdapter(this, buttonList);
        mainGrid.setAdapter(buttonArrayAdapter);
    }

    public String getEntry(String key) {
        return sharedPref.getString(key, "");
    }

    public void saveEntry(String key, String message) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, message);
        editor.commit();
    }

    private void signIn() {
        Log.d("FIREBASE", "signIn");

        // 1 - validate display name, email, and password entries
        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        // 2 - save valid entries to shared preferences
        if (!checkEntry(username, email, password)) {
            return;
        }

        saveEntry("userName", username);
        saveEntry("email", email);
        saveEntry("password", password);

        // 3 - sign into Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("FIREBASE", "signIn:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()) {
                            // update profile. displayname is the value entered in UI
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(username)
                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d("FIREBASE", "User profile updated.");
                                                // Go to FirebaseActivity
                                                startActivity(new Intent(MainActivity.this, FirebaseActivity.class));
                                            }
                                        }
                                    });

                        } else {
                            Log.d("FIREBASE", "sign-in failed");

                            Toast.makeText(MainActivity.this, "Sign In Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean checkEntry(String username, String email, String password) {
        boolean checked = true;
        if (TextUtils.isEmpty(username)) {
            usernameText.setError("Required");
            checked = false;
        } else {
            usernameText.setError(null);
        }
        if (TextUtils.isEmpty(email)) {
            emailText.setError("Required");
            checked = false;
        } else {
            emailText.setError(null);
        }
        if (TextUtils.isEmpty(password)) {
            passwordText.setError("Required");
            checked = false;
        } else {
            passwordText.setError(null);
        }
        return checked;
    }

}