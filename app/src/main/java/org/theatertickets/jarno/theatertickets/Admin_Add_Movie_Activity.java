package org.theatertickets.jarno.theatertickets;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLException;


public class Admin_Add_Movie_Activity extends Activity {

    public TextView times;
    public TextView halls;
    public TextView turkuTime;
    public TextView turkuHall;
    public TextView tampereTime;
    public TextView helsinkiTime;
    public TextView hallHelsinki;
    public TextView hallTampere;
    public TextView dates;
    public TextView turkuDates;
    public TextView helsinkiDates;
    public TextView tampereDates;

    public Button addImageButton;
    public Button addMovie;

    public CheckBox time18;
    public CheckBox time20;
    public CheckBox time22;
    public CheckBox tampereTime18;
    public CheckBox tampereTime20;
    public CheckBox tampereTime22;
    public CheckBox helsinkiTime18;
    public CheckBox helsinkiTime20;
    public CheckBox helsinkiTime22;
    public CheckBox turkuHall1;
    public CheckBox turkuHall2;
    public CheckBox helsinkiHall1;
    public CheckBox helsinkiHall2;
    public CheckBox tampereHall1;
    public CheckBox tampereHall2;

    public EditText name;
    public EditText turkuPre;
    public EditText turkuLast;
    public EditText helsinkiPre;
    public EditText helsinkiLast;
    public EditText tamperePre;
    public EditText tampereLast;

    public ImageView movieImage;

    public int turkuTimesClicks = 0;
    public int helsinkiTimesClicks = 0;
    public int tampereTimesClicks = 0;

    public int turkuHallsClicks = 0;
    public int helsinkiHallsClicks = 0;
    public int tampereHallsClicks = 0;

    public String selectedImagePath;
    public String turkuSali;
    public String helsinkiSali;
    public String tampereSali;
    public String turkuAika;
    public String helsinkiAika;
    public String tampereAika;

