package e.aryan.agmarknet.Models;

import java.util.Date;

public class Arrival {

    private int market_center_code;
    private Date date_arrival;
    private int comm_code;
    private float arrival_std_unit;
    private String general_trend;
    private String GIS_Data
    private Date data_update;
    private String Dflag;


    public Arrival(int market_center_code, Date date_arrival, int comm_code, float arrival_std_unit, String general_trend, String GIS_Data, Date data_update, String dflag) {
        this.market_center_code = market_center_code;
        this.date_arrival = date_arrival;
        this.comm_code = comm_code;
        this.arrival_std_unit = arrival_std_unit;
        this.general_trend = general_trend;
        this.GIS_Data = GIS_Data;
        this.data_update = data_update;
        Dflag = dflag;
    }

    public int getMarket_center_code() {
        return market_center_code;
    }

    public void setMarket_center_code(int market_center_code) {
        this.market_center_code = market_center_code;
    }

    public Date getDate_arrival() {
        return date_arrival;
    }

    public void setDate_arrival(Date date_arrival) {
        this.date_arrival = date_arrival;
    }

    public int getComm_code() {
        return comm_code;
    }

    public void setComm_code(int comm_code) {
        this.comm_code = comm_code;
    }

    public float getArrival_std_unit() {
        return arrival_std_unit;
    }

    public void setArrival_std_unit(float arrival_std_unit) {
        this.arrival_std_unit = arrival_std_unit;
    }

    public String getGeneral_trend() {
        return general_trend;
    }

    public void setGeneral_trend(String general_trend) {
        this.general_trend = general_trend;
    }

    public String getGIS_Data() {
        return GIS_Data;
    }

    public void setGIS_Data(String GIS_Data) {
        this.GIS_Data = GIS_Data;
    }

    public Date getData_update() {
        return data_update;
    }

    public void setData_update(Date data_update) {
        this.data_update = data_update;
    }

    public String getDflag() {
        return Dflag;
    }

    public void setDflag(String dflag) {
        Dflag = dflag;
    }
}

