package com.javapoc.serviceImpl;

import com.javapoc.dao.ReviewDao;
import com.javapoc.daoImpl.ReviewDaoImpl;
import com.javapoc.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {

	@Override
	public String ReviewValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
        
		ReviewDao rdi = new ReviewDaoImpl();
        String records = null;
    	records = rdi.getReviewData(value);
		return records;
	}

	@Override
	public String showValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
		ReviewDao rdi = new ReviewDaoImpl();
        String records = null;
    	records = rdi.getShowReviewData(value);
		return records;
	}

	@Override
	public String approveValuesInDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		ReviewDao rdi = new ReviewDaoImpl();
        int result = rdi.approveReviewData(value);
		String records = null;
		if(result > 0){
    		records = rdi.getApproveData(value);
		}
		return records;
	}

	public String rejectValuesInDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		ReviewDao rdi = new ReviewDaoImpl();
        int result = rdi.rejectReviewData(value);
		String records = null;
		if(result > 0){
    		records = rdi.getRejectData(value);
		}
		return records;
	}

	@Override
	public String modifyValuesInDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		ReviewDao rdi = new ReviewDaoImpl();
        int result = rdi.modifyReviewData(value);
		String records = null;
		if(result > 0){
    		records = rdi.getModifyData(value);
		}    		
		return records;
	}

}
