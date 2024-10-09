package com.dna.dk_ttincanhan;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtHoTen, edtCMND, edtThongTinBoSung;
    RadioGroup rgBangCap;
    RadioButton rbTrungCap, rbCaoDang, rbDaiHoc;
    CheckBox cbDocBao, cbDocSach, cbDocCoding;
    Button btnGuiThongTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edtHoTen = findViewById(R.id.editHoten);
        edtCMND = findViewById(R.id.editCMND);
        edtThongTinBoSung = findViewById(R.id.editBosung);
        rgBangCap = findViewById(R.id.radioGroup1);
        rbTrungCap = findViewById(R.id.radtc);
        rbCaoDang = findViewById(R.id.radcd);
        rbDaiHoc = findViewById(R.id.raddh);
        cbDocBao = findViewById(R.id.chkdocbao);
        cbDocSach = findViewById(R.id.chkdocsach);
        cbDocCoding = findViewById(R.id.chkdoccoding);
        btnGuiThongTin = findViewById(R.id.btnguitt);

        btnGuiThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
                    showThongTinCaNhan();
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Question")
                        .setMessage("Are you sure you want to exit?")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private boolean validateData() {
        String hoTen = edtHoTen.getText().toString().trim();
        String cmnd = edtCMND.getText().toString().trim();

        if (hoTen.isEmpty() || hoTen.length() < 3) {
            edtHoTen.setError("Họ tên không được để trống và phải có ít nhất 3 ký tự");
            return false;
        }

        if (!cmnd.matches("[0-9]{9}")) {
            edtCMND.setError("CMND phải là số và có 9 chữ số");
            return false;
        }

        if (!cbDocBao.isChecked() && !cbDocSach.isChecked() && !cbDocCoding.isChecked()) {
            Toast.makeText(this, "Vui lòng chọn ít nhất một sở thích", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void showThongTinCaNhan() {
        String hoTen = edtHoTen.getText().toString();
        String cmnd = edtCMND.getText().toString();
        int selectedId = rgBangCap.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String bangCap = selectedRadioButton.getText().toString();

        StringBuilder soThich = new StringBuilder();
        if (cbDocBao.isChecked()) {
            soThich.append(cbDocBao.getText().toString()).append("; ");
        }
        if (cbDocSach.isChecked()) {
            soThich.append(cbDocSach.getText().toString()).append("; ");
        }
        if (cbDocCoding.isChecked()) {
            soThich.append(cbDocCoding.getText().toString());
        }

        String thongTinBoSung = edtThongTinBoSung.getText().toString();

        new AlertDialog.Builder(this)
                .setTitle("Thông tin cá nhân")
                .setMessage("Họ tên: " + hoTen + "\n" +
                        "CMND: " + cmnd + "\n" +
                        "Bằng cấp: " + bangCap + "\n" +
                        "Sở thích: " + soThich.toString() + "\n" +
                        "Thông tin bổ sung: " + thongTinBoSung)
                .setPositiveButton("OK", null)
                .show();
    }
}