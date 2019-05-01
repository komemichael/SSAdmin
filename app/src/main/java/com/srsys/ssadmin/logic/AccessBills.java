package com.srsys.ssadmin.logic;

import com.srsys.ssadmin.Application.Services;
import com.srsys.ssadmin.objects.Bills;
import com.srsys.ssadmin.persistence.BillPersistence;

import java.util.List;

public class AccessBills
{
    private BillPersistence data;

    public AccessBills()
    {
        data = Services.getBillPersistence();
    }

    public AccessBills(BillPersistence data)
    {
        this();
        this.data = data;
    }

    public List<Bills> getBills()
    {
        return data.getBills();
    }

    public boolean deleteBill(Bills bill)
    {
        return data.deleteBill(bill);
    }

    public String addBill(String name, String date, String amount, String type)
    {
        try {
            Boolean valid = true;
            String errorMessage = "";

            if (type.equals("Select Bill"))
            {
                valid = false;
                errorMessage = "Select bill type";
            }

            if (amount.equals(""))
            {
                valid = false;
                errorMessage = "Enter amount paid";
            }

            if (date.equals(""))
            {
                valid = false;
                errorMessage = "Select date of payment";
            }

            if (name.equals(""))
            {
                valid = false;
                errorMessage = "Empty Bill name";
            }

            if (valid == true)
                data.writeBill(name, date, amount, type);
            return errorMessage;
        }
        catch (Exception e)
        {
            return "Error";
        }
    }
}
