package com.validator.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Eshcheadertrans {
	
	private String confirmation_number;

    private String eshc_recipientid;

    private String firstname;

    private String client_operationalid;

    private String gender;

    private String client_cobrand_type;

    private String city;

    private String language;

    private String zip4;

    private String client_cobrand_code;

    private String suffix;

    private String state;

    private String email;

    private String timestamp;

    private String zip;

    private String communicationid;

    private String groupid;

    private String contract;

    private String middlename;

    private String web_address;

    private String number_rx;

    private String phiindicator;

    private String lastname;

    private String brand_id;

    private String recipient_type;

    private String client_cobrand_name;

    private String order_date;

    private String environment;

    private String carrier;

    private String dob;

    private String addressline3;

    private String order_source;

    private String addressline2;

    private String addressline1;
    private String cust_svc_phone;
    
    private List<EshcxTransEshcHeaderTrans> eshc_rx_trans_to_eshc_headertrans;
    private HeadertransPatientRecip headertrans_to_patientrecip;


}
