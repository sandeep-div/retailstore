package com.sandeep.retailstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.retailstore.model.InvoiceRequestModel;
import com.sandeep.retailstore.model.InvoiceResponseModel;
import com.sandeep.retailstore.model.ResponseBodyDTO;
import com.sandeep.retailstore.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController  {


	@Autowired
	private InvoiceService invoiceService;

	
	@PostMapping("/generate")
	public ResponseEntity<ResponseBodyDTO<InvoiceResponseModel>> generateInvoice(
			@RequestBody InvoiceRequestModel invoiceRequestModel ) {
		InvoiceResponseModel responseModel = new InvoiceResponseModel();
		try {
			 responseModel = invoiceService.generateInvoice(invoiceRequestModel);
				return new ResponseEntity<>(new ResponseBodyDTO<InvoiceResponseModel>(true,
						"Invoice generated successfully", responseModel), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(new ResponseBodyDTO<InvoiceResponseModel>(false,
					exception.getMessage(), responseModel), HttpStatus.OK);
		}
	}

}
