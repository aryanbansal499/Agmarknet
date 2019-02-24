package e.aryan.agmarknet.Api;

import java.util.List;

import e.aryan.agmarknet.Models.Commodity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CommodityApi {

    String URL = "https://api.myjson.com/bins/";
    @GET("lu6tg")
    Call<List<Commodity>> getCommodityData();
}

