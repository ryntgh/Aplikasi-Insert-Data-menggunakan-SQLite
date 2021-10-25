package id.ryan.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText GetPesan;
    Button Simpan;
    String Pesan;
    Boolean CekPesanKosong;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetPesan = findViewById(R.id.pesan);
        Simpan = findViewById(R.id.simpan);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openOrCreateDatabase("db_pesan", Context.MODE_PRIVATE, null);
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tb_pesan (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, pesan VARCHAR);");

                Pesan = GetPesan.getText().toString();

                CekPesanKosong(Pesan);

                if (CekPesanKosong) {
                    //Jika edit teks tidak kosong, maka disimpan
                    sqLiteDatabase.execSQL("INSERT INTO tb_pesan (pesan) VALUES ('" + Pesan + "');");
                    Toast.makeText(MainActivity.this, "Data Berhasil di Simpan", Toast.LENGTH_SHORT).show();

                    //Menghapus pesan setelah terjadi input
                    GetPesan.getText().clear();

                } else {
                    //Jika kosong, maka akan muncul toast
                    Toast.makeText(MainActivity.this, "Masukkan Pesan", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void CekPesanKosong(String pesan) {
        CekPesanKosong = !TextUtils.isEmpty(pesan);
    }
}
