package com.example.serivces;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.domain.response.GetAtmDetails;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ATMServiceImpl implements IATMService {
	
	private static final Logger logger = LoggerFactory.getLogger(ATMServiceImpl.class);
	
	@Autowired 
	RestTemplate restTemplate;
	
	@Value("${restUrl}")
	private String restUrl;
	
	@Override
	public List<GetAtmDetails> getAtmList() throws Exception {
		logger.info("GetAtmList Started");
		List<GetAtmDetails> myObjects = new ArrayList<>();
		try {
			String result = restTemplate.getForObject(restUrl, String.class);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			myObjects = objectMapper.readValue(result.substring(5), new TypeReference<List<GetAtmDetails>>() {
			});
			logger.info("GetAtmList End");
		} catch (Exception e) {
			throw e;
		}
		return myObjects;
	}

}
