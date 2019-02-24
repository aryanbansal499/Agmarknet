package e.aryan.agmarknet.Models;

public class Commodity {

    private String CommodityName;
    private  int comm_code;


    public Commodity(String CommodityName, int comm_code){
        this.CommodityName = CommodityName;
        this.comm_code = comm_code;
    }

    public String getCommodityName() {
        return CommodityName;
    }

    public int getComm_code() {
        return comm_code;
    }



}
