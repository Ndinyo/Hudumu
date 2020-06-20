package com.example.franklin.hudumu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.MenuItemCompat;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.franklin.hudumu.adapters.HomeGridviewAdapter;
import com.example.franklin.hudumu.authentication.ClientProfile;
import com.example.franklin.hudumu.authentication.LoginHuduma;
import com.example.franklin.hudumu.paymentplan.PaymentPlans;
import com.example.franklin.hudumu.sample.Main2Activity;
import com.example.franklin.hudumu.sample2.ProductActivity;
import com.example.franklin.hudumu.sample2.Shopping2;
import com.example.franklin.hudumu.services.HudumaServices;
import com.example.franklin.hudumu.utils.ContactUs;
import com.example.franklin.hudumu.utils.Faq;
import com.example.franklin.hudumu.utils.History;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fa);
        //fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();
        //}
        //});

        //Activate search
        handleIntent(getIntent());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Main Home Page Grid view
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new HomeGridviewAdapter(this));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, "Home view: " + i, Toast.LENGTH_SHORT).show();
                if (i == 0) {//Services
                    Intent intent = new Intent(MainActivity.this, HudumaServices.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
                    startActivity(intent);
                    //finish();
                    PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                    mBuilder.setContentIntent(pendingIntent);

                    //android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(MainActivity.this);
                    //builder.setContentIntent(pendingIntent);

                    //NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    //manager.notify(1, builder.build());

                } else if (i == 1) {//Profile
                    Intent intent = new Intent(MainActivity.this, Testing.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "This page allows you to edit and Update your profile!", Toast.LENGTH_LONG).show();
                    PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                    mBuilder.setContentIntent(pendingIntent);

                } else if (i == 2) {//Payment plans

                    Intent intent = new Intent(MainActivity.this, PaymentPlans.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
                    startActivity(intent);
                    //finish();
                    PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                    mBuilder.setContentIntent(pendingIntent);


                } else if (i == 3) {//History
                    Intent intent = new Intent(MainActivity.this, History.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
                    startActivity(intent);
                    //finish();
                    PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                    mBuilder.setContentIntent(pendingIntent);
                } else if (i == 4) {//FAQ
                    Intent intent = new Intent(MainActivity.this, Faq.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
                    startActivity(intent);
                    //finish();
                    PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                    mBuilder.setContentIntent(pendingIntent);
                } else if (i == 5) {//Contact us
                    MainActivity.this.getSupportFragmentManager().beginTransaction().add(0, ContactUs.newInstance()).addToBackStack(null).replace(R.id.containerID, ContactUs.newInstance()).commit();
                }
            }
        });
    }

    private String mSearchText;

    public String getSearchText() {
        return mSearchText;
    }

    public void setSearchText(String searchText) {
        this.mSearchText = searchText;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    /*protected void onResume(){
        super.onResume();
        String searchText = ((MainActivity)this.getApplicationContext()).getSearchText();
    }*/

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //((MainActivity) this.getApplicationContext()).setSearchText(query);
            //this.finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //      if (mAuthStateListener != null){
        //        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        //  }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        //getMenuInflater().inflate(R.menu.main, menu);

        //Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_search_bar).getActionView();

        //Assumes the current activity as searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);//Don't iconify the widget; expand it by default

        //searchView.setQueryHint(getString(R.string.place_autocomplete_search_hint));

        //searchView.setOnQueryTextListener(this);

        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    private void logoutApp() {
        FirebaseAuth.getInstance().signOut();
        //Ensure users return to Login Page
        Intent intent = new Intent(MainActivity.this, LoginHuduma.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Flags - remove current activity from stack
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout) {
            logoutApp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //MainActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.containerID, ServicesFragment.newInstance()).commit();
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(MainActivity.this, ClientProfile.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            //finish();
            PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
            mBuilder.setContentIntent(pendingIntent);

        } else if (id == R.id.nav_services) {
            Intent intent = new Intent(MainActivity.this, HudumaServices.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//Exit and start a new task
            startActivity(intent);
            //finish();
            PendingIntent pendingIntent = TaskStackBuilder.create(MainActivity.this).addNextIntentWithParentStack(intent)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
            mBuilder.setContentIntent(pendingIntent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
