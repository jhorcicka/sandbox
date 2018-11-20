package com.javacodegeeks.examples.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * https://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-client-basic-authentication-example/#1.4_subsec
 * 
 * SOAP REQUEST: 
 <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.jaxws.examples.javacodegeeks.com/">
     <soapenv:Header> 
         <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" soapenv:mustUnderstand="1"> 
             <wsse:UsernameToken xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" wsu:Id="UsernameToken-1"> 
                 <wsse:Username>my-username</wsse:Username> 
                 <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">my-password</wsse:Password> 
             </wsse:UsernameToken> 
         </wsse:Security> 
     </soapenv:Header>
     <soapenv:Body>
         <ser:sayHello>
             <arg0>john</arg0>
         </ser:sayHello>
     </soapenv:Body>
 </soapenv:Envelope>
 */
@WebService
public class HelloWorld {
    public HelloWorld() {
    }

    @WebMethod
    public String sayHello(String name) {
        return "hello " + name;
    }
}
