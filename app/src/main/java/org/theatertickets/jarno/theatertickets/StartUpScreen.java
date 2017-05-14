package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;


public class StartUpScreen extends Activity {

    TextView theaterReservationTextView;
    EditText username;
    EditText password;
    Button loginButton;
    TextView createUserTextView;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);

        theaterReservationTextView = (TextView) findViewById(R.id.TheaterReservation);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        createUserTextView = (TextView) findViewById(R.id.createUserTextView);


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginDataBaseAdapter.getUserPassword(username.getText().toString()).equals(password.getText().toString())) {
                    nextActivity("Profile/Movies");
                } else if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    nextActivity("ADMINPUOLI");
                }
            }
        });

        createUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextActivity("CreateUser");

            }
        });

        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    if(loginDataBaseAdapter.getUserPassword(username.getText().toString()).equals(password.getText().toString())) {
                        nextActivity("Profile/Movies");
                    }else if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                        nextActivity("ADMINPUOLI");
                    }

                }
                return true;
            }
        });






    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_start_up_screen, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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

    public void nextActivity(String a){
       if(a=="Reservation"){
           Intent i = new Intent(getApplicationContext(), Reservation.class);
           startActivity(i);
       }
       if(a=="CreateUser"){
           Intent iCreateUser = new Intent(getApplicationContext(), CreateUser.class);
           startActivity(iCreateUser);
       }
       if(a=="Profile/Movies"){

           Intent iMovies = new Intent(getApplicationContext(), Movies.class);
           iMovies.putExtra("NAME", username.getText().toString());
           startActivity(iMovies);
       }
        if(a=="ADMINPUOLI"){
            Intent i = new Intent(getApplicationContext(), AdminNavigation.class);
            startActivity(i);
        }
    }
}
