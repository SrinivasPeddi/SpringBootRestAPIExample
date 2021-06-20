package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.response.GenericResponse;
import com.example.domain.response.GetAtmDetails;
import com.example.serivces.IATMService;

@RestController
public class AtmController {
	
	private static final Logger logger = LoggerFactory.getLogger(AtmController.class);

	@Autowired
    private IATMService atmService;
	
	@GetMapping(value = "/getAtmList")
    public ResponseEntity<GenericResponse<List<GetAtmDetails>>> getAtmList(){
		GenericResponse<List<GetAtmDetails>> genricResponse = new GenericResponse<List<GetAtmDetails>>();
        try{
        	List<GetAtmDetails> atmList = atmService.getAtmList();
        	genricResponse.setReposnse(atmList);
        	genricResponse.setStatus("Success");
        	return ResponseEntity.status(HttpStatus.OK).body(genricResponse);
        }catch(Exception e) {
        	logger.error("Error while getting Atm details", e);
        	genricResponse.setStatus("Failure");
        	genricResponse.setErrorMessage(e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genricResponse);
        }
    }
	
	@GetMapping(value = "/getAtmListOnCity/{city}")
    public ResponseEntity<GenericResponse<List<GetAtmDetails>>> getAtmListOnCity(@PathVariable("city") String city){
		GenericResponse<List<GetAtmDetails>> genricResponse = new GenericResponse<List<GetAtmDetails>>();
        try{
        	List<GetAtmDetails> atmList = atmService.getAtmList();
        	List<GetAtmDetails> result = atmList.stream()
                    .filter(atmDetails -> null != atmDetails.getAddress() && city.equalsIgnoreCase(atmDetails.getAddress().getCity()))
                    .collect(Collectors.toList());
        	genricResponse.setReposnse(result);
        	genricResponse.setStatus("Success");
        	return ResponseEntity.status(HttpStatus.OK).body(genricResponse);
        }catch(Exception e) {
        	logger.error("Error while getting Atm details based on city", e);
        	genricResponse.setStatus("Failure");
        	genricResponse.setErrorMessage(e.getMessage());
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(genricResponse);
        }
    }

}
