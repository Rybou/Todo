package com.bourymbodj.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<ToDo> imageArry= new ArrayList<ToDo>();
    ListView dataList;
    ToDoImageAdapter adapter;
    DBhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= new DBhelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.activity_promt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText titleInput = (EditText) promptsView
                        .findViewById(R.id.titleInput);
                final EditText descriptionInput = (EditText) promptsView
                        .findViewById(R.id.descriptionInput);
                final DatePicker datePicker = (DatePicker) findViewById(R.id.dateD);


                // set title
                alertDialogBuilder.setTitle("Add a task");

                // set dialog message
                alertDialogBuilder
                        //.setMessage("Complete the information")
                        .setCancelable(false)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                if (datePicker!= null) {
                                    int day = datePicker.getDayOfMonth();
                                    int month = datePicker.getMonth();
                                    int year = datePicker.getYear();

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    final String formatedDate = sdf.format(new Date(year, month, day));


                                    // if this button is clicked, close
                                    // current activity
                                    //MainActivity.this.finish();
                                    //ToDo myTask= new ToDo();
                                    Log.d("Insert: ", "Inserting ..");
                                    db.addToDo(new ToDo(titleInput.getText().toString(), descriptionInput.getText().toString(), formatedDate, 0));
                                    // myTask.execute();
                                    // button.setEnabled(false);
                                    // text.setText(userInput.getText());
                                    //textview.setText(userInput.getText());
                                    List<ToDo> todo = db.getAllToDos();
                                    for ( ToDo cn : todo){
                                        String log = "ID:" + cn.getId() + " Title: " + cn.getTitle()
                                                + " ,Description: " + cn.getDescription()
                                                + " ,Date: " + cn.getDate();

// Writing Contacts to log
                                        Log.d("Result: ", log);
//add contacts data in arrayList
                                        imageArry.add(cn);

                                    }
                                    adapter = new ToDoImageAdapter(MainActivity.this, R.layout.activity_list_item, imageArry);
                                    dataList = (ListView) findViewById(R.id.list);
                                    dataList.setAdapter(adapter);
                                    registerForContextMenu(dataList);
                                    addTask();

                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


                // textview.setText;
                Toast.makeText(getApplicationContext(), " Add is Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:

                Toast.makeText(getApplicationContext(), "Completed tasks is Selected", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

        public void addTask()
    {

    }
    }

