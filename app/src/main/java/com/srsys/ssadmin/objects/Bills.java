package com.srsys.ssadmin.objects;


public class Bills
{
    String date;
    String name;
    String type;
    String amount;

    String accountName;
    String id;

    public Bills(String name, String date, String amount, String type)
    {
        this.date = date;
        this.name = name;
        this.type = type;
        this.amount = amount;
    }

    public Bills()
    {

    }

    public String getDate()
    {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName()
    {
        return name;
    }

    public void setNameM(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }
}
