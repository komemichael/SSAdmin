package com.srsys.ssadmin.Application;

import com.srsys.ssadmin.persistence.BillPersistence;
import com.srsys.ssadmin.persistence.MessagePersistence;
import com.srsys.ssadmin.persistence.UserPersistence;

import com.srsys.ssadmin.persistence.firebase.BillPersistenceFirebase;
import com.srsys.ssadmin.persistence.firebase.MessagePersistenceFirebase;
import com.srsys.ssadmin.persistence.firebase.UserPersistenceFirebase;

public class Services
{
    private static BillPersistence billPersistence = null;
    private static UserPersistence userPersistence = null;
    private static MessagePersistence messagePersistence = null;

    public static synchronized BillPersistence getBillPersistence()
    {
        if (billPersistence == null)
        {
            billPersistence = new BillPersistenceFirebase();
        }
        return billPersistence;
    }

    public static synchronized UserPersistence getUserPersistence()
    {
        if (userPersistence== null)
        {
            userPersistence = new UserPersistenceFirebase();
        }
        return userPersistence;
    }

    public static synchronized MessagePersistence getMesesagePersistence()
    {
        if (messagePersistence== null)
        {
            messagePersistence = new MessagePersistenceFirebase();
        }
        return messagePersistence;
    }
}
