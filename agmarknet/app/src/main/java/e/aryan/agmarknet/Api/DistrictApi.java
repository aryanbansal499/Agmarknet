package e.aryan.agmarknet.Api;

import java.util.List;

import e.aryan.agmarknet.Models.District;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DistrictApi {

    String URL = "https://api.jsonbin.io/b/";

    @GET("5c4d6d1015735a2542357dd9")
    Call<List<District>> getDistrictData();

}
