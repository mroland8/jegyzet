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

public class Registration extends AppCompatActivity {
    EditText email;
    EditText pass;
    EditText passConf;

    private static final int SECRET_KEY = 391;


    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        int secretkey = getIntent().getIntExtra("SECRET_KEY",0);

        if(secretkey!=391){
            finish();
        }

        fAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.RegEmailInput);
        pass=findViewById(R.id.RegPassInput);
        passConf=findViewById(R.id.RegPassInput2);
    }

    public void registration(View view) {
        String emailStr = email.getText().toString();
        String passStr = pass.getText().toString();
        String passConfStr = passConf.getText().toString();

        if(!passStr.equals(passConfStr)){
            return;
        }

        fAuth.createUserWithEmailAndPassword(emailStr, passStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    noteLink();
                    Log.i("fasza", "jo a regisztracio");
                } else {
                    Log.i("gebasz", "nem jo a regisztracio");
                }
            }
        });
    }

    public void loginLink(View view) {
        finish();
    }

    public void noteLink(){
        Intent intent = new Intent(this, jegyzetLista.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}