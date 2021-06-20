package com.example.serivces;

import java.util.List;

import com.example.domain.response.GetAtmDetails;

public interface IATMService {
	
	List<GetAtmDetails> getAtmList() throws Exception;

}
