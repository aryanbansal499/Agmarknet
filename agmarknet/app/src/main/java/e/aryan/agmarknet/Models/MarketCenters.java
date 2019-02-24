package e.aryan.agmarknet.Models;

public class MarketCenters {

    private String MarketName;
    private int DistrictCode;
    private String StateCode;


    public String getMarketName() {
        return MarketName;
    }

    public int getDistrictCode() {
        return DistrictCode;
    }

    public String getStateCode() {
        return StateCode;
    }

    public MarketCenters(String MarketName, int DistrictCode, String StateCode) {
        this.MarketName = MarketName;
        this.DistrictCode = DistrictCode;
        this.StateCode = StateCode;
    }
}
