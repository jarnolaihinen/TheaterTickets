package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class Reservation extends Activity implements other_Fragment.OnDataPass, seatsFragment.OnDataPass{

    ImageView preview;
    TextView textView;
    Button reservationbutton;
    static Spinner dateSpinner;
    static Spinner timeSpinner;
    int dateSpinnerPosition;
    int timeSpinnerPosition;
    String dateSpinnerDate;
    String timeSpinnerTime;
    String theater; //elokuvateatteri
    String movie; //leffan nimi
    int movieID; //leffan id
    LoginDataBaseAdapter loginDataBaseAdapter;
    List<String> showtimes;
    List<String> list;
    List <String> adapterList;
    List<ImageButton> kolmossaliButtonList;
    List<ImageButton> kakkossaliButtonList;
    String reservations;
    String hall;    //sali
    String time;    //leffan aika
    String user;  //username
    int userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);


        theater = getIntent().getStringExtra("THEATER");
        Log.v(theater,"Kaupunki!");
        movie = getIntent().getStringExtra("NAME");
        user = getIntent().getStringExtra("USER");

        dateSpinner = (Spinner)findViewById(R.id.dateSpinner);
        timeSpinner = (Spinner)findViewById(R.id.timeSpinner);
        textView = (TextView)findViewById(R.id.textView);
        reservationbutton = (Button)findViewById(R.id.button);
        preview = (ImageView) findViewById(R.id.previewImage);
        textView.setText("RESERVATION FOR " + movie + " in "+ theater);


        showtimes = new ArrayList<String>();

        reservationbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("leffan tietoja", movie + "," + theater + "," + hall +"," + time + "," + userID + "," + reservations);
                if (reservations != null) {
                    String[] temp = reservations.split(";");
                    Log.v(reservations, "viimeinen alkio");
                    if (!temp[temp.length - 1].equals(null)) {

                        loginDataBaseAdapter.insertReservation(movie, theater, hall, time, userID, movieID, reservations);
                        finish();
                    }

                }
            }
        });


        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        userID = loginDataBaseAdapter.getIDforUser(user);
        preview.setImageURI(Uri.parse(loginDataBaseAdapter.getMovieUriwithName(movie)));


        //metodi joka hakee leffojen päivät

        int thisdayi = getDay();

        list = new ArrayList<String>();
        list.add(Integer.toString(thisdayi));
        list.add(Integer.toString(thisdayi+1));
        list.add(Integer.toString(thisdayi+2));
        list.add(Integer.toString(thisdayi+3));
        list.add(Integer.toString(thisdayi+4));
        list.add(Integer.toString(thisdayi+5));
        list.add(Integer.toString(thisdayi+6));
        list.add(Integer.toString(thisdayi+7));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dataAdapter);

        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateTimeSpinner(position+getDay(), list);

                Log.v("?", "SPINNERISTÄ VALITTU " + Integer.toString(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //seats_fragment on kolmossali
        //fragment_other on kakkossali
        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                timeSpinnerPosition = position;
                //oikea päivä ja sali
                timeSpinnerTime = adapterList.get(timeSpinnerPosition);
                String[] parts = timeSpinnerTime.split(" ");
                String[] parts2 = parts[2].split(";");
                movieID = Integer.parseInt(parts2[1]);
                time = parts2[0];
                FragmentManager fm =getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();

                parentView.getItemAtPosition(position);
                hall = parts[0];
                Log.v(parts[0],"sali");
                if(parts[0].equals("Kolmossali")){
                    seatsFragment seatsFragment = new seatsFragment();
                    fragmentTransaction.replace(android.R.id.content, seatsFragment);

                    //fillReservation("Kolmossali", loginDataBaseAdapter.checkSeats(parts2[1]));



                }else {
                    other_Fragment other_fragment = new other_Fragment();
                    fragmentTransaction.replace(android.R.id.content, other_fragment);

                }
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reservation, menu);
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


  //  public void updateDateSpinner(){


   // }
    public void updateTimeSpinner(int position, List<String> list1){

        dateSpinnerDate=list1.get(position-getDay());
        Log.v("Päivä", dateSpinnerDate);

        //haetaan tietokannasta datespinnerdatella
        adapterList = new ArrayList<String>();
        adapterList = loginDataBaseAdapter.availableMoviesWithThisDate(theater, movie
                , dateSpinnerDate);
        //lisätään uuden ajat toiseen spinneriin
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, adapterList);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(dataAdapter2);
    }

   /* public void fillReservation(String hall, List<String> list){

        if (hall.equals("Kolmossali")) {
            for(int i=0;i<kolmossaliButtonList.size();i++){
                for(int j=0;j<list.size();j++) {
                    if (Integer.toString(i).equals(list.get(j))){
                       // kolmossaliButtonList.get(i).setBackground(); asettaa punaisen taustan kun löytyy varaus tietokannasta
                        //tsekataan button boolean tai vastaava
                    }
                }
            }

        }else{

        }


    } */

    public int getDay(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.getDefault());
        Date date = new Date();
        String thisday = dateFormat.format(date);
        return Integer.parseInt(thisday);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    public String getMovietoFragment(){
        return Integer.toString(movieID);
    }

    public List<String> getAllInfoMovie(){
        // (String name, String theater, String sali, String aika, Integer user_id, Integer id, String varatut)
        List<String> temp = new ArrayList<String>();
        temp.add(movie);
        temp.add(theater);
        temp.add(time);
        temp.add(Integer.toString(userID));
        temp.add(Integer.toString(movieID));
        return temp;
    }

    @Override
    public void onDataPass(String data) {
        reservations=data;
    }
}
