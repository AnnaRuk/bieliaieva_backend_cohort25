## task 1
actions with Products
### Description
Implement endpoints for working with the Products list

Add Product
Update Product
Get Product by ID
Get All Products
delete Product
Do available Product //DRAFT,AVAILABLE, TEMPORARILY_UNAVAILABLE,OUTDATED
When creating a Product, it must have a DRAFT status (draft)

When a Product is available, it will have a AVAILABLE status

### Examples of requests and responses
  1. Add Products
  - requests:
POST /api/products
Request body:
````json
{
"productName": "new product", 
"categoryID": 2,
"expirationDate": "02.02.2024",
"description": "Description of the new product",
"price": 100.0
}
````
- responses:
Status 201 CREATED,product has been added successfuly
Status 400 Bad Request, Validation error
Status 409 CONFLICT, There is already a product with this productName
Response body:
````json
{
"id":1,
"productName": "new product",
"categoryID": 2,
"expirationDate": "02.02.2024",
"description": "Description of the new product",
"price": 100.0
"state": "DRAFT"
}
````
```` table:
create table products (
id int8 generated by default as identity,
categoryid int4 not null,
description varchar(5000) not null,
expiration_date date,
price float8 not null,
product_name varchar(50) not null,
state varchar(255),
primary key (id)
)
````





[//]: # (///TODO)
2. Update Product

- requests:
  POST /api/products/1
  Request body:
````json
{

}
````
- response:
  Status 201 CREATED
  Response body:
````json
{

}
````
3. Get Product by ID
- requests:
  POST /api/products/1
  Request body:

- response:
  Status 200 OK, Request processed successfully
- Status 404, NOT_FOUND,  Product not found
  Response body:
````json
{
  "id":1,
  "productName": "new product",
  "categoryID": 2,
  "expirationDate": "02.02.2024",
  "description": "Description of the new product",
  "price": 100.0
  "state": "DRAFT"
}
````
4. Get All Products
- requests:
  POST /api/products
- response:
  Status 200 OK, Request processed successfully
- Status 404, NOT_FOUND,  Products not found
  Response body:
````
{
 [ 
   {
     "id":1,
     "productName": "new product",
     "categoryID": 2,
     "expirationDate": "02.02.2024",
     "description": "Description of the new product",
     "price": 100.0
     "state": "DRAFT"
   },
   {
     "id":2,
     "productName": "new product",
     "categoryID": 2,
     "expirationDate": "02.02.2024",
     "description": "Description of the new product",
     "price": 100.0
     "state": "DRAFT"
   },
   ...
   {
     "id":3,
     "productName": "new product",
     "categoryID": 2,
     "expirationDate": "02.02.2024",
     "description": "Description of the new product",
     "price": 100.0
     "state": "DRAFT"
   }
   
   
 ]
}
````

5. delete Product

- requests:
  POST /api/products
  Request body:
````json
{

}
````
- response:
  Status 201 CREATED
  Response body:
````json
{

}
````

6. Publish Product

- requests:
  POST /api/products
  Request body:
````json
{

}
````
- response:
  Status 201 CREATED
  Response body:
````json
{
}
````