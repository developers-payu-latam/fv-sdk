# Fraud Detection service SDK #

With this SDK you will be able to integrate with the fraud detection services in a quick and easy way. You only need to add the SDK as a dependency in your project.

##¿What data do you need for access our fraud detection services?

You will need some aunthentication credentials for access our services, to obtain them contact us. You will need:  
	- Your unique client identifier  
	- Your user login and password to authentication  

##¿How you can get an instance of the fraud detection service client?

Getting an instance of our service client is easy, only do this:

With your aunthentication credentials, create the Credentials object:
```java
	Credentials credentials = new Credentials(yourClientId, yourLogin, yourPassword);
```

With your Credentials and the base URL of our service, create the configuration of the client:
```java
	FDClientConfiguration clientConfiguration = FDClientConfiguration.builder()
					.credentials(credentials)
					.fdServiceBaseUrl(webServiceBaseUrl)
					.build();
```
The base URL of our services, depends on the environment you're connecting to. For our sandbox environment, use: https://pruebas.maf.pagosonline.net/ws

So, for getting the client just do:
```java
	FDClient fraudDetectionClient = FDClientFactory.createDefaultClient(clientConfiguration);
```
##¿How do you invoke the fraud detection service?

First, create the object with the data of the transaction(the order) that you want to sent to the fraud detection service, a simple example could be:
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
You can build in a similar way the required objects, for example, the data of the buyer user:
```java
Buyer.builder()
		.id("the buyer Id")
		.name("the buyer names")
		.surname("the buyer surnames")
		.email("the buyer email")
		.build();
```		
With the transaction and the instance of the client you will be able to use the different services. For example to call the prevalidation service: 
```java
	FDPrevalidationResponse prevalidationResponse = fraudDetectionClient.prevalidate(transaction);
```
And you will be able to get the information of your interest from the service response. For example, to get the decision made by the fraud detection service:
```java
	prevalidationResponse.getDecision();
```
To know if there was an error when evaluating the transaction:
```java
	prevalidationResponse.hasError();
```
