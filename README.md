# Order Manager (a POC on Modern "Development Best Practices")
- This project does not have a fool-proof implementation of order module of any modern e-commerce platform, rather it showcases how the problems faced by most of the businesses today can be simply managed with a "distributed app" approach. Such a system is implemented using modern web technologies, knitted with asynchronous and synchronous communications and further empowered with Observability. Thanks for Prometheus-Grafana stack, which consists of loggging tools (Loki), Distributed Tracing tools (micrometer) and Dashboarding powerhouse (Grafana).


- Some of the technologies and developer tools used in this project are:
- Spring Boot 3.4, Java 21+ (Java 24)
- Keycloak (OAuth2, OpenID Connect) for User Account management, Authorization and Authentication [*Reference Doc*](https://github.com/rishiraj88/order-manager/blob/main/gateway%20implementation%20with%20Keycloak.md)
- Prometheus-Grafana stack for Observability (Prometheus, Loki, micrometer and Grafana  Dashboard)
- Modern strategy for Spring Security implementation (as in Spring Boot 3, Spring 6)
- Use of WebClient and RestClient (Spring Boot 3) and not of FeignClient (Spring Boot 2.7 style)
- Apache Kafka for for message queues (MQ), which provide for asynchronous communication (as in Notification) across web services
- Docker-Compose for containerization of apps
- Testcontainers for automated API Testing and wiremock for Contract Testing, along with Rest-Assured
- Container orchestration, clustering and auto-scaling with Deployment YAML for Kubernetes.
- Port exposure with Service kind of descriptors (Kubernetes)

## Highlights of technological tools
- Prometheus-Grafana stack for Observability (Prometheus, Grafa-Loki, micrometer)
- Multi-stage Dockerfile for smaller container sizes and enhanced security of business apps
- Declarations of YAML for Kubernetes-based cluster deployment
- Gateway implementation as a microservice to secure and connect the backend APIs to external clients

## Development Style for the Big Distributed Platform:
The distributed platform, a technological giant, is being developed and enhanced continuously in two main streams:
- `archiV1java`: using Java/Jakarta with Spring Boot 3 as main development toolset. It gets the first chance and higher priority to try more and newer technological frameworks, libraries and performance enhancers out. Think of Fedora and Rocky Linux here.
- `snapshotV2kotlin`: using Kotlin with Spring Boot 3 as main development stack. It inherits the tried-and-tested tooling from *archiV1java* stream.

## This application has three main business components, implemented as Microservices:
As three RESTful APIs, these business components interact with one another:
- Product
- Order
- Inventory

## More components, services, config and connectors are there as well- 
- to integrate the three main actors (our "heroes"), and
- Gateway and User Authentication with OpenID Connect (Keykloak: <./gateway/README.md>)
- API Documentation with OpenAPI Specification (OAS): Swagger
- Message queues in order to facilitate smooth communication among the services.


And the result is: unmatched and flawless user experience. :) 

## A few exciting screenshots (updated in May 2025)

![to list products via API access (using Postman)](./assets/observability/list-products-in-Postman-20250505.png)

![Welcome landing view in Prometheus-Grafana portal (local setup)](./assets/observability/grafana-welcome-page-20250505.png)

![viewing distributed traces with Tempo](./assets/observability/traces-in-tempo-20250505.png)

![applying filters for log monitoring with Prometheus-Grafana](./assets/observability/filters-in-prometheus-20250505.png)

![to add new product record via API access](./assets/observability/add-new-product-in-Postman-20250505.png)

![viewing the logs of Product application in real time with Grafana stack](./assets/observability/product-app-logs-in-prometheus-20250505.png)

![Grafana Dashboard graphs 1st capture](./assets/observability/Prometheus-Grafana-dashboard-1-20250505.png)

![Grafana Dashboard graphs 2nd capture](./assets/observability/Prometheus-Grafana-dashboard-2-20250505.png)


## To view and test-run the web app

### When without Deployment
- to list pods and services
<pre>$ kubectl get pods,svc</pre>

- to get the URL of Order app.
<pre>$ minikube service om-order-svc --url</pre>


