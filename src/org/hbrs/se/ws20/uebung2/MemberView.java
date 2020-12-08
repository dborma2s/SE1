package org.hbrs.se.ws20.uebung2;

import java.util.List;

public class MemberView {

    public void dump(List<Member> list){
        for(Member m : list){
            System.out.println(m);
        }
    }
}
