package com.example.jegyzet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class jegyzetLista extends AppCompatActivity {

    private FirebaseUser user;

    private RecyclerView recyclerView;
    private ArrayList<jegyzet> itemlist;
    private JegyzetItemAdapter jegyzetItemAdapter;
    private static final int SECRET_KEY = 391;

    private int gridNumber=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jegyzet_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if(secret_key!=391){
            finish();
        }

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            finish();
        }

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));

        itemlist = new ArrayList<>();
        jegyzetItemAdapter = new JegyzetItemAdapter(this, itemlist);

        recyclerView.setAdapter(jegyzetItemAdapter); // Set adapter to RecyclerView

        initializData();
    }


    private void initializData() {
        String[] cim = getResources().getStringArray(R.array.cim);
        itemlist.clear();
        for (String title : cim) {
            itemlist.add(new jegyzet(title, "")); // Assuming the second parameter is content
        }

        jegyzetItemAdapter.notifyDataSetChanged();
    }


    public void hozzaadaslink(View view) {
        Intent intent = new Intent(this, jegyzetModositas.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_right);
    }
}