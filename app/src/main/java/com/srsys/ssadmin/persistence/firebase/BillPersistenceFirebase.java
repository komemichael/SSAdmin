package com.srsys.ssadmin.persistence.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.srsys.ssadmin.objects.Bills;
import com.srsys.ssadmin.persistence.BillPersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillPersistenceFirebase implements BillPersistence
{
    List<Bills> bills  = new ArrayList<>();

    private FirebaseDatabase database;
    private DatabaseReference myRef;


    public BillPersistenceFirebase()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Bills");
    }

    @Override
    public void writeBill(String name) {
        Bills bill = new Bills(new Date(), name);
        myRef.setValue(bill);
    }

    @Override
    public List<Bills> getBills()
    {
        return this.bills;
    }
}
