package com.mickleentityltdnigeria.mickle_pay.exceptions;

public class NetworkException extends Exception{

    public NetworkException() {

        super("No available network connectivity.");
    }

    public NetworkException(String message) {

        super(message);
    }


}