### When with Deployment
- to list deployments and services
<pre>$ kubectl get deployments,svc</pre>

- to get the URL of Order app.
<pre>$ minikube service om-order-svc --url</pre>

### Similar steps for Product app with Deployment
- to list deployments and services
<pre>$ kubectl get deployments,svc</pre>

- to get the URL of Product app.
<pre>$ minikube service om-product-svc --url</pre>

## Scale the services
<pre>kubectl scale deployment om-order-depl --replicas=2</pre>
<pre>kubectl scale deployment om-product-depl --replicas=2</pre>


![realm-creation-success-in-keycloak-admin](./assets/gateway/08-realm-creation-success-in-keycloak-admin.png)


Documentation is being updated for the year 2025. However, the screenshots of the year 2024 are quite convincing and "in context".

## Tech Tools Used for Development
- Spring Boot, with Spring Web
- Spring Cloud Config (for service integration)
- Spring Security (for Authz-n-Authn)
- Keycloak (for user account management)
- MongoDB (NoSQL Database)
- Spring Data MongoDB (for Mongo Repository)
- MySQL (Relational Database System)
- Spring Data MySQL (for JPA Repository)
- Flyway (for Database Migration)
- Docker-Compose (for containerization)
- Local container repo registry (registry image from Docker Hub)
- Swagger (for OpenAPI-based API documentation)
- Testcontainers (for Test Automation with stubs)
- Rest-Assured (for Testing and Validation)
- Lombok (for boilerplate code injection)
- Spring RestClient (Spring Boot 3.2 offering. It replaces the older option Spring OpenFeign (for inter-service communication))
- Resilient4j v2 for Fault Tolerance

## General Note on Development Style
I enjoy the following approach for inception, enhancement and fortification of projects:
1. Start in simple, lazy and less structured approach.
2. Modularise, distribute and containerise various components, guided by relative prioritization.
3. Look out for fan-out scenarios and go for asynchronous messaging across services accordingly.
4. Alongside work, in gradual steps, towards making the deliverables (distributables) compact in file size and deploy them in replicas (clusters).


Suggestions and feedback comments from readers are always welcomed with love. Some screenshots from one session on cluster management and orchestration are here:
- ![01-kubectl-pods-created](./assets/upload20250215/01-kubectl-pods-created.png)
- ![02-docker-gui-pods](./assets/upload20250215/02-docker-gui-pods.png)
- ![03-replicas-in-replicaset](./assets/upload20250215/03-replicas-in-replicaset.png)
- ![04-docker-gui-scaled-replicaset](./assets/upload20250215/04-docker-gui-scaled-replicaset.png)
- ![05-kubectl-scaling-replicaset](./assets/upload20250215/05-kubectl-scaling-replicaset.png)
- ![06-kubectl-deployment-created](./assets/upload20250215/06-kubectl-deployment-created.png)
- ![07-kubectl-service-created](./assets/upload20250215/07-kubectl-service-created.png)
- ![08-kubectl-other-deployment-created](./assets/upload20250215/08-kubectl-other-deployment-created.png)
- ![09-kubectl-experimentation](./assets/upload20250215/09-kubectl-experimentation.png)
- ![10-docker-gui-after-cleanup](./assets/upload20250215/10-docker-gui-after-cleanup.png)


## Modules
- Product
- Order
- Inventory
- Gateway (with redirection, and OAuth2 with Keycloak and Docker-Compose)
- More may be added on need basis.

## Product Module
This module is for adding, listing, modifying and removing products and product details.

### API Base
The product API has its base at "/api/v1/products".

### Database Details
MongoDB engine powers the product module. The data store is well expected to be enriched with newer fields very frequently, so NoSQL database technology is used for this.
The database is deployed in a Docker container with a persistent volume to hold business data.

### Project Setup with Dependencies
The module for Product (Kotlin variant) has been set up with the following dependencies, specified in Spring Init:
- Spring Web
- Spring Data MongoDB
- Testcontainers
- Rest-Assured
- Swagger (for OpenAPI-based API documentation)

![Spring Boot project dependencies](./assets/omkt/product/spring-init-for-Product.png)

### Testing

### Manual Testing

