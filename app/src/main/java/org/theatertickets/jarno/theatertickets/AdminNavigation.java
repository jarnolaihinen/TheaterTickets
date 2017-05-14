package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AdminNavigation extends Activity {

    public Button addMoviesButton;

    public TextView deleteMovie;
    public TextView deletePerson;

    public ListView movieList;
    public ListView peopleList;
    public ArrayAdapter<String> adapter;
    public ArrayAdapter<String> adapter2;
    public List<String> listItems;
    public List<String> listItems2;

    public int positionToRemove;
    public int positionToRemove2;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_navigation);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addMoviesButton = (Button) findViewById(R.id.addMoviesButton);

        addMoviesButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent siirto = new Intent(AdminNavigation.this, Admin_Add_Movie_Activity.class);
                AdminNavigation.this.startActivityForResult(siirto, 1);
            }
        });

        deleteMovie = (TextView) findViewById(R.id.deleteMovie);
        deletePerson = (TextView) findViewById(R.id.deletePerson);

        listItems2 = new ArrayList<String>();//tähän saatu lista, ihmiset
        listItems2 = loginDataBaseAdapter.allUsers();

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems2);

        peopleList = (ListView) findViewById(R.id.peopleList);
        adaptoi2();

        peopleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, final int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(AdminNavigation.this);
                positionToRemove = position;
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + listItems2.get(positionToRemove) + "?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String[] temp = listItems2.get(positionToRemove).split(";");
                        loginDataBaseAdapter.removeUser(temp[1]);
                        listItems2.remove(positionToRemove);
                        adaptoi2();
                    }
                });
                adb.show();
            }
        });


        listItems = new ArrayList<String>();//tähän saatu lista, leffat
        listItems=loginDataBaseAdapter.allMovies();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);

        movieList = (ListView) findViewById(R.id.movieList);

        adaptoi();

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(AdminNavigation.this);
                positionToRemove2 = position;
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " +listItems.get(positionToRemove2) +"?");
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        String[] temp = listItems.get(positionToRemove2).split(";");
                        Log.v(temp[2], "Movie deleted");
                        loginDataBaseAdapter.removeMovie(temp[2]);
                        listItems.remove(positionToRemove2);

                        adaptoi();
                    }});
                adb.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_navigation, menu);
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

    public void adaptoi2(){
        peopleList.setAdapter(adapter2);
    }

    public void adaptoi(){
        movieList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            listItems=loginDataBaseAdapter.allMovies();
            listItems2 = loginDataBaseAdapter.allUsers();
            adaptoi();
            adaptoi2();
        }
    }
}