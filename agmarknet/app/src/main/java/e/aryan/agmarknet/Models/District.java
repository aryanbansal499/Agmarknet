package e.aryan.agmarknet.Models;

public class District {

    private String DistrictName;
    private String state_code;
    private int DistrictCode;


    public String getDistrictName() {
        return DistrictName;
    }

    public String getState_code() {
        return state_code;
    }

    public int getDistrictCode() {
        return DistrictCode;
    }

    public District(String DistrictName, String state_code, int DistrictCode) {
        this.DistrictName = DistrictName;
        this.state_code = state_code;
        this.DistrictCode = DistrictCode;
    }
}