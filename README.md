### Postman collection
You can find postman collection at _https://github.com/sandeep-div/retailstore/RetailStore.postman_collection_

### UML Diagram
You can find postman collection at _https://github.com/sandeep-div/retailstore/UML_DIAGRAM.png_

### Installation

_How to run_
1. Clone the repo :
   ```sh
   git clone https://github.com/sandeep-div/retailstore.git
   ```
2. Create schema :
   ```sh
   Create a schema in mysql server named "retailstore"
   ```
3. Run command to run project :
   ```sh
   mvn spring-boot:run
   ```
   
### Sample request and response

_Request_
   ```sh
   {
    "productRequestList":
    [ 
        {"id":1,"quantity":1},
        {"id":2,"quantity":2},
        {"id":3,"quantity":3},
        {"id":4,"quantity":4}
    ],
    "userId":"9584665552"
}
   ```
_Response_
   ```sh
   {
    "body": {
        "productResponseList": [
            {
                "productId": 1,
                "productName": "Cricket bat",
                "quantity": 1,
                "unitPrice": 1200.0,
                "price": 1200.0,
                "discount": 120.0,
                "finalPrice": 1080.0
            },
            {
                "productId": 2,
                "productName": "Cricket ball",
                "quantity": 2,
                "unitPrice": 100.0,
                "price": 200.0,
                "discount": 20.0,
                "finalPrice": 180.0
            },
            {
                "productId": 3,
                "productName": "Salt",
                "quantity": 3,
                "unitPrice": 50.0,
                "price": 150.0,
                "discount": 0.0,
                "finalPrice": 150.0
            },
            {
                "productId": 4,
                "productName": "Rice",
                "quantity": 4,
                "unitPrice": 250.0,
                "price": 1000.0,
                "discount": 0.0,
                "finalPrice": 1000.0
            }
        ],
        "totalPercentageBasedDiscountAmount": 140.0,
        "totalAmountBasedDiscountAmount": 120.0,
        "totalInvoiceAmount": 2550.0,
        "finalInvoiceAmount": 2290.0
    },
    "status": true,
    "message": "Invoice generated successfully"
}
   ```
