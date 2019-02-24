package e.aryan.agmarknet.Models;

public class State {


//    private String STATE_CODE;
//    private String state_name;
//    private String email_sio;
//    private String email_dmi;
//    private String phone_sio;
//    private String send_flag;
//    private String state_name_hindi;
//    private String state_name_punjabi;
//    private String state_name_telugu;
//    private String state_name_marathi;
//    private String state_name_bengali;
//    private String state_name_assamese;
//    private String state_name_oriya;
//    private String state_name_tamil;
//    private String state_name_guj;
//    private String state_name_kannada1;
//    private String state_name_malayalam;
//    private String state_name_hindi_uni;
//    private String state_name_kannada;
//    private String passw;
//    private String langImpl;
//    private String email_state;
//    private String SAMB_email;
//    private String NIC_StateCor_Email;
//    private String SAMB1_email;
//    private String state_agrisec;
//    private String state_name_punjabi_uni;
//    private int state_code_lg;
//    private Date pms_update_date;

    private String StateName;
    private String STATE_CODE;


    public String getSTATE_CODE() {
        return STATE_CODE;
    }

    public State(String StateName, String STATE_CODE){
        this.StateName = StateName ;
        this.STATE_CODE = STATE_CODE;
    }

    public String getStateName() {
        return StateName;
    }


}
