package org.theatertickets.jarno.theatertickets;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.OnClickListener;


public class Movies extends Activity implements ActionBar.OnNavigationListener {

    LoginDataBaseAdapter loginDataBaseAdapter;
    List<String> list;
    String user;
    String City;
    ActionBar actionBar;
    ArrayList<String> itemList;
    private MenuItem mSpinnerItem1 = null;
    ArrayAdapter<String> aAdpt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        user = getIntent().getStringExtra("NAME");
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        actionBar = getActionBar();

        City = loginDataBaseAdapter.getCity(user);

        list = new ArrayList<String>();



        updateSpinner();
        list= loginDataBaseAdapter.allMovies();

        //parse list kuntoon! nimi;uri
        updateMovieList();



    }

    public void updateSpinner(){


        itemList = new ArrayList<String>();
        City = loginDataBaseAdapter.getCity(user);
        if (City.equals("Turku")) {
            itemList.add("Turku");
            itemList.add("Tampere");
            itemList.add("Helsinki");
        }
        if (City.equals("Helsinki")) {
            itemList.add("Helsinki");
            itemList.add("Tampere");
            itemList.add("Turku");
        }
        if (City.equals("Tampere")) {
            itemList.add("Tampere");
            itemList.add("Turku");
            itemList.add("Helsinki");
        }
        aAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);


        setContentView(R.layout.activity_movies);
    }
    public void updateMovieList(){

        GridLayout movieGrid = (GridLayout) findViewById(R.id.grid_table);
        movieGrid.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            ImageButton temp = new ImageButton(this);
            final String[] tempString = list.get(i).split(";");
            temp.setId(i);
            temp.setMaxWidth(280);
            temp.setMaxHeight(200);
            temp.setBackgroundColor(Color.BLACK);
            temp.setImageURI(Uri.parse(tempString[1]));
            temp.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = view.getId();
                    String[] temp = list.get(id).split(";");

                    Intent i = new Intent(getApplicationContext(), Reservation.class);
                    i.putExtra("NAME", temp[0]);
                    i.putExtra("THEATER", City);
                    i.putExtra("USER", user);

                    startActivityForResult(i, 1);
                }
            });
            movieGrid.addView(temp);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.menu_movies, menu);
        mSpinnerItem1 = menu.findItem( R.id.menu_spinner1);
        View view1 = mSpinnerItem1.getActionView();
        if (view1 instanceof Spinner)
        {
            final Spinner spinner = (Spinner) view1;
            spinner.setAdapter(aAdpt);
            aAdpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    City = itemList.get(arg2);
                    list=loginDataBaseAdapter.allMoviesByCity(City);
                    updateMovieList();




                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

        }
        return true;
    }

    public void logOut() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.yourprofile:

                Intent i  = new Intent(getApplicationContext(),Customer_page.class);
                i.putExtra("NAME",user);
                startActivityForResult(i, 1);
                return true;
            case R.id.logoutButton:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        return false;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == 1) {
            updateSpinner(); // your "refresh" code
            updateMovieList();

        }
    }


}
