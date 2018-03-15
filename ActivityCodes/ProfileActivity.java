package com.example.welcome.navdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        ImageButton btneditprofile;
        ImageButton btneditprofilepic;
        Button btnsaveChanges;
        EditText namefield,emailfield,phonenofield;
        Bundle extras;
    public static final String UPLOAD_URL = "https://amanksinghme.000webhostapp.com/Android/uploadimage.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MY MESSAGE";
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "goto the home page..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        init();
        //buttonChoose.setOnClickListener(this);
        //buttonUpload.setOnClickListener(this);
        namefield.setFocusable(false);
        emailfield.setFocusable(false);
        phonenofield.setFocusable(false);
        if(extras==null){
            loginIntent();
        }
        btneditprofile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                btneditprofilepic.setVisibility(view.VISIBLE);
//                btnsaveChanges.setVisibility(view.VISIBLE);
//                namefield.setFocusable(true);
//                emailfield.setFocusable(true);
//                phonenofield.setFocusable(true);
                Intent myIntent=new Intent(ProfileActivity.this,EditProfileActivity.class);
                myIntent.putExtras(extras);
                ProfileActivity.this.startActivity(myIntent);
                finish();
            }
        });

        btnsaveChanges.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                btneditprofilepic.setVisibility(view.INVISIBLE);
                namefield.setFocusable(false);
                emailfield.setFocusable(false);
                phonenofield.setFocusable(false);
                btnsaveChanges.setVisibility(view.INVISIBLE);
            }
        });
    }
    void loginIntent(){
        Intent myIntent = new Intent(ProfileActivity.this,LoginActivity.class);
        ProfileActivity.this.startActivity(myIntent);
        System.exit(0);
    }

    void init(){
        btneditprofile=(ImageButton)findViewById(R.id.editButton);
       // btneditprofilepic=(ImageButton)findViewById(R.id.buttonChoose);
        namefield=(EditText)findViewById(R.id.et_username);
        emailfield=(EditText)findViewById(R.id.et_useremail);
        phonenofield=(EditText)findViewById(R.id.et_userphone);
        btnsaveChanges=(Button)findViewById(R.id.btnsavechanges);
        extras=getIntent().getExtras();
        //buttonView = (Button) findViewById(R.id.buttonViewImage);
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
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            Intent myIntent = new Intent(ProfileActivity.this,MainActivity_Nav.class);
            // myIntent.putExtras(extras);
            this.startActivity(myIntent);
            System.exit(0);
            // Handle the camera action
        } else if (id == R.id.nav_login) {
            Intent myIntent = new Intent(ProfileActivity.this,LoginActivity.class);
            // myIntent.putExtras(extras);
            this.startActivity(myIntent);
            System.exit(0);

        } else if (id == R.id.nav_home) {
            Intent myIntent = new Intent(ProfileActivity.this,HomeActivity.class);
            myIntent.putExtras(extras);
            this.startActivity(myIntent);
            System.exit(0);

        } else if (id == R.id.nav_profile) {


        } else if (id == R.id.nav_logout) {
            Intent myIntent = new Intent(ProfileActivity.this,LoginActivity.class);
           // myIntent.putExtras(extras);
            this.startActivity(myIntent);
            System.exit(0);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
