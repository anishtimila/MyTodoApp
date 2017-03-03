package com.dlowrey.mytodoapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Dane Lowrey on 3/1/2017.
 */

public class MockDatabase {


    /**
     * THIS IS NOT HOW A REAL DATABASE WOULD LOOK
     * this is only a mock database for now
     */

    // our 'database' object, an arraylist of strings
    private ArrayList<String> myDatabase = new ArrayList<>();

    // private instance of type MockDatabase,
    // this is used to create a singleton pattern
    // singleton makes it so we can only have ONE MockDatabase object
    // instantiated throughout our whole app
    private static MockDatabase instance;

    // default MockDatabase Constructor,
    // private because it should only ever be called by MockDatabase.getMockDatabaseInstance()
    private MockDatabase(){

    }

    /**
     * this method handles the singleton pattern logic
     * it only allows one instance of MockDatabase to be created,
     * and then passes that object to whatever calls this method
     *
     * This is how we should get MockDatabase Objects in our code
     * @return a MockDatabase
     */
    public static MockDatabase getMockDatabaseInstance(){

        // If a MockDatabase object has not been created
        if(instance == null){
            // Create one and store it in instance
            instance = new MockDatabase();
        }

        // return the MockDatabase object stored in instance
        return instance;

    }


    /**
     * insert a string into our ArrayList of strings
     * @param todo
     */
    public void insert(String todo){
        myDatabase.add(todo);
    }


    /**
     * get all strings in our database
     * @return
     */
    public ArrayList<String> getTodos(){
        return myDatabase;
    }



}
