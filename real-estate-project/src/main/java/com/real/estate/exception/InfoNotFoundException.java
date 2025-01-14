package com.real.estate.exception;

public class InfoNotFoundException extends RuntimeException{
    
    public InfoNotFoundException(Integer infoId) {
        super("Info NOT Found With ID : " + infoId);
    }
    
    public InfoNotFoundException(String msg) {
        super(msg);
    }
}
