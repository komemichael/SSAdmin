package com.srsys.ssadmin.persistence;

import com.srsys.ssadmin.objects.Bills;

import java.util.List;

public interface BillPersistence
{
    List<Bills> getBills();

    void writeBill(String name);
}
