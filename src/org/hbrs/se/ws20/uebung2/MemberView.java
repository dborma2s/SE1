package org.hbrs.se.ws20.uebung2;

import java.util.List;

public class MemberView
{
    public void dump(List<Member> liste)
    {
        for (Member mem : liste) {

            System.out.println(mem);
        }
    }
}
