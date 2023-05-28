package com.sandeep.retailstore.service;

import com.sandeep.retailstore.model.InvoiceRequestModel;
import com.sandeep.retailstore.model.InvoiceResponseModel;

public interface InvoiceService {


	InvoiceResponseModel generateInvoice(InvoiceRequestModel requestModel);

}