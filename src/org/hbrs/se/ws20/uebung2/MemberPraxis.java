package org.hbrs.se.ws20.uebung2;

public class MemberPraxis implements Member
{
    private Integer id;
    public MemberPraxis(Integer id)
    {
        this.id = id;
    }
    @Override
    public Integer getID() {
        return this.id;
    }
    public String toString()
    {
        return "Member (ID = [" + id +"])";
    }
}
