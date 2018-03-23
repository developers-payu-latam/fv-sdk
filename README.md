# Fraudvault SDK #

With this SDK you will be able to integrate the Fraudvault services in a quick and easy way. To add the SDK dependency:

**Maven:**

````
	<dependency>
  		<groupId>com.payulatam.fraudvault</groupId>
  		<artifactId>fv-client-impl</artifactId>
  		<version>1.0.0</version>
  	</dependency>
````

**Gradle:**

````
compile group: 'com.payulatam.fraudvault', name: 'fv-client-impl', version: '1.0.0'
````

##¿What data do you need for access our Fraudvault services?

You will need some aunthentication credentials for access our services, to obtain them contact us. You will need:
+ Your unique client identifier
+ Your user login and password to authentication

##¿How you can get an instance of the Fraudvault client?

Getting an instance of our service client is easy, only do this:

With your aunthentication credentials, create the Credentials object:
```java
Credentials credentials = new Credentials(yourClientId, yourLogin, yourPassword);
```

With your Credentials and the base URL of our Fraudvault service, create the configuration of the FraudvaultClient:
```java
FraudvaultClientConfiguration fraudvaultClientConfiguration = FraudvaultClientConfiguration.builder()
				.credentials(credentials)
				.fraudvaultServiceBaseUrl(webServiceBaseUrl)
				.build();
```
The base URL of our Fraudvault services, depends on the environment you're connecting to. For our sandbox environment, use: https://pruebas.maf.pagosonline.net/ws

So, for getting the Fraudvault client just do:
```java
FraudvaultClient fraudvaultClient = FraudvaultClientFactory.createDefaultFraudvaultClient(fraudvaultClientConfiguration);
```
##¿How do you invoke the Fraudvault services?
With the instance of the client you will be able to use the different Fraudvault services. For example to call the prevalidation service:

First, create the object with the data of the transaction(the order) that you want to prevalidate, a simple example could be:
```java
Transaction transaction = Transaction.builder()
										.transactionId(theTransactionId)
										.referenceCode("theReferenceCode")
										.sessionId("theSessionId")
										.creationDate(theTransactionCreationDate)
										.countryIso("theTransactionCountryIsoCode")
										.description("theTransactionDescription")
										.value(theTransactionValue)
										.sellerUser(theSellerUser)
										.buyerUser(theBuyerUser)
										.paymentInformation(thePaymentData)
										.deliveryAddress(theDeliveryAddress)
										.build();
```
You can build in a similar way the required object, for example, the data of the buyer user:
```java
Buyer.builder()
		.id("the buyer Id")
		.name("the buyer names")
		.surname("the buyer surnames")
		.email("the buyer email")
		.build();
```		
With the transaction, now you can call the Fraudvault service:
```java
FraudvaultPrevalidationResponse prevalidationResponse = fraudvaultClient.prevalidate(transaction);
```
And you will be able to get the information of your interest from the service reponse. For example, to get the decision made by Fraudvault:
```java
	prevalidationResponse.getDecision();
```
To know if there was an error when evaluating the transaction:
```java
	prevalidationResponse.hasError();
```
