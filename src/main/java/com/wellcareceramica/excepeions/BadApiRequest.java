package com.wellcareceramica.excepeions;

public class BadApiRequest extends RuntimeException{

    public BadApiRequest(String message){
        super(message);
    }

    public BadApiRequest(){
        super("Bad Request !!");
    }
}
