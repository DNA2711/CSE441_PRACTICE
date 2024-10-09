package com.dna.day_change;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editnamduonglich;
    Button button1;
    TextView textview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editnamduonglich = findViewById(R.id.editnamduonglich);
        button1 = findViewById(R.id.button1);
        textview4 = findViewById(R.id.textview4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int namduong = Integer.parseInt(editnamduonglich.getText().toString());
                    String can = getCan(namduong);
                    String chi = getChi(namduong);
                    String namamlich = can + " " + chi;
                    textview4.setText(namamlich);
                } catch (NumberFormatException e) {
                    editnamduonglich.setError("Vui lòng nhập năm dương lịch");
                }
            }
        });
    }

    private String getCan(int namduong) {
        String[] can = {"Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ"};
        return can[namduong % 10];
    }

    private String getChi(int namduong) {
        String[] chi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};
        return chi[namduong % 12];
    }
}