package com.example.pozsikmt.receptkonyv_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton btnKategory = findViewById(R.id.btnKategory);
        btnKategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(MenuActivity.this, KategoryActivity.class);
                startActivity(profileIntent);
            }
        });

        ImageButton btnKedvenc = findViewById(R.id.btnKedvenc);
        btnKedvenc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent holidayIntent = new Intent(MenuActivity.this, KedvencActivity.class);
                startActivity(holidayIntent);
            }
        });

        ImageButton btnBevasarlo = findViewById(R.id.btnBevasarlo);
        btnBevasarlo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(MenuActivity.this, ShopActivity.class);
                startActivity(profileIntent);
            }
        });

        ImageButton btnRecept = findViewById(R.id.btnRecept);
        btnRecept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent holidayIntent = new Intent(MenuActivity.this, ReceptActivity.class);
                startActivity(holidayIntent);
            }
        });


    }

}
