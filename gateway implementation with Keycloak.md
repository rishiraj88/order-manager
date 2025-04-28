# Gateway Module
It is the gateway to Order Manager, a distributed app built with Spring Cloud Config and Spring Boot.

## Setup
- Create a new project with Spring Init. Make sure to add the two dependencies:
![Spring Init](./assets/gateway/01-spring-init.png)

- Then add the code (Java code has been added, feel free to switch to Kotlin if you prefer!).

### Manual Testing and Operation, along with Keycloak Configuration
- Now sit back tight and do the dirty engineering on service endpoints, integration and parameter matching.

- Run `docker-compose up -d` out of Product, Order and Inventory services.
- Start (Run.../Debug...) Product, Order, Inventory and Gateway services with IDE or CLI.
- Hit Product service over the port of Gateway. You succeed to access the service endpoint:
![via-gateway-products](./assets/gateway/02-via-gateway-products.png)

- Query the Inventory database with mysql login in Docker CLI to copy or remember one SKU code:
![03-inventory-state-in-db](./assets/gateway/03-inventory-state-in-db.png)

- Test the inventory service for availability of that SKU twice- for true and for false:
![04-via-gateway-inventory-false](./assets/gateway/04-via-gateway-inventory-false.png)
![05-via-gateway-inventory-true](./assets/gateway/05-via-gateway-inventory-true.png)

- Now write (just type in or copy-and-edit or get help from AI assistant) ´docker-compose-yml´ for Keycloak and its database. And, start the containers with `docker-compose up -d`:
![06-keycloak-docker-compose-run](./assets/gateway/06-keycloak-docker-compose-run.png)

- When the containers have started, visit `http://localhost:9090` in a web browser, login as admin (check creds in docker-compose or secrets file) and create a new realm for your app:
![07-create-realm-in-keycloak-admin](./assets/gateway/07-create-realm-in-keycloak-admin.png)

- The realm should get created quickly and please you with this:
![08-realm-creation-success-in-keycloak-admin](./assets/gateway/08-realm-creation-success-in-keycloak-admin.png)

- Create client with client creds ID and capability config:
![09-create-client-creds-id-in-keycloak-admin](./assets/gateway/09-create-client-creds-id-in-keycloak-admin.png)
![10-config-capabilities-to-service-accounts-roles](./assets/gateway/10-config-capabilities-to-service-accounts-roles.png)

- Client creation should finish smoothly as well:
![11-client-creation-success-in-keycloak-admin](./assets/gateway/11-client-creation-success-in-keycloak-admin.png)

- Under credentials of the new client, Client Secret is autopopulated. It may be used as such or may well be regenerated. Either choice is good at this moment:
![12-view-client-secret-under-credentials](./assets/gateway/12-view-client-secret-under-credentials.png)

- Click and open the link of OpenID Endpoint Config:
![13-view-OpenID-endpoint-config-url-under-realm-settings](./assets/gateway/13-view-OpenID-endpoint-config-url-under-realm-settings.png)
- You may see a page similar to the one shown below. Copy ´issuer´ value from the page:
![14-open-OpenID-endpoint-config-page](./assets/gateway/14-open-OpenID-endpoint-config-page.png)

- Use the issuer value to make the respective registry in `application.properties` of gateway service:
![15-issuer-value-to-app_props](./assets/gateway/15-issuer-value-to-app_props.png)

- Now try to access Product (or another) service via gateway port. 401 response code must be returned:
![16-http-401-unauthzd-to-products-in-HttpClient](./assets/gateway/16-http-401-unauthzd-to-products-in-HttpClient.png)

- Check with another HTTP request sender:
![17-http-401-unauthzd-to-products-in-Postman](./assets/gateway/17-http-401-unauthzd-to-products-in-Postman.png)

- Now, to add authorization, do the following exercise:
  - In Postman (or a similar GUI tool), under Request > Authorization, go to `Configure New Token > Configuration Options`. There select `Client Credentials` against `Grant Type`.
  - Then paste into `Access Token URL` the value (the URL) of "token endoint" header which is on OpenID Endpoint Config page:
![18-token-endpoint-value-from-openid-endpoint-config](./assets/gateway/18-token-endpoint-value-from-openid-endpoint-config.png)
  - Copy or remember the client creds ID from client settings in Keycload Admin and paste against `Client ID` in Postman dialog:
![19-client-creds-id-from-client-settings](./assets/gateway/19-client-creds-id-from-client-settings.png)

  - Copy client secret from client credentials (Keycloak Admin) against `Client Secret` in Postman dialog:
![20-client-secret-from-client-credentials](./assets/gateway/20-client-secret-from-client-credentials.png)

  - Make sure that `Grant Type` is `Client Crendentials`:
![21-grant-type-client-credentials-in-authz-in-postman](./assets/gateway/21-grant-type-client-credentials-in-authz-in-postman.png)

  - Click on the tempting `Get New Access Token` button... finally:
![22-get-new-access-token](./assets/gateway/22-get-new-access-token.png)

  - Now the Authz is complete for you. Hurray! Enjoy accessing your favorite services via gateway, now in a secure manner :) :
![23-authz-complete](./assets/gateway/23-authz-complete.png)

  - And yes, this is a no-explainer. It's obviously obvious to click on the `Use Token` button. Doing so gets you a credit of 300 seconds to get inside on the Mission IM-Possible:
![24-use-token](./assets/gateway/24-use-token.png)

  - Product service is now secured with gateway and Keycloak:
![25-via-gateway-products-authorized](./assets/gateway/25-via-gateway-products-authorized.png)

  - Order service is also secured with Keycloak, thanks to gateway for integrating them well:
![26-via-gateway-orders-authorized](./assets/gateway/26-via-gateway-orders-authorized.png)

## Contact Pointers
- **LinkedIn:** <https://www.linkedin.com/in/rishirajopenminds>
- **X:** <https://twitter.com/RishiRajDevOps>
- **Start Page:** <https://bio.link/rishiraj49de>
- **GitHub:** <https://github.com/rishiraj88>

## Credits and Gratitude
I thank all who have mentored, taught and guided me. Also, I appreciate who have supported my work with pair programming and more.
