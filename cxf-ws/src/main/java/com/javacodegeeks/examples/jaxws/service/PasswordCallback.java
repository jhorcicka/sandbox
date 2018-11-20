package com.javacodegeeks.examples.jaxws.service;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class PasswordCallback implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            final WSPasswordCallback callback = (WSPasswordCallback) callbacks[i];

            if (callback.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                //You must set a password for the user, WSS4J would compare
                //the password with the password sent by client, if they match
                //message will be processed. Any mismatch in password will result in a SOAP Fault.

                if (callback.getIdentifier().equalsIgnoreCase("my-username")) {
                    callback.setPassword("my-password");
                }
            }
        }
    }
}
