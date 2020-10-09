package com.example.user.sistempakarmotor;

/**
 * Created by user on 22/10/2018.
 */

public class InfoKerusakanModel {

    String namakerusakan, deskripsi, solution;

    public InfoKerusakanModel(String namakerusakan, String deskripsi, String solution){
        this.namakerusakan = namakerusakan;
        this.deskripsi = deskripsi;
        this.solution = solution;
    }

    public  String getNamakerusakan()
    {
        return namakerusakan;
    }
    public  String getDeskripsi()
    {
        return deskripsi;
    }
    public String getSolution() {
        return solution;
    }
}
