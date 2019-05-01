package com.srsys.ssadmin.Application;

import com.srsys.ssadmin.persistence.BillPersistence;
import com.srsys.ssadmin.persistence.firebase.BillPersistenceFirebase;

public class Services
{
    private static BillPersistence billPersistence = null;

    public static synchronized BillPersistence getBillPersistence()
    {
        if (billPersistence == null)
        {
            billPersistence = new BillPersistenceFirebase();
        }
        return billPersistence;
    }
}
