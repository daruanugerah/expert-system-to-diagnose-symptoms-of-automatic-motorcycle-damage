package com.example.user.sistempakarmotor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class InfoKerusakanActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    public static ArrayList<InfoKerusakanModel> data;
    ArrayList<String> namakerusakanlist;
    ArrayList<String> deskripsilist;
    ArrayList<String> solutioncombinelist;
    SearchView searchView;
    LinkedHashMap namelist;
    LinkedHashMap title;

    private SQLiteDatabase db2;
    private SQLiteHelper datakerusakan;
    private Cursor kerusakanCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_kerusakan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informasi Kerusakan");
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);*/
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Pencarian");
        searchView.setQueryRefinementEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<InfoKerusakanModel>();
        datakerusakan = new SQLiteHelper(this);
        db2 = datakerusakan.getWritableDatabase();
        datakerusakan.createTable2(db2);
        datakerusakan.generateData2(db2);

        fetchData();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {return  false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                final ArrayList<InfoKerusakanModel> filteredList = new ArrayList<InfoKerusakanModel>();
                for (int i = 0; i < namakerusakanlist.size(); i++) {
                    final String text = namakerusakanlist.get(i).toLowerCase();
                    if (text.contains(newText)) {
                        filteredList.add(new InfoKerusakanModel(namakerusakanlist.get(i),deskripsilist.get(i),solutioncombinelist.get(i)));
                    }
                }
                adapter = new CustomAdapterInfoKerusakan(filteredList);
                recyclerView.setAdapter(adapter);
                return true;
            }
        });
    }

    public void fetchData() {

        namelist=new LinkedHashMap<>();
        title=new LinkedHashMap<>();

        int a,b,c;
        kerusakanCursor = db2.query("penyakit" ,null, null, null, null, null, null);
        a=kerusakanCursor.getColumnIndex("nama_kerusakan");
        b=kerusakanCursor.getColumnIndex("deskripsi");
        c=kerusakanCursor.getColumnIndex("penanganan");

        namakerusakanlist=new ArrayList<String>();
        deskripsilist= new ArrayList<String>();
        solutioncombinelist= new ArrayList<String>();

        while (kerusakanCursor.moveToNext()){
            title.put(kerusakanCursor.getString(a),"");
            namelist.put(kerusakanCursor.getString(b), kerusakanCursor.getString(c));
        }
        Iterator entry = title.entrySet().iterator();
        Iterator entries = namelist.entrySet().iterator();

        while (entries.hasNext()&& entry.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            Map.Entry thatEntry = (Map.Entry) entry.next();
            namakerusakanlist.add(String.valueOf(thatEntry.getKey()));
            deskripsilist.add(String.valueOf(thisEntry.getKey()));
            solutioncombinelist.add(String.valueOf(thisEntry.getValue()));
        }

        for (int i = 0; i < namakerusakanlist.size(); i++) {
            data.add(new InfoKerusakanModel(namakerusakanlist.get(i), deskripsilist.get(i), solutioncombinelist.get(i)));
        }
        adapter = new CustomAdapterInfoKerusakan(data);
        recyclerView.setAdapter(adapter);
    }

}
