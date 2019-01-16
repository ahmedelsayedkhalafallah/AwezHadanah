package com.example.ahmed.awezhadanah;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmed.awezhadanah.adapter.order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder mBuilder;
    PendingIntent pending;
    FirebaseDatabase fdb ;
    DrawerLayout drawerLayout;
    DatabaseReference dbr;
    EditText name,age,phone,comment;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        //initialize input fields
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        comment = findViewById(R.id.comment);
        //initialize reference to binding requests section
         dbr = fdb.getInstance().getReference().child("binding");


         //notification
        dbr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot Single:dataSnapshot.getChildren())
                {
                    order p = Single.getValue(order.class);
                    String token = FirebaseInstanceId.getInstance().getToken();
                    Log.d(TAG,"Token: "+ token);

//                    Toast.makeText(MainActivity.this, p.getName(), Toast.LENGTH_SHORT).show();
//                     mBuilder = new NotificationCompat.Builder(MainActivity.this,"default")
//                            .setSmallIcon(R.drawable.icon)
//                            .setContentTitle(p.getName())
//                            .setContentText("new order with baby name : "+p.getName()+" arrived in : "+p.getDate()+" Hour: "+p.getTime())
//                            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                     .setContentIntent(pending)
//                     .setAutoCancel(true);
//                     NotificationManagerCompat notificationManagerCompat =  NotificationManagerCompat.from(MainActivity.this);
//                     notificationManagerCompat.notify(1,mBuilder.build());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Initiating Drawer navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Handle navigation view item clicks here.
                        switch (menuItem.getItemId()) {

                            case R.id.log: {
                                Intent intent = new Intent(getApplicationContext(),login.class);
                                startActivity(intent);
                                break;
                            }
                            case R.id.about: {
                                Intent intent = new Intent(getApplicationContext(), about.class);
                                startActivity(intent);
                                break;
                            }
                        }
                        //close navigation drawer


                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static String random() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public void send(View view) {
        if(phone.getText().toString().length() == 11){
        String s = random();
        dbr.child(s).child("name").setValue(name.getText().toString());
        dbr.child(s).child("age").setValue(age.getText().toString());
        dbr.child(s).child("phone").setValue(phone.getText().toString());
        dbr.child(s).child("comment").setValue(comment.getText().toString());
        dbr.child(s).child("id").setValue(s);
        dbr.child(s).child("date").setValue(getCurrentDate());
        dbr.child(s).child("time").setValue(getCurrentTime());
        dbr.child(s).child("ddate").setValue("");
        dbr.child(s).child("dtime").setValue("");

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("شكرا لكم,\nتم استلام طلبكم بنجاح وسيتم التواصل معكم فى أسرع وقت");
        dlgAlert.setTitle("عاوز حضانة");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
        name.setText("");
        age.setText("");
        phone.setText("");
        comment.setText("");}
        else{
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("لقد قمت بادخال رقم هاتف خاطئ ، من فضلك قم بإدخال رقم صحيح");
            dlgAlert.setTitle("رقم هاتف خاطئ");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
        }

    }


    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }


    public String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }


}
