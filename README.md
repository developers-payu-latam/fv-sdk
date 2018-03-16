# Fraudvault SDK #

With this sdk you will be able to integrate with the Fraudvault service in a quick and easy way.
 
##¿What data do you need for access our Fraudvault services?

You will need some aunthentication credentials for access our services, You have to contact us for get: Your identifier of client into our system, your user login and password to authentication.

##¿How you can get an instance of the Fraudvault client?

Getting an instance of our service client is easy, only do this:

With your aunthentication credentials, create the Credentials object:
```java
Credentials credentials = Credentials.builder(yourClientId, yourLogin, yourPassword).build();
```

With your Credentials and the base URL of our Fraudvault service, create the configuration of the FraudvaultClient:
```java
FraudvaultClientConfiguration fraudvaultClientConfiguration = FraudvaultClientConfiguration.builder(credentials, webServicebaseUrl).build();
```
The base URL of our Fraudvault services, depends on the environment you're connecting to. For our sandbox environment, use: https://pruebas.maf.pagosonline.net/ws

So, for getting the Fraudvault client just do:
```java
FraudvaultClient fraudvaultClient = FraudvaultClientFactory.createDefaultFraudvaultClient(fraudvaultClientConfiguration);
```
##¿How you I invoke the Fraudvault services?
With the instance of the client you will be able to use the different Fraudvault services. For example to call the prevalidation service:

1 Create the object with the data of the transaction(the order) that you want to prevalidate, a simple example could be:
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
										.paymentInformation(thePaymentMethod)
										.deliveryAddress(theDeliveryAddress)
										.build();
```
You can build in a similar way the required object, for example, the data of the buyer user:
```java
Buyer.builder()
		.id("the buyer Id")
		.names("the buyer names")
		.surnames("the buyer surnames")
		.email("the buyer email")
		.build();
```		
2 With the transaction, now you can call the Fraudvault service:
```java
FraudvaultPrevalidation prevalidationResponse = fraudvaultClient.prevalidate(transaction);
```
And you will be able to get the information of your interest from the service reponse. 
For example:
For get the decision made by Fraudvault:
```java
	prevalidationResponse.getDecision();
```
For know if there was an error when evaluating the transaction:
```java
	prevalidationResponse.getErrorCode();
```
