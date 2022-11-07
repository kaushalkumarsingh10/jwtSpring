package com.kaushal.jwt.dao;

import java.io.Serializable;

public class Response implements Serializable {
    private String token;

    public Response(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
