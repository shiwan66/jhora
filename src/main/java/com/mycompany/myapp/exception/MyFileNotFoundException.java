package com.mycompany.myapp.exception;

/**
 * Created by qk on 18/8/22.
 */
public class MyFileNotFoundException extends RuntimeException{
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
