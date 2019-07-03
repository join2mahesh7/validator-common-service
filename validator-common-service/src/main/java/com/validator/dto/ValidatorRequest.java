package com.validator.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ValidatorRequest{
	private Data _data;

    private int _campaignId;

    private boolean _responsePayload;

    private List<RequestOptions> _importOptions; 

}
