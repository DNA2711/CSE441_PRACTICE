package com.dna.bai8;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

btnCall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
// TODO Auto-generated method stub
//Tạo mới một đối tượng intent
            Intent intent1 =new Intent(MainActivity. this, CallPhoneActivity.class);
//Thực thi Intent1
        }
    });
}