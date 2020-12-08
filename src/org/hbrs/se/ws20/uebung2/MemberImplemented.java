package org.hbrs.se.ws20.uebung2;

import java.io.Serializable;

public class MemberImplemented implements Member, Serializable {


    private Integer id;

    public MemberImplemented(Integer id){
        this.id = id;
    }

    @Override
    public Integer getID(){
        return id;
    }

    @Override
    public String toString(){
        return "Member (ID = " + id  + ")";
    }
}
