package e.aryan.agmarknet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import e.aryan.agmarknet.Api.CommodityApi;
import e.aryan.agmarknet.Models.Commodity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class StateWise extends WeekTabs {

    Commodity com = new Commodity("Select Commodity",0);
    private String dateType ;
    private String commodityName;
    private int comm_code;
    private RadioGroup rg;
    private RadioButton radioButtonArrival;
    private RadioButton radioButtonPrice;
    private String typeOfChoice;
    private Button setDate;
    private TextView txtDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Graphs g;

    public StateWise() {

    }

    public void setTypeOfChoice(String typeOfChoice) {
        this.typeOfChoice = typeOfChoice;
       //Log.d("TypeofChoice ",typeOfChoice);
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
        //Log.d("selected commodity :" ,commodityName);
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
        }

    public String getCommodityName() { return commodityName; }

    public String getDateType() {return dateType; }

    public String getTypeOfChoice() {return typeOfChoice;}

    public int getComm_code() {
        return comm_code;
    }

    public void setComm_code(int comm_code) {
        this.comm_code = comm_code;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_tab1, container, false);
        final Spinner spinner1 = view.findViewById(R.id.tb1commodity_spinner);
        rg = view.findViewById(R.id.GrpBtn1);
        radioButtonArrival = view.findViewById(R.id.arrivalBtn1);
        radioButtonPrice = view.findViewById(R.id.priceBtn1);
        String data = getDateType();
        setDate = view.findViewById(R.id.setDatebtn);
        txtDate = view.findViewById(R.id.tv1);
        final int id1 = R.id.arrivalBtn1;
        final int id2 = R.id.priceBtn1;



        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnTypeChanged(group,checkedId,id1,id2);
                getCommodityData(spinner1);
                setDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                        final int month1 = 1;

                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                        String lengthOfTime = getDateType();
                                        putDate(lengthOfTime,month,year,day,dateFormat,txtDate);
                                    }
                                },  year, month, dayOfMonth);

                        //datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        datePickerDialog.show();


                    }
                });
                //g = new Graphs(getDateType(),getCommodityName(),getTypeOfChoice());
            }
        });

        // send button txtdate and dateFormat

        return view;
    }

    //get the commodity name as string from this method.
    public void getCommodityData(final Spinner spin) {
        Retrofit retrofit = BuildRetrofit(CommodityApi.URL);
        CommodityApi api = retrofit.create(CommodityApi.class);

        //Toast.makeText(getContext(),dateType,Toast.LENGTH_LONG).show();

        Call<List<Commodity>> call = api.getCommodityData();
        Graphs g;


        call.enqueue(new Callback<List<Commodity>>() {
            @Override
            public void onResponse(Call<List<Commodity>> call, Response<List<Commodity>> response) {

                final List<Commodity> commodities = response.body();
                commodities.add(0, com);

                String[] commodityNames = new String[commodities.size() + 1];
                //commodityNames[0] = "Select Commodity"
                for (int i = 0; i < commodities.size(); i++) {
                    commodityNames[i] = commodities.get(i).getCommodityName();

                }
                spin.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, commodityNames));
                for (Commodity obj : commodities) {
                    Log.d("CommodityName", obj.getCommodityName());
                }
                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String commodity = parent.getItemAtPosition(position).toString();
                        Log.d("selected commodity",commodity);
                        Toast.makeText(parent.getContext(),"Selected : "+ commodity,Toast.LENGTH_LONG).show();
                        int flag = 0;
                        Log.d("onitemDate",getTypeOfChoice());
                        while(flag == 0 && commodity != "Select Commodity") {
                            setCommodityName(commodity);
                            Log.d("getCom", getCommodityName());
                            //Log.d("onitemDate",getTypeOfChoice());
                            flag += 1;
                            }
                            //get the commodity code wrt the commodity name.
                            putComm_code(commodities,commodityName);
                        }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(parent.getContext(),"Please Select the Following",Toast.LENGTH_LONG).show();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Commodity>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void putComm_code(List<Commodity> commodities, String commodityName){

        for (Commodity obj : commodities){
            if (obj.getCommodityName().equals(commodityName)){
                setComm_code(obj.getComm_code());
                Log.d("comm_code",""+ getComm_code());
            }
        }
    }



}
