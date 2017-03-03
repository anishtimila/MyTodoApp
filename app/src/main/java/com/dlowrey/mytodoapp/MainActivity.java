package com.dlowrey.mytodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.dlowrey.mytodoapp.R.styleable.FloatingActionButton;
import static com.dlowrey.mytodoapp.R.styleable.Toolbar;

public class MainActivity extends AppCompatActivity {

    // private access variable for our ListView
    private ListView mListView;
    // private access variable for our adapter
    private ListAdapter mAdapter;
    // private access variable for our database object
    private MockDatabase db;
    // private access variable for our array list of strings
    private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call the parent class's onCreate() method
        // This code is generated when we @Override the onCreate()
        // method in AppCompatActivity
        super.onCreate(savedInstanceState);
        // Connect to the XML layout
        setContentView(R.layout.activity_main);


        // Reference the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Reference the Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        // Set the onClickListener for the floating action button
        // this method will get called when the button is clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to start our AddTodoItemClass
                Intent addTodoIntent = new Intent(getApplicationContext(), AddTodoItem.class);
                // Start the activity for a result, set the requestCode to 1
                startActivityForResult(addTodoIntent, 1);
            }
        });


        // get an instance of the MockDatabase object
        // this will return either a new instance of the MockDatabase if
        // one has not been created or a previously created one
        // (See MockDatabase.getMockDatabaseInstance())
        db = MockDatabase.getMockDatabaseInstance();

        // Reference our ListView
        mListView = (ListView)findViewById(R.id.todo_list);

        // Get any current data in our database
        mArrayList = db.getTodos();

        // Instantiate a new ArrayAdapter
        // This is the middle man between our data from our database and our ListView
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrayList);
        // Set our ListView's adapter to the one we just made
        mListView.setAdapter(mAdapter);



    }


    /**
     * This function gets called when an activity started with
     * startActivityForResult() finishes.
     * In our case, we start AddTodoItem.class for a result
     * @param requestCode the code used when starting the activity
     * @param resultCode the result code passed back from the activity
     * @param data any data passed back from the activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Sometimes you will have multiple startActivityForResult() calls,
        // you need to filter them by requestCode (in our case we only had one, with the
        // requestCode == 1)
        switch (requestCode){
            case 1:
                // Our user has input a new thing to the database, refresh the listview
                mArrayList = db.getTodos();
                mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mArrayList);
                mListView.setAdapter(mAdapter);
                break;
            default:
                break;
        }

    }

    /**
     * this just creates the options menu you see if you click the
     * three dots in the app's toolbar
     * @param menu a menu object to inflate
     * @return true or false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * this is like the onClickListender for a menu item
     * it gets called and decides what to do when a menu item is
     * selected in the app's toolbar menu
     * @param item which item object was selected
     * @return true or false
     */
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
}
