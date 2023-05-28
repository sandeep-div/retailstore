package com.sandeep.retailstore.model;

import java.util.List;

public class InvoiceRequestModel {

    private  List<ProductRequestModel> productRequestList;
    
    private Long userId;
	
	

	public List<ProductRequestModel> getProductRequestList() {
		return productRequestList;
	}

	public void setProductRequestList(List<ProductRequestModel> productRequestList) {
		this.productRequestList = productRequestList;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
