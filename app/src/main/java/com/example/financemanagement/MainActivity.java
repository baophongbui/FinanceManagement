package com.example.financemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button button;
    static TextView txtVuaNap;
    static String vuaNap;
    SharedPreferences sharedPreferences;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnNap);
        txtVuaNap = findViewById(R.id.txtVuaNap);

        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        String test =sharedPreferences.getString("key Hien Co", null);
        if (test == null) {
            test = "0";
        }
        String str = NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(test));

        txtVuaNap.setText(str);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent(this, naptien.class);
        startActivity(intent);
    }

}