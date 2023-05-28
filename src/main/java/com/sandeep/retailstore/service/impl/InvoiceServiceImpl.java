package com.sandeep.retailstore.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.retailstore.entity.Product;
import com.sandeep.retailstore.entity.User;
import com.sandeep.retailstore.exception.ProductException;
import com.sandeep.retailstore.model.InvoiceRequestModel;
import com.sandeep.retailstore.model.InvoiceResponseModel;
import com.sandeep.retailstore.model.ProductRequestModel;
import com.sandeep.retailstore.model.ProductResponseModel;
import com.sandeep.retailstore.model.ProductType;
import com.sandeep.retailstore.model.UserType;
import com.sandeep.retailstore.repository.ProductRepository;
import com.sandeep.retailstore.repository.UserRepository;
import com.sandeep.retailstore.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public InvoiceResponseModel generateInvoice(InvoiceRequestModel requestModel) {
		InvoiceResponseModel invoiceResponseModel = new InvoiceResponseModel();
		List<ProductResponseModel> productResponseModels = new ArrayList<>();
        Double totalAmount = 0.0; 
        Double totalPercentageBasedDiscount = 0.0; 
		Long percentageBasedDiscount = generatePercentageBasedDiscount(requestModel.getUserId());

		for (ProductRequestModel productRequestModel : requestModel.getProductRequestList()) {
			ProductResponseModel productResponseModel = new ProductResponseModel();
			Optional<Product> productOptional = productRepository.findById(productRequestModel.getId());
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				productResponseModel.setProductId(product.getId());
				productResponseModel.setProductName(product.getName());
				productResponseModel.setQuantity(productRequestModel.getQuantity());
				productResponseModel.setUnitPrice(product.getUnitPrice());
				Double price = productRequestModel.getQuantity() * product.getUnitPrice();
				totalAmount +=price;
				productResponseModel.setPrice(price);
				Double discountPrice = generateDiscountPrice(productRequestModel.getQuantity(),
						product.getUnitPrice(), percentageBasedDiscount,product.getType());
				totalPercentageBasedDiscount +=discountPrice;
				productResponseModel.setDiscount(discountPrice);
				productResponseModel.setFinalPrice(price - discountPrice);
				productResponseModels.add(productResponseModel);
			}
			else
			{
			 throw new ProductException("Product with id: " + productRequestModel.getId() + " not found");
			}
		}
		Double totalAmountBasedDiscountAmount = 0.0;
		totalAmountBasedDiscountAmount = getAmountBasedDiscount(totalAmount, totalPercentageBasedDiscount,
				totalAmountBasedDiscountAmount);
		invoiceResponseModel.setTotalInvoiceAmount(totalAmount);
		invoiceResponseModel.setTotalPercentageBasedDiscountAmount(totalPercentageBasedDiscount);
		invoiceResponseModel.setTotalAmountBasedDiscountAmount(totalAmountBasedDiscountAmount);
		invoiceResponseModel.setFinalInvoiceAmount(totalAmount - totalPercentageBasedDiscount - totalAmountBasedDiscountAmount);
		invoiceResponseModel.setProductResponseList(productResponseModels);
		return invoiceResponseModel;
	}

	private Double getAmountBasedDiscount(Double totalAmount, Double totalPercentageBasedDiscount,
			Double totalAmountBasedDiscountAmount) {
		if((totalAmount - totalPercentageBasedDiscount)  >= 100)
		{
			Double amount = (totalAmount - totalPercentageBasedDiscount);
			double multipleOfHundred = (amount.intValue() / 100);
			totalAmountBasedDiscountAmount = multipleOfHundred * 5;
		}
		return totalAmountBasedDiscountAmount;
	}

	private double generateDiscountPrice(Long quantity, double unitPrice, Long percentageBasedDiscount, ProductType productType) {
		if (percentageBasedDiscount == 0l || productType.equals(ProductType.GROCERY)) {
			return 0;
		}
		return (quantity * unitPrice) * percentageBasedDiscount / 100;

	}

	private Long generatePercentageBasedDiscount(Long userId) {
		if (userId == null) {
			return 0l;
		}
		Optional<User> userOptional = userRepository.findById(userId);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getType().equals(UserType.STORE_EMPLOYEE)) {
				return 30l;
			} else if (user.getType().equals(UserType.STORE_AFFILIATE)) {
				return 10l;
			} else if (ChronoUnit.YEARS.between(user.getCreatedAt(), LocalDateTime.now()) > 2) {
				return 5l;
			}
		}

		return 0l;
	}

}
