package e.aryan.agmarknet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import e.aryan.agmarknet.Api.DistrictApi;
import e.aryan.agmarknet.Api.MarketApi;
import e.aryan.agmarknet.Api.StatesApi;
import e.aryan.agmarknet.Api.CommodityApi;
import e.aryan.agmarknet.Models.Commodity;
import e.aryan.agmarknet.Models.District;
import e.aryan.agmarknet.Models.MarketCenters;
import e.aryan.agmarknet.Models.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("ValidFragment")
public class WeekTabs extends Fragment  {


   // private String dateType ;

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    private Date maxDate;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    private Date fromDate;

    private String typeOfChoice;
    public void setTypeOfChoice(String typeOfChoice) {
        this.typeOfChoice = typeOfChoice;
        //Log.d("TypeofChoice ",typeOfChoice);
    }

    public String getTypeOfChoice() {return typeOfChoice;}

    private String typeOfData;

    public Retrofit BuildRetrofit(String Url){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public void putDate(String lengthOfTime,int month , int year , int day, DateFormat dateFormat,TextView txtDate){
        //String lengthOfTime = getDateType();

        Calendar cal = Calendar.getInstance();
        month = month+1;
        String set = day + "/" + month + "/" + year;
        long dayDiff;
        if(lengthOfTime.equals("Weekly")) {

            dayDiff = 604800000L;
            dateHelper(dayDiff,set,dateFormat,txtDate);

        }
        else if (lengthOfTime.equals("Monthly")){
            dayDiff = 2592000000L;
            dateHelper(dayDiff,set,dateFormat,txtDate);
        }
        Log.d("Month" ,"get" +month);
    }

    public void dateHelper(long dayDiff, String set, DateFormat dateFormat,TextView txtDate){

        try {

            Date maxDate = new SimpleDateFormat("dd/MM/yyyy").parse(set);
            setMaxDate(maxDate);
            Log.d("Till","till" +maxDate);
            //cal.add(day,-dayDiff);
            Date fromDate = new Date (maxDate.getTime()-dayDiff);
            setFromDate(fromDate);
            //StateWise s = new StateWise(maxDate,fromDate);
            //String formatFrom = dateFormat.format(fromDate);
            Log.d("From","from "+ fromDate);
            txtDate.setText("From "+ dateFormat.format(fromDate) + " Till "+ dateFormat.format(maxDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    //helper function to set the the type of choice i.e by arrival or by price.
    public void doOnTypeChanged(RadioGroup group,int checkedId,int id1, int id2){
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId == id1){
            Toast.makeText(this.getContext(),"You Choose Arrival",Toast.LENGTH_SHORT).show();
            setTypeOfChoice("Arrival");
            Log.d("Ttt",getTypeOfChoice());
        }
        if(checkedRadioId == id2){
            Toast.makeText(this.getContext(),"You Choose Price",Toast.LENGTH_SHORT).show();
            setTypeOfChoice("Price");
        }
    }


}
