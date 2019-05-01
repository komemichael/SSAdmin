package com.srsys.ssadmin.objects;

import java.util.List;

public class Account
{
    String username;
    String password;

    List<Bills> bills;


    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public void setBills(List<Bills> bills) {
        this.bills = bills;
    }
}