    public String movieName;
    public String turkuEnsi;
    public String turkuViim;
    public String helsinkiEnsi;
    public String helsinkiViim;
    public String tampereEnsi;
    public String tampereViim;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__add__movie_);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        try {
            loginDataBaseAdapter = loginDataBaseAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        movieImage = (ImageView) findViewById(R.id.movieImage);

        times = (TextView) findViewById(R.id.times);
        halls = (TextView) findViewById(R.id.halls);
        turkuTime = (TextView) findViewById(R.id.turkuTime);
        turkuHall = (TextView) findViewById(R.id.turkuHall);
        tampereTime = (TextView) findViewById(R.id.tampereTime);
        helsinkiTime = (TextView) findViewById(R.id.helsinkiTime);
        hallHelsinki = (TextView) findViewById(R.id.hallHelsinki);
        hallTampere = (TextView) findViewById(R.id.hallTampere);
        dates = (TextView) findViewById(R.id.dates);
        turkuDates = (TextView) findViewById(R.id.turkuDates);
        helsinkiDates = (TextView) findViewById(R.id.helsinkiDates);
        tampereDates = (TextView) findViewById(R.id.tampereDates);

        addImageButton = (Button) findViewById(R.id.addImageButton);
        addImageButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 1);
            }
        });

        addMovie = (Button) findViewById(R.id.addMovie);
        addMovie.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Log.v(movieName + turkuSali + turkuEnsi + turkuAika + selectedImagePath, "Tietokantaan laitettavan leffan tiedot");
                if((movieName != null && turkuSali != null || helsinkiSali != null || tampereSali != null) &&
                        (turkuAika != null || helsinkiAika != null || tampereAika != null) &&
                        (turkuEnsi != null || helsinkiEnsi != null || tampereEnsi != null)) {
                    addMovies();
                    finish();
                }
            }
        });

        time18 = (CheckBox) findViewById(R.id.time18);
        time18.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(time18.isChecked()){
                    turkuTimesClicks++;
                    if(turkuTimesClicks > 1){
                        time18.toggle();
                        turkuTimesClicks--;
                    }
                    if(turkuTimesClicks == 1){
                        turkuAika = "18:00";
                    }
                }else{
                    turkuTimesClicks--;
                    turkuAika = null;
                }
            }
        });

        time20 = (CheckBox) findViewById(R.id.time20);
        time20.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(time20.isChecked()){
                    turkuTimesClicks++;
                    if(turkuTimesClicks > 1){
                        time20.toggle();
                        turkuTimesClicks--;
                    }
                    if(turkuTimesClicks == 1){
                        turkuAika = "20:00";
                    }
                }else{
                    turkuTimesClicks--;
                    turkuAika = null;
                }
            }
        });
        time22 = (CheckBox) findViewById(R.id.time22);
        time22.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(time22.isChecked()){
                    turkuTimesClicks++;
                    if(turkuTimesClicks > 1){
                        time22.toggle();
                        turkuTimesClicks--;
                    }
                    if(turkuTimesClicks == 1){
                        turkuAika = "18:00";
                    }
                }else{
                    turkuTimesClicks--;
                    turkuAika = null;
                }
            }
        });

        tampereTime18 = (CheckBox) findViewById(R.id.tampereTime18);
        tampereTime18.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(tampereTime18.isChecked()){
                    tampereTimesClicks++;
                    if(tampereTimesClicks > 1){
                        tampereTime18.toggle();
                        tampereTimesClicks--;
                    }
                    if(tampereTimesClicks == 1){
                        tampereAika = "18:00";
                    }
                }else{
                    tampereTimesClicks--;
                    tampereAika = null;
                }
            }
        });

        tampereTime20 = (CheckBox) findViewById(R.id.tampereTime20);
        tampereTime20.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(tampereTime20.isChecked()){
                    tampereTimesClicks++;
                    if(tampereTimesClicks > 1){
                        tampereTime20.toggle();
                        tampereTimesClicks--;
                    }
                    if(tampereTimesClicks == 1){
                        tampereAika = "20:00";
                    }
                }else{
                    tampereTimesClicks--;
                    tampereAika = null;
                }
            }
        });

        tampereTime22 = (CheckBox) findViewById(R.id.tampereTime22);
        tampereTime22.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(tampereTime22.isChecked()){
                    tampereTimesClicks++;
                    if(tampereTimesClicks > 1){
                        tampereTime22.toggle();
                        tampereTimesClicks--;
                    }
                    if(tampereTimesClicks == 1){
                        tampereAika = "22:00";
                    }
                }else{
                    tampereTimesClicks--;
                    tampereAika = null;
                }
            }
        });

        helsinkiTime18 = (CheckBox) findViewById(R.id.helsinkiTime18);
        helsinkiTime18.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(helsinkiTime18.isChecked()){
                    helsinkiTimesClicks++;
                    if(helsinkiTimesClicks > 1){
                        helsinkiTime18.toggle();
                        helsinkiTimesClicks--;
                    }
                    if(helsinkiTimesClicks == 1){
                        helsinkiAika = "18:00";
                    }
                }else{
                    helsinkiTimesClicks--;
                    helsinkiAika = null;
                }
            }
        });

        helsinkiTime20 = (CheckBox) findViewById(R.id.helsinkiTime20);
        helsinkiTime20.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(helsinkiTime20.isChecked()){
                    helsinkiTimesClicks++;
                    if(helsinkiTimesClicks > 1){
                        helsinkiTime20.toggle();
                        helsinkiTimesClicks--;
                    }
                    if(helsinkiTimesClicks == 1){
                        helsinkiAika = "20:00";
                    }
                }else{
                    helsinkiTimesClicks--;
                    helsinkiAika = null;
                }
            }
        });

        helsinkiTime22 = (CheckBox) findViewById(R.id.helsinkiTime22);
        helsinkiTime22.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(helsinkiTime22.isChecked()){
                    helsinkiTimesClicks++;
                    if(helsinkiTimesClicks > 1){
                        helsinkiTime22.toggle();
                        helsinkiTimesClicks--;
                    }
                    if(helsinkiTimesClicks == 1){
                        helsinkiAika = "22:00";
                    }
                }else{
                    helsinkiTimesClicks--;
                    helsinkiAika = null;
                }
            }
        });

        turkuHall1 = (CheckBox) findViewById(R.id.turkuHall1);
        turkuHall1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(turkuHall1.isChecked()){
                    turkuHallsClicks++;
                    if(turkuHallsClicks > 1){
                        turkuHall1.toggle();
                        turkuHallsClicks--;
                    }
                    if(turkuHallsClicks == 1) {
                        turkuSali = "Kakkossali";
                    }
                }else{
                    turkuHallsClicks--;
                    turkuSali = null;
                }
            }
        });

        turkuHall2 = (CheckBox) findViewById(R.id.turkuHall2);
        turkuHall2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(turkuHall2.isChecked()) {
                    turkuHallsClicks++;
                    if (turkuHallsClicks > 1) {
                        turkuHall2.toggle();
                        turkuHallsClicks--;
                    }
                    if (turkuHallsClicks == 1){
                        turkuSali = "Kolmossali";
                    }
                }else{
                    turkuHallsClicks--;
                    turkuSali = null;
                }
            }
        });

        helsinkiHall1 = (CheckBox) findViewById(R.id.helsinkiHall1);
        helsinkiHall1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(helsinkiHall1.isChecked()){
                    helsinkiHallsClicks++;
                    if(helsinkiHallsClicks > 1){
                        helsinkiHall1.toggle();
                        helsinkiHallsClicks--;
                    }
                    if(helsinkiHallsClicks == 1) {
                        helsinkiSali = "Kakkossali";
                    }
                }else{
                    helsinkiHallsClicks--;
                    helsinkiSali = null;
                }
            }
        });

        helsinkiHall2 = (CheckBox) findViewById(R.id.helsinkiHall2);
        helsinkiHall2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (helsinkiHall2.isChecked()) {
                    helsinkiHallsClicks++;
                    if (helsinkiHallsClicks > 1) {
                        helsinkiHall2.toggle();
                        helsinkiHallsClicks--;
                    }
                    if(helsinkiHallsClicks == 1) {
                        helsinkiSali = "Kolmossali";
                    }
                } else {
                    helsinkiHallsClicks--;
                    helsinkiSali = null;
                }
            }
        });

        tampereHall1 = (CheckBox) findViewById(R.id.tampereHall1);
        tampereHall1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (tampereHall1.isChecked()) {
                    tampereHallsClicks++;
                    if (tampereHallsClicks > 1) {
                        tampereHall1.toggle();
                        tampereHallsClicks--;
                    }
                    if(tampereHallsClicks == 1){
                        tampereSali = "Kakkossali";
                    }

                } else {
                    tampereHallsClicks--;
                    tampereSali = null;
                }
            }
        });

        tampereHall2 = (CheckBox) findViewById(R.id.tampereHall2);
        tampereHall2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (tampereHall2.isChecked()) {
                    tampereHallsClicks++;
                    if (tampereHallsClicks > 1) {
                        tampereHall2.toggle();
                        tampereHallsClicks--;
                    }
                    if (tampereHallsClicks == 1) {
                        tampereSali = "Kolmossali";
                    }
                } else {
                    tampereHallsClicks--;
                    tampereSali = null;
                }
            }
        });

        name = (EditText) findViewById(R.id.name);
        name.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                movieName = name.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        turkuPre = (EditText) findViewById(R.id.turkuPre);
        turkuPre.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                turkuEnsi = turkuPre.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
        turkuLast = (EditText) findViewById(R.id.turkuLast);
        turkuLast.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                turkuViim = turkuLast.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        helsinkiPre = (EditText) findViewById(R.id.helsinkiPre);
        helsinkiPre.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                helsinkiEnsi = helsinkiPre.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        helsinkiLast = (EditText) findViewById(R.id.helsinkiLast);
        helsinkiLast.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                helsinkiViim = helsinkiLast.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        tamperePre = (EditText) findViewById(R.id.tamperePre);
        tamperePre.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                tampereEnsi = tamperePre.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        tampereLast = (EditText) findViewById(R.id.tampereLast);
        tampereLast.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                tampereViim = tampereLast.getText().toString();
            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


    }

    public void addMovies(){
        if(turkuSali != null && turkuAika != null && turkuEnsi != null) {
            Log.v(movieName + turkuSali + turkuEnsi + turkuAika + selectedImagePath, "Tietokantaan laitettavan leffan tiedot");
            loginDataBaseAdapter.insertMovie(movieName, "Turku", turkuSali, turkuEnsi, turkuAika, "0;0", selectedImagePath);
        }
        if(helsinkiSali != null && helsinkiAika != null && helsinkiEnsi != null) {
            loginDataBaseAdapter.insertMovie(movieName, "Helsinki", helsinkiSali, helsinkiEnsi, helsinkiAika, "0;0", selectedImagePath);
        }
        if(tampereSali != null && tampereAika != null && tampereEnsi != null) {
            loginDataBaseAdapter.insertMovie(movieName, "Tampere", tampereSali, tampereEnsi, tampereAika, "0;0", selectedImagePath);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            selectedImagePath = uri.toString();
            movieImage.setImageURI(uri);
        }
    }

}
