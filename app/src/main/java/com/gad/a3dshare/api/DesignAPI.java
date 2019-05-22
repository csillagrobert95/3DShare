package com.gad.a3dshare.api;

import com.gad.a3dshare.api.model.Design;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DesignAPI {
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;

    public DesignAPI() {
        initDB();
    }

    private void initDB(){
        this.firebaseDatabase = FirebaseDatabase.getInstance();
        this.mDatabase = this.firebaseDatabase.getReference();
    }

    public void getDesigns(){

        mDatabase.child("designs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("Data: " + snapshot.getValue());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
