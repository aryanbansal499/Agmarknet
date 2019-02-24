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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import e.aryan.agmarknet.Api.DistrictApi;
import e.aryan.agmarknet.Api.MarketApi;
import e.aryan.agmarknet.Api.StatesApi;
import e.aryan.agmarknet.Models.Commodity;
import e.aryan.agmarknet.Models.District;
import e.aryan.agmarknet.Models.MarketCenters;
import e.aryan.agmarknet.Models.State;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@SuppressLint("ValidFragment")
public class MarketWise extends WeekTabs {

    private RadioGroup rg;
    private RadioButton radioButtonArrival;
    private RadioButton radioButtonPrice;

    private Button setDate;
    private TextView txtDate;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private String dateType ;
    boolean state_flag = false;
    District dis = new District("Select District","parent",0);
    MarketCenters mar = new MarketCenters("Select Market",0,"-");


    public String getDateType() {return dateType; }



    public void setDateType(String dateType) {
        this.dateType = dateType;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_tab2,container,false);
        final Spinner spinner2 = view.findViewById(R.id.tb2state_spinner);
        Spinner spinner3 = view.findViewById(R.id.tb2district_spinner);
        rg = view.findViewById(R.id.GrpBtn2);
        radioButtonArrival = view.findViewById(R.id.arrivalBtn2);
        radioButtonPrice = view.findViewById(R.id.priceBtn2);
        String data = getDateType();
        setDate = view.findViewById(R.id.setDatebtn2);
        txtDate = view.findViewById(R.id.tv2);
        final int id1 = R.id.arrivalBtn2;
        final int id2 = R.id.priceBtn2;

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                doOnTypeChanged(group,checkedId,id1,id2);
                //Log.d("marketType",getTypeOfChoice());
                //getCommodityData(spinner1);
                getStateData(spinner2);
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


        return view;
    }

    //grab the state code from state.
    public String getStateCode(List<State> states, String stateName){
        String StateCode = new String();
        for(State obj : states){
            if(obj.getStateName().equals(stateName)){
                StateCode = obj.getSTATE_CODE();
            }
        }
        return StateCode;
    }

    //get district code from state.
    public int putDistrictCode(List<District> district, String DistrictName){
        int DistrictCode = 0 ;
        for(District obj : district){
            if(obj.getDistrictName().equals(DistrictName)){
                DistrictCode = obj.getDistrictCode();
            }
        }
        return DistrictCode;
    }

    //load the state spinner
    public void getStateData(final Spinner spinner) {

        Retrofit retrofit = BuildRetrofit(StatesApi.BASE_URL);
        StatesApi statesApi = retrofit.create(StatesApi.class);

        Call<List<State>> call = statesApi.getStateData();

        call.enqueue(new Callback<List<State>>() {
            @Override
            public void onResponse(Call<List<State>> call, Response<List<State>> response) {

                final List<State> states = response.body();

                String[] stateNames = new String[states.size()];
                stateNames[0] = "Select State";
                for (int i = 1; i < states.size(); i++) {
                    stateNames[i] = states.get(i).getStateName();
                }
                spinner.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item, stateNames));

                for (State obj : states) {
                    Log.d("StateName", obj.getStateName());

                }



                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String state = parent.getItemAtPosition(position).toString();
                        Log.d("selected state",state);
                        Toast.makeText(parent.getContext(),"Selected : "+ state,Toast.LENGTH_LONG).show();
                        String StateCode = getStateCode(states,state);
                        Log.d("StateCDR", StateCode);
                        state_flag = true;
                        getDistrictData(StateCode);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(parent.getContext(),"Please Select the Following",Toast.LENGTH_LONG).show();

                    }
                });

            }

            @Override
            public void onFailure(Call<List<State>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //load the district spinner
    public void getDistrictData(final String StateCode) {


        Retrofit retrofit = BuildRetrofit(DistrictApi.URL);
        DistrictApi api = retrofit.create(DistrictApi.class);

        Call<List<District>> call = api.getDistrictData();

        call.enqueue(new Callback<List<District>>() {
            @Override
            public void onResponse(Call<List<District>> call, Response<List<District>> response) {

                List<District> districts = response.body();
                final ArrayList<District> district_names = new ArrayList<District>();

                if(state_flag ) {
                    district_names.add(0, dis);
                }
                for(District obj : districts) {
                    if(obj.getState_code().equals(StateCode)){
                        district_names.add(obj);

                    }
                }
                for(District obj : district_names){
                    Log.d("DistrictName", obj.getDistrictName());
                }
                String[] DNames = new String[district_names.size()];
                //commodityNames[0] = "Select Commodity"
                for(int i = 0;i<district_names.size();i++){
                    DNames[i] = district_names.get(i).getDistrictName();
                }

                Spinner spinner3  = getView().findViewById(R.id.tb2district_spinner);
                spinner3.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,DNames));
                spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String district = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(),"Selected : "+ district ,Toast.LENGTH_LONG).show();
                        int districtCode = putDistrictCode(district_names,district);
                        Log.d("district code is %d :","value" + districtCode);
                        getMarketData(StateCode,districtCode);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<District>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //load the market spinner.
    public void getMarketData(final String StateCode,final int districtCode) {



        Retrofit retrofit = BuildRetrofit(MarketApi.URL);
        MarketApi api = retrofit.create(MarketApi.class);

        Call<List<MarketCenters>> call = api.getMarketData();

        call.enqueue(new Callback<List<MarketCenters>>() {
            @Override
            public void onResponse(Call<List<MarketCenters>> call, Response<List<MarketCenters>> response) {

                List<MarketCenters> markets = response.body();
                ArrayList<MarketCenters> market_names = new ArrayList<MarketCenters>();

                market_names.add(0,mar);
                for(MarketCenters obj : markets) {
                    if((obj.getStateCode().equals(StateCode))&& (obj.getDistrictCode() == districtCode)){
                        market_names.add(obj);

                    }
                }
                for(MarketCenters obj : market_names){
                    Log.d("MarketName", obj.getMarketName()
                    );
                }
                String[] MNames = new String[market_names.size()];
                for(int i = 0;i<market_names.size();i++){
                    MNames[i] = market_names.get(i).getMarketName();
                }

                Spinner spinner4  = getView().findViewById(R.id.tb2market_spinner);
                spinner4.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,MNames));

            }

            @Override
            public void onFailure(Call<List<MarketCenters>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



}
