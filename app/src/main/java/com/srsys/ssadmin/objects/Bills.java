package com.srsys.ssadmin.objects;

import java.util.Date;

public class Bills
{
    Date date;
    String namebill;

    public Bills(Date date, String namebill)
    {
        this.date = date;
        this.namebill = namebill;
    }

    public Date getDate()
    {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNamebill()
    {
        return namebill;
    }

    public void setNamebill(String namebill) {
        this.namebill = namebill;
    }
}
