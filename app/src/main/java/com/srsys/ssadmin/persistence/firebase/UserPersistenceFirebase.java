package com.srsys.ssadmin.persistence.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.srsys.ssadmin.objects.User;
import com.srsys.ssadmin.persistence.UserPersistence;

import java.util.ArrayList;
import java.util.List;

public class UserPersistenceFirebase implements UserPersistence
{
    List<User> users;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public UserPersistenceFirebase()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("User");

        users  = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    User u = ds.getValue(User.class);
                    users.add(u);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DB", "DB Error");
            }
        });
    }

    @Override
    public void writeUser(String username, String pass, String email) {
        User user = new User(username, pass, email);
        user.setId(myRef.push().getKey());
        user.setName(username);
        myRef.child(user.getId()).setValue(user);
    }

    @Override
    public boolean deleteUser(User user)
    {
        myRef.child(user.getId()).removeValue();
        if (myRef.child(user.getId()).equals(null))
            return true;
        return false;
    }

    @Override
    public List<User> getUsers()
    {
        return this.users;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }
}
