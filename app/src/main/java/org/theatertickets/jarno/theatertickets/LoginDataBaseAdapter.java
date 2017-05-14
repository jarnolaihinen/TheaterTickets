package org.theatertickets.jarno.theatertickets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "theater.db";
    public static final int NAME_COLUMN = 1;
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_CREATE_USERS = "create table " + "USERS" +
            "( " + "ID " + "integer primary key autoincrement," + "USERNAME text, PASSWORD text, EMAIL text, CITY text); ";
    static final String DATABASE_CREATE_MOVIES = "create table " + "MOVIES" +
            "(" + "sID " + "integer primary key autoincrement," + "NAME text, THEATER text, HALL text, DAY text, TIME text, RESERVED text, URI text); ";
    static final String DATABASE_CREATE_RESERVATION = "create table " + "RESERVATIONS" +
            "(" + "ssID " + "integer primary key autoincrement," + "mNAME text, mTHEATER text, mHALL text, " +
            "mAIKA text, mSEATS text, uID integer, mID integer); ";


    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public LoginDataBaseAdapter(Context _context){
        context=_context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public LoginDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        db.close();
    }
    public SQLiteDatabase getDataBaseInstance(){
        return db;
    }

    public String getUserPassword(String username){              //GET PASSWORD FOR USER
        Cursor cursor = db.query("USERS", null, " USERNAME=?",new String[]{username}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getUserIDwithUserName(String name){
        Cursor cursor = db.query("USERS", null, "uID=?", new String[]{name}, null,null,null);
        if(cursor.getCount()<1){
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String ID = cursor.getString(cursor.getColumnIndex("uID"));
        cursor.close();
        return ID;
    }

    public void updateUser(String username, String password, String email){        //UPDATE USER INFO
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", username);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("EMAIL", email);

        String where = "USERNAME = ?";
        db.update("USER", updatedValues, where, new String[]{username});
    }

    public void updateMoviesReservation(String movieID, String reserved){

        Log.v("updateMoviesReservation", reserved);
        ContentValues contentValues = new ContentValues();
        if(!reserved.equals("")) {
            contentValues.put("RESERVED", reserved);
            Log.v("update huudettu", movieID);
        }else{
            contentValues.put("RESERVED", "0;0");

        }
        String where = "sID=?";
        db.update("MOVIES", contentValues, where, new String[]{movieID});
        Log.v("update huudettu", movieID);
    }

    public void insertMovie(String name, String theater, String sali, String day, String hours, String reserved, String uri){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("THEATER", theater);
        contentValues.put("HALL", sali);
        contentValues.put("DAY", day);
        contentValues.put("TIME", hours);
        contentValues.put("RESERVED", reserved);
        contentValues.put("URI", uri);

        db.insert("MOVIES", null, contentValues);
    }

    public void insertUser(String username, String password, String email, String city){
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD", password);
        newValues.put("EMAIL", email);
        newValues.put("CITY",city);

        db.insert("USERS", null, newValues);

    }
    public void updateCity(String name, String city){
        ContentValues contentValues = new ContentValues();

        contentValues.put("CITY", city);
        String where = "USERNAME = ?";
        db.update("USERS", contentValues, where, new String[]{name});
    }


    public String getCity(String name){
        Cursor cursor = db.query("USERS", null, "USERNAME=?", new String[]{name}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "did not found";
        }
        cursor.moveToFirst();
        String nimi = cursor.getString(4);
        cursor.close();
        return nimi;
    }
    public String getEmail(String name){
        Cursor cursor = db.query("USERS", null, "USERNAME=?", new String[]{name}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "did not found";
        }
        cursor.moveToFirst();
        String nimi = cursor.getString(3);
        cursor.close();
        return nimi;
    }
    public String getMovieUriwithName(String movieName){
        Cursor cursor = db.query("MOVIES", null, "NAME=?", new String[]{movieName}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "did not found";
        }
        cursor.moveToFirst();
        String uri = cursor.getString(7);
        cursor.close();
        return uri;

    }

    public void insertReservation(String name, String theater, String sali, String aika, Integer user_id, Integer id, String varatut){
        ContentValues newValues = new ContentValues();
        newValues.put("mNAME",name);
        newValues.put("mTHEATER",theater);
        newValues.put("mHALL",sali);
        newValues.put("mAIKA",aika);
        newValues.put("mSEATS",varatut); //nä on siis vain userin varaamat
        newValues.put("uID",user_id);
        newValues.put("mID", id);

        db.insert("RESERVATIONS",null,newValues);

        updateMoviesReservation(Integer.toString(id),varatut);



    }

    public int getIDforUser(String username){
        Cursor cursor = db.query("USERS", null, " USERNAME=?",new String[]{username}, null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return 0;
        }
        cursor.moveToFirst();
        int userID = Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID")));
        cursor.close();
        return userID;
    }

    public boolean checkAvailability(String username){
        try {
            Cursor cursor = db.query("USERS", null, "USERNAME=?", new String[]{username}, null, null, null);
            if (cursor.getCount() < 1) {
                cursor.close();
                return true;
            }
            cursor.moveToFirst();
            if (cursor.getString(cursor.getColumnIndex("USERNAME")).equals(null)) {
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public List<String> availableMoviesWithThisDate(String teatteri, String elokuva, String date){
        List<String> movies = new ArrayList<String>();
        Log.v("ennen querya","");
        String selectQuery = "SELECT * " +  "FROM MOVIES " + "WHERE  NAME=? AND THEATER=? AND DAY=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{elokuva, teatteri, date},null);
        Log.v("queryn jälkeen", "");

        if (cursor.moveToFirst()) {
            do {
                movies.add(cursor.getString(3)+ " - " + cursor.getString(5) + ";" + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return movies;
    }

    public List<String> allMovies(){
        List<String> allmovies = new ArrayList<String>();
        String selectQuery = "SELECT * " + "FROM MOVIES" + " GROUP BY NAME";
        Cursor cursor = db.rawQuery(selectQuery, null, null);

        if (cursor.moveToFirst()) {
            do {
                allmovies.add(cursor.getString(1) + ";" + cursor.getString(7) + ";" + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return allmovies;
    }

    public List<String> allUsers(){
        List<String> allusers = new ArrayList<String>();
        String selectQuery = "SELECT * " + "FROM USERS" + " GROUP BY USERNAME";
        Cursor cursor = db.rawQuery(selectQuery, null, null);

        if (cursor.moveToFirst()) {
            do {
                allusers.add(cursor.getString(1) + " ID;" + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return allusers;

    }

    public List<String> checkSeats(String movieID){

        String parseTemp="";
        String selectQuery = "SELECT * " +  "FROM MOVIES " + "WHERE  sID=?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{movieID},null);

        if (cursor.moveToFirst()) {
            do {
                parseTemp = cursor.getString(6);
            } while (cursor.moveToNext());
        }
        cursor.close();
        List<String> seats = new ArrayList<String>();
        String[] res = parseTemp.split(";");
        for(int i=0;i<res.length; i++){
            seats.add(res[i]);
        }
        return seats;
    }


    public List<Integer> getReservationForMovie(String movieID){
        String[] templist;
        List<Integer> seats = new ArrayList<Integer>();
        String temp="";

        Cursor cursor = db.query("MOVIES", null, "sID=?", new String[]{movieID}, null,null,null);

        if (cursor.moveToFirst()) {
            do {
                temp=cursor.getString(6);
                Log.v("cursorista", cursor.getString(6));
            } while (cursor.moveToNext());
        }
        cursor.close();

            templist = temp.split(";");
            Log.v(temp, "templist");
            for(int i = 0; i < templist.length; i++) {
                if(templist[i]!="") {
                    seats.add(Integer.parseInt(templist[i]));
                }
            }
        return seats;
    }

    public String getReservationSeatsForUser(String resID){
        String returnable="";
        Cursor cursor = db.query("RESERVATIONS", null, "ssID=?", new String[]{resID}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                returnable = (cursor.getString(5) + "-" + cursor.getString(7));
                Log.v(cursor.getString(7), "getreservationseatsforuser");
            } while (cursor.moveToNext());
        }
        cursor.close();

        return returnable;

    }






    public void removeUser(String id){
        db.delete("USERS", "ID" + "="+ id, null);

    }

    public List<String> getUsersReservations(String ID) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.query("RESERVATIONS", null, " uID=?",new String[]{ID}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                list.add("Name: "+cursor.getString(1) + " Theater:" + cursor.getString(2) + "Hall: "+ cursor.getString(3) + "Time: "+ cursor.getString(4) + "ID%"+cursor.getString(0));
                Log.v("Name: "+cursor.getString(1) + " Theater:" + cursor.getString(2) + "Hall: "+ cursor.getString(3) + "Time: "+ cursor.getString(4), "reservaatio");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }//"ssID " + "integer primary key autoincrement," + "mNAME text, mTHEATER text, mHALL text, " + "mAIKA text, mSEATS text, uID integer, mID integer); ";

    public void removeMovie(String s) {
        db.delete("MOVIES", "sID" + "=" + s, null);
        db.delete("RESERVATIONS", "uID" + "=" + s, null);
    }
    public List<String> allMoviesByCity(String city){
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.query("MOVIES", null, "THEATER=?", new String[]{city}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1) + ";" + cursor.getString(7) + ";" + cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;

    }
}
