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


    public Boolean addBill(String name)
    {
        try {
            data.writeBill(name);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