#### Testing Screenshots [20250903]
![Product module: adding a product](./assets/testingOn20250903/screenshot.1756887155.png)
![Product module: adding another product](./assets/testingOn20250903/screenshot.1756887177.png)
![Product module: listing products](./assets/testingOn20250903/screenshot.1756891751.png)
![Product module: creating an order](./assets/testingOn20250903/screenshot.1756894133.png)
![Product module: creating another order](./assets/testingOn20250903/screenshot.1756894156.png)
![Product module: querying inventory status for item](./assets/testingOn20250903/screenshot.1756896799.png)
![Product module: viewing application logs for inventory](./assets/testingOn20250903/screenshot.1756896913.png)



#### Product
![Product module: adding first product for this day](./assets/omkt/product/add-product1-20250429.png)
![Product module: adding second product for this day](./assets/omkt/product/add-product2-20250429.png)
![Product module: listing the available products](./assets/omkt/product/get-products-20250429.png)

![Product module: manual testing](./assets/manual-testing-of-product.png)
![Product module: manual testing](./assets/omkt/product/addOneProduct-manual-testing.png)
![Product module: manual testing](./assets/omkt/product/getAllProducts-manual-testing.png)

#### Order


![Order module: getting no orders available](./assets/omkt/order/no-orders-found20250429.png)

![Order module: creating a new order](./assets/omkt/order/new-order-created20250429.png)

![Order module: getting orders listed](./assets/omkt/order/orders-found20250429.png)



![Order module: manual testing](./assets/omkt/order/getAllOrders-manual-testing.png)

![Order module: manual testing](./assets/omkt/order/createOneNewOrder-manual-testing.png)


#### Inventory
![Inventory module: manual testing](./assets/omkt/inventory/checkStock-true.png)

![Inventory module: manual testing](./assets/omkt/inventory/checkStock-false.png)


#### Automated Testing
A number of test cases are already provided in the **Tests** class. For POST request to add one new product, the following JSON-formatted data can be used to formulate request body:
<pre>{
"name":"mobile", 
"desc":"details", 
"pricePerItem":120.80
}</pre>

#### Product testing in Apr-2025
![Product module: automated testing](./assets/omkt/product/addProduct-automated-testing.png)


#### Order testing in Apr-2025
![Order module: automated testing](./assets/omkt/order/createOrder-automated-testing.png)

#### Testing Done in Apr-2024
![Product module: automated testing](./assets/automated-testing-of-product_20240411.png)

#### Testing Done in May-2024
![Product module: automated testing](./assets/automated-testing-of-product_20240502.png)


## Order Module
This module is for creating, listing, modifying and removing orders and order details.

### API Base
The order API has its base at "/api/v1/orders".

### Database Details
MySQL engine powers the order module. The data store is expected to be structured with infrequent field additions, modifications and removals. So, SQL database (RDBMS) technology is used for this.
The database is deployed in a Docker container with a persistent volume to hold business data.

### Steps to set local image repository registry up

- Create the registry for local image repositories:
docker run -d -p 5000:5000 --restart=always --name registry registry:3.0.0

Build app images with customer labels:
docker build . -t localhost:5000/om-order

Push the images to the local repository:
docker push localhost:5000/om-order



#### Spring Boot project dependencies (Intial Set)
![Spring Boot project dependencies](./assets/spring-init-for-order.png)

#### Project dependency added for Wiremock
![Project dependency added for Wiremock](./assets/WireMock-dep.png)

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

#### Testing Done on 11-Apr-2024
![Order module: automated testing](./assets/automated-testing-of-order_20240411.png)

#### Testing Done on 02-May-2024
![Order module: automated testing](./assets/automated-testing-of-order_20240502.png)

#### Testing Done on 06-May-2024 with WireMock

- Before Stubbing: Order Request and Positive Scenario Response
![Order module: automated API testing with stubbing](./assets/automated-testing-of-order_20240506-01Positive-OrderReq-with-Inventory-connected.png)

- Before Stubbing: Order Request and Negative Scenario Response
![Order module: automated API testing with stubbing](./assets/automated-testing-of-order_20240506-02Negative-OrderReq-with-Inventory-connected.png)

