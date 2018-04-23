# Fraudvault SDK #

With this SDK you will be able to integrate the Fraudvault services in a quick and easy way. To add the SDK dependency in your project:

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

##Prerequisites

You can use this SDK if you are using Java version 7+  

The Fraudvault service is running on the HTTPS protocol allowing a secure transmission of data, so in order to connecting, you need to add our SSL certificate to the Java CA certificate(cacerts) store.  
- First, get the certificate file: contact us or get it through a request to our server in your browser. Save the file to the **jdk\jre\lib\security** folder or any other folder if you prefer.  
 
- Second, add the certificate to the JVM used by your application: At an administrator command prompt that is set to your JDK's **jdk\jre\lib\security** folder, run the following command to import the certificate:
	
    ````
	    keytool -keystore cacerts -importcert -alias **<the_alias_you_desire_to_use>** -file **<the_path_for_the_certificate_file>**
    ````
Replace **<the_alias_you_desire_to_use>** with the appropriate value, only if you want to set an alias for the certificate, otherwise you don't need to specify the **-alias** parameter.  
Replace **<the_path_for_the_certificate_file>** with the path of the certificate; if you save the file to the **jdk\jre\lib\security** folder, you don't need to set the full path, only the name with the extension of the file. If you save the file in other location you have to set the full path of the certificate file.  

- Run the following command to ensure the certificate has been successfully imported:
	
	````
		keytool -list -keystore cacerts
	````

##¿What data do you need for access our Fraudvault services?

You will need some aunthentication credentials for access our services, to obtain them contact us. You will need:  
	- Your unique client identifier  
	- Your user login and password to authentication  

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
With the transaction and the instance of the client you will be able to use the different Fraudvault services. For example to call the prevalidation service: 
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
