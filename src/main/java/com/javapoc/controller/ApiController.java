package com.javapoc.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.javapoc.service.ChangeService;
import com.javapoc.service.DisplayService;
import com.javapoc.service.HistoryService;
import com.javapoc.service.LoginService;
import com.javapoc.service.ReviewService;
import com.javapoc.service.SearchService;
import com.javapoc.service.WriteService;
import com.javapoc.serviceImpl.ChangeServiceImpl;
import com.javapoc.serviceImpl.DisplayServiceImpl;
import com.javapoc.serviceImpl.HistoryServiceImpl;
import com.javapoc.serviceImpl.LoginServiceImpl;
import com.javapoc.serviceImpl.ReviewServiceImpl;
import com.javapoc.serviceImpl.SearchServiceImpl;
import com.javapoc.serviceImpl.WriteServiceImpl;

@Path("/rms")
public class ApiController {
	
	@Path("/validate-user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String validateUser(String input) {
		LoginService lsi = new LoginServiceImpl();
		String output = lsi.validateUser(input);
		return output;
	}
	
	@Path("/save-data-to-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveValues(String input) {
		WriteService wsi = new WriteServiceImpl();
		String output = wsi.saveValuesToDB(input);
		return output;
	}
	
	@Path("/load-data-from-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String loadValues(String input) {
		WriteService wsi = new WriteServiceImpl();
		String output = wsi.loadValuesFromDB(input);
		return output;
	}
	
	@Path("/submit-data-to-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String submitValues(String input) {
		WriteService wsi = new WriteServiceImpl();
		String output = wsi.submitValuesToDB(input);
		return output;
	}
	
	@Path("/clear-data-in-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String clearValues(String input) {
		WriteService wsi = new WriteServiceImpl();
		String output = wsi.clearValuesInDB(input);
		return output;
	}
	
	@Path("/review-data-from-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String reviewValues(String input) {
		ReviewService rsi = new ReviewServiceImpl();
		String output = rsi.ReviewValuesFromDB(input);
		return output;
	}
	
	@Path("/show-review-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String showReviewValues(String input) {
		ReviewService rsi = new ReviewServiceImpl();
		String output = rsi.showValuesFromDB(input);
		return output;
	}
	
	@Path("/approve-review-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String approveReviewValues(String input) {
		ReviewService rsi = new ReviewServiceImpl();
		String output = rsi.approveValuesInDB(input);
		return output;
	}
	
	@Path("/reject-review-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String rejectReviewValues(String input) {
		ReviewService rsi = new ReviewServiceImpl();
		String output = rsi.rejectValuesInDB(input);
		return output;
	}
	
	@Path("/modify-review-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String modifyReviewValues(String input) {
		ReviewService rsi = new ReviewServiceImpl();
		String output = rsi.modifyValuesInDB(input);
		return output;
	}	
	
	@Path("/display-data-from-db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String displayValues() {
		DisplayService dsi = new DisplayServiceImpl();
		String output = dsi.DisplayValuesFromDB();
		return output;
	}
	
	@Path("/search-data-from-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String searchValues(String input) {
		SearchService ssi = new SearchServiceImpl();
		String output = ssi.SearchValuesFromDB(input);
		return output;
	}
	
	@Path("/change-data-from-db")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String changeValues() {
		ChangeService csi = new ChangeServiceImpl();
		String output = csi.ChangeValuesFromDB();
		return output;
	}
	
	@Path("/show-change-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String showChangeValues(String input) {
		ChangeService csi = new ChangeServiceImpl();
		String output = csi.showValuesFromDB(input);
		return output;
	}
	
	@Path("/edit-change-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editChangeValues(String input) {
		ChangeService csi = new ChangeServiceImpl();
		String output = csi.editValuesInDB(input);
		return output;
	}
	
	@Path("/history-data-from-db")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String historyValues(String input) {
		HistoryService hsi = new HistoryServiceImpl();
		String output = hsi.historyValuesFromDB(input);
		return output;
	}
	
	@Path("/show-history-data")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String showHistoryValues(String input) {
		HistoryService hsi = new HistoryServiceImpl();
		String output = hsi.showValuesFromDB(input);
		return output;
	}
	
}
