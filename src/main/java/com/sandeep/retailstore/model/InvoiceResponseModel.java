package com.sandeep.retailstore.model;

import java.util.List;

public class InvoiceResponseModel {

    private List<ProductResponseModel> productResponseList;
    
    private double totalPercentageBasedDiscountAmount;
    
    private double totalAmountBasedDiscountAmount;
    
    private double totalInvoiceAmount;
    
    private double finalInvoiceAmount;
    
	

	public List<ProductResponseModel> getProductResponseList() {
		return productResponseList;
	}

	public void setProductResponseList(List<ProductResponseModel> productResponseList) {
		this.productResponseList = productResponseList;
	}

	public double getTotalPercentageBasedDiscountAmount() {
		return totalPercentageBasedDiscountAmount;
	}

	public void setTotalPercentageBasedDiscountAmount(double totalPercentageBasedDiscountAmount) {
		this.totalPercentageBasedDiscountAmount = totalPercentageBasedDiscountAmount;
	}

	public double getTotalAmountBasedDiscountAmount() {
		return totalAmountBasedDiscountAmount;
	}

	public void setTotalAmountBasedDiscountAmount(double totalAmountBasedDiscountAmount) {
		this.totalAmountBasedDiscountAmount = totalAmountBasedDiscountAmount;
	}

	public double getTotalInvoiceAmount() {
		return totalInvoiceAmount;
	}

	public void setTotalInvoiceAmount(double totalInvoiceAmount) {
		this.totalInvoiceAmount = totalInvoiceAmount;
	}

	public double getFinalInvoiceAmount() {
		return finalInvoiceAmount;
	}

	public void setFinalInvoiceAmount(double finalInvoiceAmount) {
		this.finalInvoiceAmount = finalInvoiceAmount;
	}
	
	

}
