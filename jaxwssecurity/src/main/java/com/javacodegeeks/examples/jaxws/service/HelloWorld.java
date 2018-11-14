package com.javacodegeeks.examples.jaxws.service;

import java.io.FileWriter;

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
        log("in sayHello: name=" + name);
        return "hello " + name;
    }

    private void log(String message) {
        try {
            FileWriter fileWriter = new FileWriter("/tmp/mytodo2.txt");
            fileWriter.write(message);
            fileWriter.close();
        } catch (Exception e) {
        }
    }
}
