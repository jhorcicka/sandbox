package com.javacodegeeks.examples.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * https://examples.javacodegeeks.com/enterprise-java/jws/jax-ws-client-basic-authentication-example/#1.4_subsec
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
