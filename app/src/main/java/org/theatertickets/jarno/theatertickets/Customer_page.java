package org.theatertickets.jarno.theatertickets;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Customer_page extends Activity {
    String user, city;
    List<String> Citylist;
    Spinner City;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView userNameTextView, userEmailTextView;
    ListView reservationListView;
    ImageView cityImage;
    List<String> userReservations;
    ArrayAdapter<String> adapter;
    int localposition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_page);
        final ActionBar actionBar = getActionBar();
        user = getIntent().getStringExtra("NAME");
        actionBar.setTitle("Hello, " + user);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);

        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        reservationListView = (ListView)findViewById(R.id.listView);
        //otetaan tietokannasta käyttäjän userid:llä varaukset ja lyödään listviewiin

        cityImage= (ImageView)findViewById(R.id.cityImage);



        userReservations = new ArrayList<String>();
        userReservations = loginDataBaseAdapter.getUsersReservations(Integer.toString(loginDataBaseAdapter.getIDforUser(user)));
        city = loginDataBaseAdapter.getCity(user);

        Log.v(city, "=kaupunki");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, userReservations);


        // Assign adapter to ListView
        reservationListView.setAdapter(adapter);

        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                localposition=i;
                AlertDialog.Builder adb = new AlertDialog.Builder(Customer_page.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + userReservations.get(i) + "?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String toDataBase="";
                        Log.v(userReservations.get(localposition), "varauslistview");
                        String[] resID = userReservations.get(localposition).split("%");    //%ID
                        Log.v(userReservations.get(localposition), "userreservations");
                        String seatsMID = loginDataBaseAdapter.getReservationSeatsForUser(resID[1]);
                        Log.v(seatsMID, "IIIIDE");
                        String[] seatsandMovieID = seatsMID.split("-");
                        String reservedSeats = seatsandMovieID[0];  //splitataan ja omaan taulukkoon
                        Log.v(reservedSeats, "reservedseats");
                        String[] parsable = reservedSeats.split(";");
                        Log.v("splitattu", "databaseen");
                        String movieIDtemp = seatsandMovieID[1];
                        Log.v(movieIDtemp, "movieID");
                        List<Integer> list = new ArrayList<Integer>();
                        list = loginDataBaseAdapter.getReservationForMovie(movieIDtemp);


                        for (int j=0;j<parsable.length;j++){
                            for(int k=0;k<list.size();k++){

                                if(Integer.parseInt(parsable[j])==list.get(k)){
                                    list.remove(k);
                                }
                            }

                        }

                        for(int a=0;a<list.size();a++){
                            toDataBase = toDataBase + ";" + list.get(a);
                            Log.v(toDataBase, "databaseen");
                        }

                        //toDataBase tietokantaan
                        Log.v(movieIDtemp, toDataBase);

                        loginDataBaseAdapter.updateMoviesReservation(movieIDtemp,toDataBase);
                        userReservations.remove(localposition);
                        updateListView();


                    }
                });
                adb.show();








            }
        });



        City = (Spinner)findViewById(R.id.cityselect);
        Citylist = new ArrayList<String>();
        if(city.equals("Turku")){
            Citylist.add("Turku");
            Citylist.add("Helsinki");
            Citylist.add("Tampere");
        }
        if(city.equals("Tampere")){
            Citylist.add("Tampere");
            Citylist.add("Turku");
            Citylist.add("Helsinki");
        }
        if(city.equals("Helsinki")){
            Citylist.add("Helsinki");
            Citylist.add("Turku");
            Citylist.add("Tampere");
        }
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Citylist);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        City.setAdapter(dataAdapter2);

        City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loginDataBaseAdapter.updateCity(user,Citylist.get(i));
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
        userNameTextView = (TextView)findViewById(R.id.nameText);
        userEmailTextView = (TextView)findViewById(R.id.emailText);
        userNameTextView.setText(user);
        userEmailTextView.setText(loginDataBaseAdapter.getEmail(user));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_customer_page, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    public void updateListView(){
        reservationListView.setAdapter(adapter);
    }
}
