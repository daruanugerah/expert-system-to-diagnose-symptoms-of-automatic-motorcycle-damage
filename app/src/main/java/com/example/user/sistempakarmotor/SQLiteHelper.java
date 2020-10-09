package com.example.user.sistempakarmotor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.sistempakarmotor.models.Gejala;
import com.example.user.sistempakarmotor.models.Keputusan;
import com.example.user.sistempakarmotor.models.Kerusakan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poscoict on 26/09/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    static SQLiteHelper sqh;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbpakarmotor";

    //TABLE
    private static final String TABLE_KERUSAKAN = "kerusakan";
    private static final String TABLE_GEJALA = "gejala";
    private static final String TABLE_KEPUTUSAN = "keputusan";

    //KOLOM
    private static final String KEY_ID = "id";
    private static final String KEY_PID = "pid";
    private static final String KEY_GID = "gid";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_CARA = "cara";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";

    public static SQLiteHelper getInstance(Context context){
        if(sqh == null){
            sqh = new SQLiteHelper(context);
        }

        return sqh;
    }

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_KERUSAKAN_TBL = "CREATE TABLE " + TABLE_KERUSAKAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PID + " TEXT,"
                + KEY_NAMA + " TEXT,"
                + KEY_CARA + " TEXT" + ")";

        String CREATE_GEJALA_TBL = "CREATE TABLE " + TABLE_GEJALA + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_GID + " TEXT,"
                + KEY_NAMA + " TEXT" + ")";

        String CREATE_KEPUTUSAN_TBL = "CREATE TABLE " + TABLE_KEPUTUSAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_PID + " TEXT,"
                + KEY_GID + " TEXT" + ")";

        db.execSQL(CREATE_KERUSAKAN_TBL);
        db.execSQL(CREATE_GEJALA_TBL);
        db.execSQL(CREATE_KEPUTUSAN_TBL);
    }

    public void createTable2(SQLiteDatabase db2) {
        db2.execSQL("DROP TABLE IF EXISTS penyakit");
        db2.execSQL("CREATE TABLE if not exists penyakit (kode_kerusakan TEXT PRIMARY KEY , " +
                "nama_kerusakan TEXT, deskripsi TEXT, penanganan TEXT);");
    }

    public void generateData2(SQLiteDatabase db2) {
        db2.execSQL("INSERT INTO penyakit VALUES ('K001', 'Kerusakan Accu', 'Accumulator adalah komponen penyimpan arus listrik yang biasa digunakan untuk menyalakan sebuah rangkaian kelistrikan ditempat dimana tidak ada sumber listrik.', " +
                "'-Bersihkan terlebih dahulu seluruh body aki kemudian buka penutup atas dengan mencukitnya.\n" +
                "-Jika anda jumpai tutup karet yang menyumbat lubang aki bisa anda buka dengan mudah kemudian siapkan air zuur yang sudah anda masukkan kedalam tabung suntikan lalu masukkan cairan hingga sebatas 1cm di bawah lubang aki.\n" +
                "-Tutup kembali lubang dengan karet penutup aslinya pastikan tidak ada kebocoran atau bahkan sekedar merembas dengan cara membalik badan aki.\n" +
                "-Tutup kembali body aki seperti semula dan jangan lupa rekatkan dengan lem sampai benar benar aman dan tidak mudah terlepas.\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K002', 'Kerusakan Busi', 'Busi adalah suatu suku cadang yang dipasang pada mesin pembakaran dalam dengan ujung elektrode pada ruang bakar. Busi dipasang untuk membakar bensin yang telah dikompres oleh piston.', " +
                "'-Melakukan pembersihan busi\n" +
                "-Memecahkan Keramik Pemantik Busi\n" +
                "-Membakar Bagian Elektroda\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K003', 'Kerusakan Celah Klep', 'Klep atau bisa disebut valve adalah suatu alat atau bagian dari mesin motor yang bersifat dinamis yang terpasang pada kepala silinder.', " +
                "'-Setel klep dengan benar\n" +
                "-Untuk matik  lebih longgar 0.02 mm dari bebek, misalnya 0,08-0,1 mm dan 0,10–0,20 mm untuk in dan out\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K004', 'Kerusakan Injector', 'Injeksi membutuhkan perangkat bernama injector, yang bertugas me-nyuplai campuran bahan bakar dengan udara. Sistem injeksi merupakan teknologi penerus sistem karburator pada kendaraan bermotor.', " +
                "'-Memperbaiki Injektor Dengan Injektor Clener\n" +
                "-Memperbaiki Injektor Dengan Infus\n" +
                "-Memperbaiki Injektor Dengan Carbon Cleaner\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K005', 'Kerusakan CVT', 'CVT merupakan cara paling fleksibel dalam memindahkan tenaga yang dihasilkan mesin kepada roda-roda kendaraan.', " +
                "'-Untuk mencegah Rumah Roller tidak cepat aus maka beri pelumasan gemuk pada alur roller.\n" +
                "-Pada saat penggantian roller, komponen slide pieces ini juga sebaiknya diganti.\n" +
                "-Penggantian v-belt yang tepat waktu sesuai periode.\n" +
                "-Jika kampas kopling habis maka solusinya ganti kampas kopling.\n" +
                "-Penggantian oli gearbox secara rutin.\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K006', 'Kerusakan ECM', 'Engine Control Module (ECM) merupakan nama lain dari Engine Control Unit (ECU) atau dalam bahasa Indonesia Unit Kontrol Mesin adalah teknologi yang membatasi penggunaan bahan bakar tanpa mengurangi kinerja mesin.', " +
                "'-Pastikan tidak ada kode kerusakan yang tersimpan di ECM atau ECU, jika masalah kode kerusakan masih ada berarti langkah ini bakalan gatot alias gagal total.\n" +
                "-Kunci kontak posisi off, kemudian sambungkan DLC dengan SCS Connector.\n" +
                "-Putar grip gas sampai putaran penuh dan putar kunci kontak ke posisi ON.\n" +
                "-Biarkan lampu MIL berkedip kedip dengan cepat, dalam waktu kurang dari 5 detik lepaskan grip gas.\n" +
                "-Perhatikan lampu MIL apakah berkedip lebih pelan tidak seperti kedipan cepat tadi, dengan jumlah kedipan pendek sebanyak satu kali. Jika iya berarti langkah ini berhasil.\n' );");

        db2.execSQL("INSERT INTO penyakit VALUES ('K007', 'Kerusakan Radiator', 'Radiator adalah alat penukar panas yang digunakan untuk memindahkan energi panas dari satu medium ke medium lainnya yang tujuannya untuk mendinginkan maupun memanaskan. Radiator yang kita kenal pada umumnya digunakan pada kendaraan bermotor (roda dua atau roda empat).', " +
                "'-Jika radiator tersumbat maka buka kancingan upper tank lalu buka sil upper tank dan gunakan kawat tipis untuk menusuk aatu membersihkan satu per satu saluran air radiator dari kerak yang menyumbat.\n" +
                "-Jika radiator bocor maka segera untuk menggantinya agar air radiator tidak merembes ke dalam ruangan mesin sehingga mengakibatkan overhead.\n' );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KERUSAKAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEPUTUSAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GEJALA);

        onCreate(db);
    }

    //KERUSAKAN
    public void addKerusakan(Kerusakan kerusakan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_PID, kerusakan.getPid());
        cv.put(KEY_NAMA, kerusakan.getNama());
        cv.put(KEY_CARA, kerusakan.getCara());

        db.insert(TABLE_KERUSAKAN, null ,cv);
        db.close();
    }

    public Kerusakan getKerusakan(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KERUSAKAN, new String[] {KEY_ID,KEY_PID, KEY_NAMA, KEY_CARA}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Kerusakan kerusakan = new Kerusakan(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return kerusakan;
    }

    public Kerusakan getKerusakan(String keyset){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KERUSAKAN, new String[] {KEY_ID,KEY_PID, KEY_NAMA, KEY_CARA}, KEY_PID + "=?", new String[] { keyset }, null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Kerusakan kerusakan = new Kerusakan(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return kerusakan;
    }

    public List<Kerusakan> getAllKerusakan(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Kerusakan> kerusakans = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_KERUSAKAN;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Kerusakan kerusakan = new Kerusakan(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                kerusakans.add(kerusakan);
            } while (cursor.moveToNext());
        }

        return kerusakans;
    }

    //GEJALA
    public void addGejala(Gejala gejala){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_GID, gejala.getGid());
        cv.put(KEY_NAMA, gejala.getGejala());

        db.insert(TABLE_GEJALA, null ,cv);
        db.close();
    }

    public Gejala getGejala(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GEJALA, new String[] {KEY_ID,KEY_GID, KEY_NAMA}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Gejala gejala = new Gejala(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

        return gejala;
    }

    public List<Gejala> getAllGejala(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Gejala> gejalas = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_GEJALA;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Gejala gejala = new Gejala(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                gejalas.add(gejala);
            } while (cursor.moveToNext());
        }

        return gejalas;
    }

    //KEPUTUSAN
    public void addKeputusan(Keputusan keputusan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_GID, keputusan.getGid());
        cv.put(KEY_PID, keputusan.getPid());

        db.insert(TABLE_KEPUTUSAN, null ,cv);
        db.close();
    }

    public Keputusan getKeputusan(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KEPUTUSAN, new String[] {KEY_ID,KEY_GID, KEY_PID}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        Keputusan putusan = new Keputusan(cursor.getInt(0), cursor.getString(1), cursor.getString(2));

        return putusan;
    }

    public List<Keputusan> getAllKeputusan(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Keputusan> keputusans = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_KEPUTUSAN;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                Keputusan keputusan = new Keputusan(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                keputusans.add(keputusan);
            } while (cursor.moveToNext());
        }

        return keputusans;
    }
}
