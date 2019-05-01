package com.srsys.ssadmin.persistence.firebase;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.srsys.ssadmin.objects.Bills;
import com.srsys.ssadmin.persistence.BillPersistence;

import java.util.ArrayList;
import java.util.List;

public class BillPersistenceFirebase implements BillPersistence
{
    List<Bills> bills;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String logged_in_user;


    public BillPersistenceFirebase()
    {
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef = database.getReference().child("Bills");

        bills  = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bills.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Bills b = ds.getValue(Bills.class);
                    bills.add(b);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DB", "DB Error");
            }
        });
    }

    @Override
    public void writeBill(String name, String date, String amount, String type) {
        Bills bill = new Bills(name, date, amount, type);
        bill.setId(myRef.push().getKey());
        bill.setAccountName(logged_in_user);
        myRef.child(bill.getId()).setValue(bill);
    }

    @Override
    public boolean deleteBill(Bills bill)
    {
        myRef.child(bill.getId()).removeValue();
        if (myRef.child(bill.getId()).equals(null))
            return true;
        return false;
    }

    @Override
    public List<Bills> getBills()
    {
        return this.bills;
    }
}
