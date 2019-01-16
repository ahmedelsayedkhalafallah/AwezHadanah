package com.example.ahmed.awezhadanah;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String TAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"New Token: "+ refreshToken);
    }

}
