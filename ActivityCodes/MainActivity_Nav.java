package com.example.welcome.navdrawer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.InputStreamReader;

public class MainActivity_Nav extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private Button btnRegister,gotoLogin;
        private EditText userId,emailId,password,repeatPassword;
    private final String REGISTER_URL="https://amanksinghme.000webhostapp.com/Android/register.php";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("HERE INIT");
        setContentView(R.layout.activity_main__nav);
        //System.out.println("HERE INIT");
        //init();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "you are not loggedIn..", Snackbar.LENGTH_LONG)
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

        //System.out.println("AFTER INIT");
        init();

        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent=new Intent(MainActivity_Nav.this,LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    void registerUser() {
        //String Fname=fname.getText().toString().trim();
        //String Lname=lname.getText().toString().trim();
        String userid=userId.getText().toString().trim();
        String emailid=emailId.getText().toString().trim();
        String Password=password.getText().toString().trim();
        String repeatpassword=repeatPassword.getText().toString().trim();
        System.out.println("SUCESS IN GET_DATA \n");
        if(userid.length()==0){
            Toast.makeText(getApplicationContext(),"userId inValid...",Toast.LENGTH_LONG).show();
            return;
        }
        boolean isValidEmail=validateEmail(emailid);
        if(!isValidEmail){
            Toast.makeText(getApplicationContext(),"Invalid email-Id",Toast.LENGTH_LONG).show();
            return;
        }
        if(Password.length()<5){
            Toast.makeText(getApplicationContext(),"password is very small",Toast.LENGTH_LONG).show();
            return;
        }
        if(Password.equals(repeatpassword))
            register(userid,emailid,Password);
        else {
            Toast.makeText(getApplicationContext(),"passwords mismatch...",Toast.LENGTH_LONG).show();
        }
    }

    void register(String Uname,String Uemail,String Password) {
        String urlsuffix="?userid="+Uname+
                "&useremail="+Uemail+"&password="+Password;
        System.out.println("urlsuffix"+urlsuffix);
        class RegisterUser extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            //loading.setMax(100);
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity_Nav.this, "Registering you..", "Please wait...",true,true);

                Toast.makeText(getApplicationContext(),"Running....",Toast.LENGTH_LONG).show();
                //loading=ProgressBar.show()
            }
            protected void onPostExecute(String result) {
                //super.onPreExecute();
                loading.dismiss();
                if(result.equals("NO_I")){
                    Toast.makeText(getApplicationContext(),"No Internet Connection....",Toast.LENGTH_LONG).show();
                    return;
                }
                else

                    Toast.makeText(getApplicationContext(),"Completed....",Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub
                String s=params[0];
                BufferedReader br=null;
                String result="NO_I";
                try {
                    System.out.println("URL started\n");
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    br=new BufferedReader(new InputStreamReader(con.getInputStream()));

                    result=br.readLine();
                    System.out.println(result);
                   // Intent intent=new Intent(MainActivity_Nav.this,LoginActivity.class);
                   // startActivity(intent);
                    Toast.makeText(getApplicationContext(),"You are Registered now..",Toast.LENGTH_LONG).show();
                    return result;
                }catch(Exception ex) {
                    System.out.println(result);
                    System.out.println("FAIL IN REGISTRATION \n");
                    //
                    return result;
                }
            }

        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlsuffix);

    }
    boolean validateEmail(String password){
        int countAT=0;
        int countS=0;
        for(int i=0;i<password.length();i++){
            if(password.charAt(i)=='@')
                countAT++;
            else
            if(password.charAt(i)==' ')
                countS++;
        }
        if(countAT!=1 || countS>0)
            return false;
        else
            return true;
    }
    private void init(){
        btnRegister=(Button)findViewById(R.id.btnRegister);
        gotoLogin=(Button)findViewById(R.id.gotoLogin);
        userId=(EditText)findViewById(R.id.userId);
        emailId=(EditText)findViewById(R.id.emailId);
        password=(EditText)findViewById(R.id.password);
        repeatPassword=(EditText)findViewById(R.id.repeatPassword);
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
        getMenuInflater().inflate(R.menu.main_activity__nav, menu);
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
            return false;
        } else if (id == R.id.nav_login) {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_home) {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
            System.exit(0);


        } else if (id == R.id.nav_profile) {
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);
            System.exit(0);

        } else if (id == R.id.nav_logout) {
            Intent intent=new Intent(this,MainActivity_Nav.class);
            startActivity(intent);
            System.exit(0);

        }else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
