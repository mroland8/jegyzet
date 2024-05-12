package com.example.jegyzet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG=MainActivity.class.getName();
    private static final int SECRET_KEY=391;
    EditText emailET;
    EditText passwordET;

    String email;
    String password;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailET=findViewById(R.id.LoginEmailInput);
        passwordET=findViewById(R.id.LoginPasswordInput);
        email=emailET.getText().toString();
        password=passwordET.getText().toString();
        fAuth=FirebaseAuth.getInstance();
    }

    public void login(View view) {
        emailET=findViewById(R.id.LoginEmailInput);
        passwordET=findViewById(R.id.LoginPasswordInput);
        email=emailET.getText().toString();
        password=passwordET.getText().toString();

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    noteLink();
                    Log.i("fasza", "jo a bejelentkezes");
                } else {
                    Log.i("gebasz", "nem jo a bejelentkezes");
                }
            }
        });
    }

    public void registerLink(View view) {
        Intent intent =new Intent(this, Registration.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
    }


    public void noteLink(){
        Intent intent = new Intent(this, jegyzetLista.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}