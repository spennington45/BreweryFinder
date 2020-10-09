package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BrewerRequestDAO;
import com.techelevator.model.BrewerRequest;

@CrossOrigin
@RestController
public class BrewerRequestController {

	@Autowired
	public BrewerRequestDAO dao;
	
	@RequestMapping(path ="/processedStatus", method = RequestMethod.PUT)
	public void processStatus( @RequestBody BrewerRequest brewerRequest ) {
		
		dao.switchProcessedStatus(brewerRequest);
		
	}
	
	@RequestMapping(path ="/setBrewerToBrewery", method = RequestMethod.PUT)
	public void setBrewerToBrewery( @RequestBody BrewerRequest brewerRequest ) {
		
		dao.updateBrewerIdForBrewery(brewerRequest);
		
	}
	
	
}