- With Stubbing of Inventory API: Response
![Order module: automated API testing with stubbing](./assets/automated-testing-of-order_20240506-03Positive-OrderReq-with-Inventory-mocked.png)

## Inventroy Module
This module is for checking whether there is enough quantity available in inventory stock in order to place a new item order.

### API Base
The inventory API has its base at "/api/inventory".

### Database Details
MySQL engine powers the inventory module. The data store is expected to be structured with infrequent field additions, modifications and removals. So, SQL database (RDBMS) technology is used for this.
The database is deployed in a Docker container with a persistent volume to hold business data.

### Project Setup with Dependencies
The module for Product has been set up with the following dependencies, specified in Spring Init (<https://start.spring.io/>):
- Lombok
- Spring Web
- Spring Data MySQL
- Flyway
- Testcontainers
- Rest-Assured
- RestClient (Spring Boot 3)
- Swagger (for OpenAPI-based API documentation)

![Spring Boot project dependencies](./assets/spring-init-for-inventory.png)

### Testing

#### Manual Testing

For GET request to check the in-stock availability of an item, the following URL pattern can be prepared and hit:
<pre>http://localhost:8082/api/inventory?skuCode=DE342GES34233125&quantityForQuery=200</pre>

#### Positive Test Scenario
![Inventory module: manual testing](./assets/manual-testing-of-inventory-01.png)

#### Negative Test Scenario 01
![Inventory module: manual testing](./assets/manual-testing-of-inventory-02.png)

#### Negative Test Scenario 02
![Inventory module: manual testing](./assets/manual-testing-of-inventory-03.png)

#### Automated Testing

#### Testing Done on 11-Apr-2024
![Inventory module: automated testing](./assets/automated-testing-of-inventory_20240411.png)

## Gateway Module
This module is for accepting the requests for external clients in first place and then redirecting them to appropriate microservices, such as:
- _to Product_ to view product list and to add new product to data store;
- _to Order_ to place an order for an item. It internally checks at Inventory service whether the requested quantity for the SKU is available in store (warehouse/shop);
- _one "backend access"_ for development purpose.  

### API Port
The gateway API is served at port `9000` and comes into access when the following URL patterns are hit:
- POST and GET at http://localhost:9000/api/products
- POST at http://localhost:9000/api/orders
- GET at http://localhost:9000/api/inventory?skuCode=DE342GES34233123&quantityForQuery=5

<pre>Examplary values are shown the the inventory URL above.</pre>

### Database Details
MySQL engine powers the user-authorization-and-authentication database. The data store is not accessed by the gateway code directly nor from the wild outside without any authorization check. The open source tool Keycloak is in place to manage user Authz-n-Authn data and very low variation in the data dictionary is expected. So, SQL database (RDBMS) technology is used for this.
Keykloak tool and the database are both deployed in Docker containers with a persistent volumes.

### Project Setup with Dependencies
The module for Product has been set up with the following dependencies, specified in Spring Init (<https://start.spring.io/>):
- Gateway
- OAuth2 Resource Server

![Spring Boot project dependencies](./assets/gateway/01-spring-init.png)

### Keycloak Config and Auth Testing
Configuration and manual testing across service endpoint integration are detailed out in the separate Markdown file: - **gateway implementation with Keycloak.md**

### Gists & Developer Notes
- to view tables in mysql database:
<pre>mysql -uusernameyourwish -ppasswordyourwish
show databases;
use leasedb;
show tables;
</pre>
- locally hosted Docker registry has been set up as:
</pre>docker run -d -p 5000:5000 --restart always --name registry registry:2
docker tag imageName localhost:5000/imageName
$ docker push localhost:5000/imageName</pre>


## Contact Pointers
- **LinkedIn:** <https://www.linkedin.com/in/rishirajopenminds>
- **GitHub:** <https://github.com/rishiraj88>
- **X:** <https://twitter.com/RishiRajDevOps>
- **Start Page:** <https://bio.link/rishiraj49de>

## Credits and Gratitude
I thank all who have mentored, taught and guided me. Also, I appreciate who have supported my work with pair programming and more.
