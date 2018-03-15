package com.example.welcome.navdrawer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewPostActivity extends AppCompatActivity {
    Button btnnewpost;Bundle extras;
    EditText addnewpost;
    String USERID,PASSWORD;
    private final String LOGIN_URL="HTTP_PATH/newpost.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        btnnewpost=(Button)findViewById(R.id.btnpost);
        addnewpost=(EditText)findViewById(R.id.newPost);
        extras = getIntent().getExtras();
        if(extras!=null){
             USERID=extras.getString("userId");
             PASSWORD=extras.getString("password");

        }
        btnnewpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }
    void loginuser() {
        System.out.println("SUCESS IN GET_DATA \n");
        String POST=addnewpost.getText().toString().trim();
        System.out.println(POST);
        System.out.println(POST.length());
        if(POST.length()==0){
            Toast.makeText(getApplicationContext(),"your post body is empty..",Toast.LENGTH_LONG).show();
            return;
        }
        login(USERID,PASSWORD,POST);
    }

    void login(String Uname,String Password,String post) {
        String POST=getSingleString(post);
        String urlsuffix="?userid="+Uname+"&password="+Password+"&post="+POST;
        System.out.println("urlsuffix"+urlsuffix);
        class LoginUser extends AsyncTask<String,Void,String> {
            protected void onPreExecute() {
                super.onPreExecute();
                // Toast.makeText(getApplicationContext(),"logging you in....",300).show();
            }
            protected void onPostExecute(String result) {
                if(result.length()==0 || result.equals("ERROR")) {
                    Toast.makeText(getApplicationContext(),"Please, fill all fields",Toast.LENGTH_LONG).show();
                    System.out.println("RETURN :"+result);
                }
                else
                if(result.equals("NOT_FOUND")) {
                    Toast.makeText(getApplicationContext(),"You are not registered...",Toast.LENGTH_LONG).show();
                    System.out.println("RETURN :"+result);
                }
                else
                    Toast.makeText(getApplicationContext(),"Congrates,Joke was posted sucessfully....",Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                // TODO Auto-generated method stub
                String s=params[0];
                BufferedReader br=null;
                try {
                    System.out.println("URL started\n");
                    URL url=new URL(LOGIN_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=br.readLine();
                    System.out.println("OK  :"+result);
                    return result;
                }catch(Exception ex) {
                    System.out.println("FAILED To LOG-IN \n");
                    return "";
                }
            }

        }
        LoginUser ur=new LoginUser();
        ur.execute(urlsuffix);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
    String getSingleString(String str){
        String post=new String("");
        String[] strings=str.split("\n");
        System.out.println(strings.length);
        for(int i=0;i<strings.length;i++) {
            char[] string = strings[i].toCharArray();
            for (int j = 0; j < string.length; j++) {
                if (string[j] == ' ')
                    string[j] = '^';
                //System.out
            }
            strings[i]=String.valueOf(string);
        }
        for(int i=0;i<strings.length;i++)
            post=post+""+strings[i]+"\\n";
        System.out.println(post);

//        String myName = "domanokz";
//        char[] myNameChars = myName.toCharArray();
//        myNameChars[4] = 'x';
//        myName = String.valueOf(myNameChars);
            return post;
    }



}
