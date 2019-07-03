package com.validator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class HeadertransPatientRecip {
	private String environment;

    private String eshc_recipientid;

    private String groupid;

    private PatientrecipEmail patientrecip_to_email;

    private String email;

}
