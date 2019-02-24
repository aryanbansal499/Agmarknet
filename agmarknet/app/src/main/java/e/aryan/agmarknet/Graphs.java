package e.aryan.agmarknet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Graphs{

    StateWise st = new StateWise();
    private String datetype;
    private String CommodityName;
    private String Choice;
    private Button setDate;
    private TextView tv;

    public Graphs(String datetype,String CommodityName,String Choice){
        this.Choice = Choice;
        this.datetype = datetype;
        this.CommodityName = CommodityName;

        Log.d("Choice :" ,Choice);

        Log.d("DateType",datetype);

        Log.d("CommodityName",CommodityName);
    }








}
