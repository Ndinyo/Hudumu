package com.example.franklin.hudumu.services;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.franklin.hudumu.R;
import com.example.franklin.hudumu.sample2.Shopping2;
import com.example.franklin.hudumu.services.carwashing.CarWash;
import com.example.franklin.hudumu.services.carwashing.CarWashing;
import com.example.franklin.hudumu.services.carwashing.SuperWashFragment;
import com.example.franklin.hudumu.services.catering.Catering;
import com.example.franklin.hudumu.services.market.Market;
import com.example.franklin.hudumu.services.shopping.Shopping;
import com.example.franklin.hudumu.adapters.HudumaServicesAdapter;
import com.example.franklin.hudumu.services.pizza.Pizza;

public class HudumaServices extends AppCompatActivity {

    ListView mListView;

    String[] mServices = {"Pizza Ordering", "Market", "Car washing", "Catering"};

    Integer[] mServiceImages = {
            R.drawable.icons8_pizza,
            R.drawable.icons8_shopping_basket, R.drawable.icons8_automatic_car_wash_filled,
            R.drawable.icons8_tableware,
    };

    private void displayMarketCategory() {
        final String[] marketSelection = {"Fruits", "Vegetables"};

        AlertDialog.Builder builder = new AlertDialog.Builder(HudumaServices.this);
        builder.setTitle("Select Category!");
        builder.setItems(marketSelection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(HudumaServices.this, Shopping2.class);
                        startActivity(intent);
                        PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                        mBuilder.setContentIntent(pendingIntent);
                        break;
                    case 1:

                        break;
                }
            }
        });
        builder.setCancelable(true);
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_service);

        HudumaServicesAdapter hudumaServicesAdapter = new HudumaServicesAdapter(this, mServices, mServiceImages);
        mListView = (ListView) findViewById(R.id.shopping_list_view);
        mListView.setAdapter(hudumaServicesAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                //if (pos == 0) {

                    /*//displayShoppingDialogue();
                    Intent intent = new Intent(HudumaServices.this, Shopping.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
                    startActivity(intent);
                    //finish();
                    PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                    mBuilder.setContentIntent(pendingIntent);*/

                 if (pos == 0) {

                    Intent intent = new Intent(HudumaServices.this, Pizza.class);
                    startActivity(intent);
                    PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                    mBuilder.setContentIntent(pendingIntent);
                } else if (pos == 1) {

                    //displayMarketCategory();
                     Intent intent = new Intent(HudumaServices.this, Market.class);
                     startActivity(intent);
                     PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                             .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                     NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                     mBuilder.setContentIntent(pendingIntent);

                } else if (pos == 2) {

                    Intent intent = new Intent(HudumaServices.this, CarWashing.class);
                    startActivity(intent);
                    PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                    mBuilder.setContentIntent(pendingIntent);
                } else if (pos == 3) {
                    Intent intent = new Intent(HudumaServices.this, Catering.class);
                    startActivity(intent);

                    PendingIntent pendingIntent = TaskStackBuilder.create(HudumaServices.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(HudumaServices.this);
                    mBuilder.setContentIntent(pendingIntent);
                }
            }
        });
    }
}
