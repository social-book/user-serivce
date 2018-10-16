package com.social.prpo.samplejdbc;

import java.io.Serializable;

abstract public class Entity implements Serializable {

    private int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
