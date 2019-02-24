package e.aryan.agmarknet.Api;

import java.util.List;

import e.aryan.agmarknet.Models.Commodity;
import e.aryan.agmarknet.Models.State;
import retrofit2.Call;
import retrofit2.http.GET;

public interface StatesApi {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("mfmf8")
    Call<List<State>> getStateData();


}
// market price https://api.myjson.com/bins/aq3qm
//arrival https://api.myjson.com/bins/tu49a