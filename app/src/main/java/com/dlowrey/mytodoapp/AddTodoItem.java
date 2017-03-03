package com.dlowrey.mytodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTodoItem extends AppCompatActivity {

    // private variable for our EditText
    private EditText mEditText;
    // private variable for our MockDatabase object
    private MockDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo_item);

        // Reference the EditText
        mEditText = (EditText)findViewById(R.id.new_todo);

        // get a MockDatabase object instance
        db = MockDatabase.getMockDatabaseInstance();


    }

    /**
     * This method is called when our save button is clicked
     * if you look in our activit_add_todo_item layout file,
     * you will see that the button we created has this method in its
     * attributes
     * @param view the view object we are dealing with (in this case our button)
     */
    public void onSaveClick(View view) {
        // Get the text the user has typed in the EditText
        String newTodo = mEditText.getText().toString();
        // Insert it into our database
        db.insert(newTodo);
        // Set the resultCode of this activity to 1
        // we could set this to 0 if we found the EditText was empty,
        // and then do something different in MainActivity.onActivityResult()
        setResult(1);
        // finish this activity
        finish();

    }
}
