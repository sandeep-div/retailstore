package com.sandeep.retailstore.serviceimpl;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sandeep.retailstore.entity.Product;
import com.sandeep.retailstore.entity.User;
import com.sandeep.retailstore.model.InvoiceRequestModel;
import com.sandeep.retailstore.model.InvoiceResponseModel;
import com.sandeep.retailstore.model.ProductRequestModel;
import com.sandeep.retailstore.model.ProductResponseModel;
import com.sandeep.retailstore.model.ProductType;
import com.sandeep.retailstore.model.UserType;
import com.sandeep.retailstore.repository.ProductRepository;
import com.sandeep.retailstore.repository.UserRepository;
import com.sandeep.retailstore.service.impl.InvoiceServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Product product;
    private User user;

    @Before
    public void setup() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setUnitPrice(10.0);
        product.setType(ProductType.GROCERY);

        user = new User();
        user.setId(1L);
        user.setType(UserType.STORE_EMPLOYEE);
        user.setCreatedAt(LocalDateTime.now().minusYears(1));

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    public void testGenerateInvoice() {
        // Prepare test data
        ProductRequestModel productRequestModel = new ProductRequestModel();
        productRequestModel.setId(1L);
        productRequestModel.setQuantity(2L);

        InvoiceRequestModel requestModel = new InvoiceRequestModel();
        requestModel.setUserId(1L);
        requestModel.setProductRequestList(Arrays.asList(productRequestModel));

        // Invoke the method
        InvoiceResponseModel responseModel = invoiceService.generateInvoice(requestModel);

        // Perform assertions
        assertNotNull(responseModel);
        assertEquals(20.0, responseModel.getTotalInvoiceAmount(), 0.01);
        assertEquals(4.0, responseModel.getTotalPercentageBasedDiscountAmount(), 0.01);
        assertEquals(5.0, responseModel.getTotalAmountBasedDiscountAmount(), 0.01);
        assertEquals(11.0, responseModel.getFinalInvoiceAmount(), 0.01);

        List<ProductResponseModel> productResponseList = responseModel.getProductResponseList();
        assertNotNull(productResponseList);
        assertEquals(1, productResponseList.size());

        ProductResponseModel productResponseModel = productResponseList.get(0);
        assertEquals(1L, productResponseModel.getProductId().longValue());
        assertEquals("Test Product", productResponseModel.getProductName());
        assertEquals(2, productResponseModel.getQuantity().intValue());
        assertEquals(10.0, productResponseModel.getUnitPrice(), 0.01);
        assertEquals(20.0, productResponseModel.getPrice(), 0.01);
        assertEquals(4.0, productResponseModel.getDiscount(), 0.01);
        assertEquals(16.0, productResponseModel.getFinalPrice(), 0.01);
    }
}
