package com.example.financemanagement;

import static com.example.financemanagement.MainActivity.vuaNap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class naptien extends AppCompatActivity {
    private TextView btnThoat;
    private static TextView numHienCo;
    private TextView numConThieu, txtConThieu;
    private TextView numMucTieu;
    private TextView txtBack;
    private Button btnNapTien;
    private EditText edtNapTien;
    int soTienNap;
    int soTienHienCo;
    int soTienMucTieu;
    int soTienThieu;
    String numSoTienHienCo;
    String numSoTienMucTieu;
    String numSoTienThieu;
    static SharedPreferences sharedPreferences;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naptien);

        btnThoat = findViewById(R.id.btnThoat);
        btnNapTien = findViewById(R.id.btnNapTien);
        numHienCo = findViewById(R.id.numHienCo);
        edtNapTien = (EditText) findViewById(R.id.edtNapTien);
        numConThieu = findViewById(R.id.numConThieu);
        numMucTieu = findViewById(R.id.numMucTieu);
        txtBack = findViewById(R.id.txtBack);
        txtConThieu = findViewById(R.id.txtConThieu);


        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnbuckle();

            }
        });


        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnbuckle();
                removeData();

            }
        });

        btnNapTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNapTien.getText().toString().isEmpty() || Integer.parseInt(edtNapTien.getText().toString()) == 0){
                    Toast.makeText(naptien.this, "Vui lòng nhập số tiền cần nạp", Toast.LENGTH_SHORT).show();
                    return;
                }
                soTienNap = Integer.parseInt(edtNapTien.getText().toString());
                soTienMucTieu = Integer.parseInt(numMucTieu.getText().toString());
                soTienThieu = Integer.parseInt(numConThieu.getText().toString());
                soTienHienCo = Integer.parseInt(numHienCo.getText().toString());


                soTienHienCo = soTienHienCo + soTienNap;
                soTienThieu = soTienMucTieu - soTienHienCo;

                numHienCo.setText(String.valueOf(soTienHienCo));
                numMucTieu.setText(String.valueOf(soTienMucTieu));
                numConThieu.setText(String.valueOf(soTienThieu));

                btnNapTien.setText("Nạp tiền thành công");
                edtNapTien.setText("");
                btnNapTien.setClickable(false);

                if (soTienHienCo >= soTienMucTieu){
                    numHienCo.setText(String.valueOf(soTienMucTieu));
                    numMucTieu.setText(soTienMucTieu + " (Đã đạt mục tiêu)");
                    txtConThieu.setText("Số tiền thừa: ");
                    numConThieu.setText(String.valueOf(soTienThieu - 2*soTienThieu));
                }
            }
        });
        retrieveData();

    }
    public void turnbuckle(){
    Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
    }

    @Override
    protected void onPause() {
        saveData();
        super.onPause();
    }
    public void saveData(){
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
//        soTienNap = numHienCo.getText().toString();
//        numSoTienThieu = numConThieu.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key Hien Co", String.valueOf(soTienHienCo));
        editor.putString("key Con thieu", String.valueOf(soTienThieu));
        editor.apply();
    }

    public void retrieveData()
    {
        sharedPreferences = getSharedPreferences("saveData",MODE_PRIVATE);
        numSoTienHienCo = sharedPreferences.getString("key Hien Co",null);
        numSoTienThieu = sharedPreferences.getString("key Con thieu",null);

        if (numSoTienHienCo == null){
            numSoTienHienCo = "0";
            numSoTienThieu = "0";
            numHienCo.setText(String.valueOf(numSoTienHienCo));
            numConThieu.setText(String.valueOf(numSoTienThieu));
        }else {
            numHienCo.setText(numSoTienHienCo);
            numConThieu.setText(numSoTienThieu);
        }


    }
    public void removeData(){
        sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("key Hien Co");
        editor.remove("key Con thieu");
        editor.apply();


    }
}