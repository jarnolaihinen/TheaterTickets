package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CreateUser extends Activity {
    private EditText Name;
    private EditText Username;
    private EditText Password;
    private Spinner City;
    String kaupunki;
    List<String> Citylist;
    ImageView Checkbox;
    ImageView Checkbox2;
    Button save;
    Button clear;
    LoginDataBaseAdapter loginDataBaseAdapter;
    ImageView cityImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        cityImage = (ImageView)findViewById(R.id.cityImage);

        Name = (EditText)findViewById(R.id.Name);
        Username = (EditText)findViewById(R.id.UserName);
        Password = (EditText)findViewById(R.id.Password);
        City = (Spinner)findViewById(R.id.City);
        Checkbox = (ImageView)findViewById(R.id.Checkbox);
        Checkbox2 = (ImageView)findViewById(R.id.Checkbox2);
        save = (Button)findViewById(R.id.saveButton);
        clear = (Button)findViewById(R.id.clearButton);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);

        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Citylist = new ArrayList<String>();
        Citylist.add("Turku");
        Citylist.add("Helsinki");
        Citylist.add("Tampere");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Citylist);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        City.setAdapter(dataAdapter2);

        City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kaupunki = Citylist.get(i);
                if(Citylist.get(i).equals("Turku")){
                    cityImage.setBackgroundResource(R.drawable.turku);
                }
                if(Citylist.get(i).equals("Tampere")){
                    cityImage.setBackgroundResource(R.drawable.tampere);
                }
                if(Citylist.get(i).equals("Helsinki")){
                    cityImage.setBackgroundResource(R.drawable.helsinki);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(sameUserName()){
                        Checkbox.setImageResource(R.drawable.good);
                    }else{
                        Checkbox.setImageResource(R.drawable.bad);
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username.setText("");
                Name.setText("");
                Password.setText("");
            }
        });

        save.setOnClickListener(new View.OnClickListener() { //VITUSTI VIRHEENKÄSITTELYÄ
            @Override
            public void onClick(View v) {
                loginDataBaseAdapter.insertUser(Username.getText().toString(),Password.getText().toString(), Name.getText().toString(),kaupunki); //Name on EMAIL
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_user, menu);
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

    public boolean sameUserName(){

        if(loginDataBaseAdapter.checkAvailability(Username.getText().toString())){
            return true;
        } else{
            return false;
        }


    }
}
