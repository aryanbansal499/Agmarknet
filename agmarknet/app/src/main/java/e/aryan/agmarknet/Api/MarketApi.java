package e.aryan.agmarknet.Api;

import java.util.List;

import e.aryan.agmarknet.Models.District;
import e.aryan.agmarknet.Models.MarketCenters;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MarketApi {

    String URL = "https://www.jasonbase.com/things/";
    @GET("Xgwr.json")
    Call<List<MarketCenters>> getMarketData();

}
