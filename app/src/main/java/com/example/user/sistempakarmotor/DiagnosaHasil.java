package com.example.user.sistempakarmotor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.user.sistempakarmotor.models.Keputusan;
import com.example.user.sistempakarmotor.models.Kerusakan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DiagnosaHasil extends AppCompatActivity {
    private String[] results;
    private HashMap<String, ArrayList<String>> chains = new HashMap<>();
    private TextView txtNamaKerusakan,txtSolusi;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa_hasil);
        getBundle();
        setupView();
        chainProcess();
        setupData();
    }

    private void setupView(){
        toolbar = (Toolbar)findViewById(R.id.toolBar);
        txtNamaKerusakan = (TextView)findViewById(R.id.txtNamaKerusakan);
        txtSolusi = (TextView) findViewById(R.id.txtSolusi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Hasil Diagnosa Gejala");

    }

    private void getBundle(){
        Bundle b = getIntent().getExtras();
        results = b.getStringArray("selectedItems");
    }

    private void chainProcess(){
        for(String code : results){
            for(Keputusan keputusan : SQLiteHelper.getInstance(this).getAllKeputusan()){
                if(keputusan.getGid().contains(code + ",")){
                    if(chains.containsKey(keputusan.getPid())){
                        chains.get(keputusan.getPid()).add(code);
                    } else {
                        ArrayList<String> str = new ArrayList<>();
                        str.add(keputusan.getGid().split(",").length + "");
                        str.add(code);
                        chains.put(keputusan.getPid(), str);
                    }
                }
            }
        }
    }

    private void setupData(){
        Set<String> keySet = chains.keySet();
        float top = -1;
        String keyset = "";
        for(String key : keySet){
            float ms = Float.parseFloat(chains.get(key).get(0));
            float ma = chains.get(key).size() - 1;
            float current = ma / ms * 100;
            if(current >= top){
                top = current;
                keyset = key;
            }
        }

        Kerusakan kerusakan = SQLiteHelper.getInstance(this).getKerusakan(keyset);
        txtNamaKerusakan.setText(kerusakan.getNama());
        txtSolusi.setText(kerusakan.getCara());
    }


}
