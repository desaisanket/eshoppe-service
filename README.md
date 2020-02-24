## E- Shoppe!
As part of our exciting team,  we are tasked to kick-start our team to create a basic shop service where customers can purchase products.

#### The Product Owner gave the team the following brief:
* We want our customers to be able to spend their “Active Days” points to buy some products from our E-Shoppe store.
#### The Business Analyst adds the following information:
* A Customer in the context of our store is really a unique ID and a name.
* A Product consists of a name, a code and a points cost.

## eshoppe-service
eshoppe-service is a simple api which provides endpoints to view all Products, list all Customers, purchase producttts with active-day-points

### Tech Stack
* Language - Java 8
* Framework - Springboot
* Database - H2
* DB version control - Liquibase
* Logging - Logback
* Testing - JUnit
* Documentation - Swagger
* Build Tool - Maven

### Running the service
1. Checkout Project from git
2. Open project in IntelliJ / Eclipse and perform *mvn clean install*
3. Run project as Springboot Application
4. Open swagger-ui to try it out: http://localhost:8080/swagger-ui.html#/shop-controller
   * /customers - lists all the customers and their active day points balance
   * /products  - lists all products and their points cost
   * /products/purchase - accepts Customer ID and Products Codes as input to purchase products for Customer

#### Sample Request Json (/products/purchase)
{
  "customerId": 1,
  "productCodes": [
    "sgbat", "sgball"
  ]
}


