package com.secretdevelopersltd.dexian.khoj;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseEnable extends android.app.Application  {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
