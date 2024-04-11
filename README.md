# Order Manager (order-manager)
Order management APIs, built with Spring Boot

## Modules
- Product
- Order
- Inventory
- More may be added on need basis.

## Product Module
This module is for adding, listing, modifying and removing products and product details.

### API Base
The product API has its base at "/api/products".

### Database Details
MongoDB engine powers the product module. The data store is well expected to be enriched with newer fields very frequently, so NoSQL database technology is used for this.
The database is deployed in a Docker container with a persistent volume to hold business data.

### Project Setup with Dependencies
The module for Product has been set up with the following dependencies, specified in Spring Init:
- Lombok
- Spring Web
- Spring Data MongoDB
- Testcontainers
- Rest-Assured

![Spring Boot project dependencies](./assets/spring-init-for-product.png)

### Testing

### Manual Testing

![Product module: manual testing](./assets/manual-testing-of-product.png)

#### Automated Testing
A number of test cases are already provided in the **Tests** class. For POST request to add one new product, the following JSON-formatted data can be used to formulate request body:
<pre>{
"name":"mobile", 
"desc":"details", 
"pricePerItem":120.80
}</pre>

![Product module: automated testing](./assets/automated-testing-of-product.png)


## Order Module
This module is for creating, listing, modifying and removing orders and order details.

### API Base
The order API has its base at "/api/orders".

### Database Details
MySQL engine powers the order module. The data store is expected to be structured with infrequent field additions, modifications and removals. So, SQL database (RDBMS) technology is used for this.
The database is deployed in a Docker container with a persistent volume to hold business data.

### Project Setup with Dependencies
The module for Product has been set up with the following dependencies, specified in Spring Init:
- Lombok
- Spring Web
- Spring Data MySQL
- Testcontainers
- Rest-Assured

![Spring Boot project dependencies](./assets/spring-init-for-order.png)

### Testing

### Manual Testing
![Order module: manual testing](./assets/manual-testing-of-order.png)

#### Automated Testing
A number of test cases are already provided in the **Tests** class. For POST request to create one new order, the following JSON-formatted data can be used to formulate request body:
<pre>{
"orderNumber":"DE3343INT432342342345", 
"itemSkuCode":"DE342GES34233234", 
"pricePerItem":120.80,
"quantity":2
}</pre>

![Order module: automated testing](./assets/automated-testing-of-order.png)

## Inventroy Module
will follow...

## Contact Pointers
- **LinkedIn:** <https://www.linkedin.com/in/rishirajopenminds>
- **X:** <https://twitter.com/RishiRajDevOps>
- **Start Page:** <https://bio.link/rishiraj49de>
- **GitHub:** <https://github.com/rishiraj88>

## Credits and Gratitude
I thank all who have mentored, taught and guided me. Also, I appreciate who have supported my work with pair programming etc.
