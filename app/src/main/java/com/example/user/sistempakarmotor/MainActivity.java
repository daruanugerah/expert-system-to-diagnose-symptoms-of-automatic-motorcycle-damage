package com.example.user.sistempakarmotor;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.sistempakarmotor.models.Gejala;
import com.example.user.sistempakarmotor.models.Keputusan;
import com.example.user.sistempakarmotor.models.Kerusakan;

import static android.R.attr.x;
import static android.R.attr.y;
import static com.example.user.sistempakarmotor.R.id.infokerusakan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        ImageButton deteksi = (ImageButton) findViewById(R.id.deteksi);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        deteksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(myAnim);
                Intent i = new Intent(getApplicationContext(), DiagnosaActivity.class);
                startActivity(i);
            }
        });

        ImageButton infokerusakan = (ImageButton)findViewById(R.id.infokerusakan);
        infokerusakan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                view.startAnimation(myAnim);
                Intent i = new Intent(getApplicationContext(), InfoKerusakanActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Ketuk Jenis Kerusakan untuk Menampilkan Detail Jenis Kerusakan", Toast.LENGTH_LONG).show();
            }
        });

        ImageButton nearwarehouse = (ImageButton)findViewById(R.id.nearwarehouse);
        nearwarehouse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                view.startAnimation(myAnim);
                String uri = "geo:" + x + "," + y + "?q=bengkel+dealer+motor+di+sekitar+saya";
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });

        if(SessionHelper.getInstance(this).getAppFirstTime()){
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K001","Kerusakan Accu",
                    "-Bersihkan terlebih dahulu seluruh body aki kemudian buka penutup atas dengan mencukitnya.\n" +
                            "-Jika anda jumpai tutup karet yang menyumbat lubang aki bisa anda buka dengan mudah kemudian siapkan air zuur yang sudah anda masukkan kedalam tabung suntikan lalu masukkan cairan hingga sebatas 1cm di bawah lubang aki.\n" +
                            "-Tutup kembali lubang dengan karet penutup aslinya pastikan tidak ada kebocoran atau bahkan sekedar merembas dengan cara membalik badan aki.\n" +
                            "-Tutup kembali body aki seperti semula dan jangan lupa rekatkan dengan lem sampai benar benar aman dan tidak mudah terlepas.\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K002","Kerusakan Busi",
                    "-Melakukan pembersihan busi.\n" +
                            "-Memecahkan Keramik Pemantik Busi\n" +
                            "-Membakar Bagian Elektroda\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K003","Kerusakan Celah Klep",
                    "-Setel klep dengan benar.\n" +
                            "-Untuk matik  lebih longgar 0.02 mm dari bebek, misalnya 0,08-0,1 mm dan 0,10–0,20 mm untuk in dan out\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K004","Keruskan Injektor",
                    "-Memperbaiki Injektor Dengan Injektor Clener.\n" +
                            "-Memperbaiki Injektor Dengan Infus.\n" +
                            "-Memperbaiki Injektor Dengan Carbon Cleaner.\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K005","Kerusakan blok CVT",
                    "-Untuk mencegah Rumah Roller tidak cepat aus maka beri pelumasan gemuk pada alur roller.\n" +
                            "-Pada saat penggantian roller, komponen slide pieces ini juga sebaiknya diganti.\n" +
                            "-Penggantian v-belt yang tepat waktu sesuai periode.\n" +
                            "-Jika kampas kopling habis maka solusinya ganti kampas kopling.\n" +
                            "-Penggantian oli gearbox secara rutin.\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K006","Kerusakan ECM",
                    "-Pastikan tidak ada kode kerusakan yang tersimpan di ECM atau ECU, jika masalah kode kerusakan masih ada berarti langkah ini bakalan gatot alias gagal total.\n" +
                            "-Kunci kontak posisi off, kemudian sambungkan DLC dengan SCS Connector.\n" +
                            "-Putar grip gas sampai putaran penuh dan putar kunci kontak ke posisi ON.\n" +
                            "-Biarkan lampu MIL berkedip kedip dengan cepat, dalam waktu kurang dari 5 detik lepaskan grip gas.\n" +
                            "-Perhatikan lampu MIL apakah berkedip lebih pelan tidak seperti kedipan cepat tadi, dengan jumlah kedipan pendek sebanyak satu kali. Jika iya berarti langkah ini berhasil.\n"));
            SQLiteHelper.getInstance(this).addKerusakan(new Kerusakan("K007","Kerusakan Radiator",
                    "-Jika radiator tersumbat maka buka kancingan upper tank lalu buka sil upper tank dan gunakan kawat tipis untuk menusuk aatu membersihkan satu per satu saluran air radiator dari kerak yang menyumbat.\n" +
                            "-Jika radiator bocor maka segera untuk menggantinya agar air radiator tidak merembes ke dalam ruangan mesin sehingga mengakibatkan overhead.\n"));

            SQLiteHelper.getInstance(this).addGejala(new Gejala("G001","1. Distater listrik tidak menyala"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G002","2. Klakson tidak bunyi"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G003","3. Lampu sein dan lampu utama mati"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G004","4. Kelistrikan mati"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G005","5. Distater manual tidak nyala"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G006","6. Suara knalpot sering meletus-meletus"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G007","7. Tarikan gas terasa berat"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G008","8. Keluar asap putih pada knalpot"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G009","9. Mesin mudah panas"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G009","10. Bahan bakar boros"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G010","11. Bunyi krecek-krecek pada bagian depan CVT"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G011","12. Bunyi klok-klok pada saat motor statione"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G012","13. Bunyi berdecit pada bagian belakang CVT"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G013","14. Getaran pada bagian belakang CVT"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G014","15. Bunyi mendengung saat gas ditutup"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G015","16. Lari motor tersendat-sendat"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G016","17. Lampu MIL berkedip terus"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G017","18. Putaran stasioner tidak stabil"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G018","19. Motor sulit menyala"));
            SQLiteHelper.getInstance(this).addGejala(new Gejala("G017","20. Bergelembung saat buka tutup radiator"));


            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K001","G001,G002,G003,G004"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K001","G004,G004"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K002","G001,G004,G005,G006"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K002","G006,G006"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K003","G007,G008"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K003","G008,G008"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K004","G001,G007,G010"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K004","G010,G010"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K005","G007,G011,G012,G013,G014,G015"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K005","G015,G015"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K006","G016,G017,G018"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K006","G018,G018"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K007","G009,G019,G020"));
            SQLiteHelper.getInstance(this).addKeputusan(new Keputusan("K007","G020,G020"));


            SessionHelper.getInstance(this).setAppFirstTime(false);
        }
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Apa Anda yakin ingin keluar?").setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.finish();
            }
        }).setNegativeButton("Tidak", null).show();
    }
}
