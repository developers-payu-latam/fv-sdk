# Fraudvault SDK #

With this sdk you will be able to integrate with the Fraudvault service in a quick and easy way.
 
##¿What data do you need for access our Fraudvault services?

You will need some aunthentication credentials for access our services, You have to contact us for get:
- Your identifier of client into our system.
- Your user login to authentication.
- Your user password to authentication.

##¿How you can get an instance of the Fraudvault client?

Getting an instance of our service client is easy, only do this:

With your aunthentication credentials, create the Credentials object:
```java
Credentials credentials = Credentials.builder(yourClientId, yourLogin, yourPassword).build();
```
