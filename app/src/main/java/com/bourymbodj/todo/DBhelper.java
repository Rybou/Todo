package com.bourymbodj.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bourymbodj on 16-07-22.
 */
public class DBhelper extends SQLiteOpenHelper {

    //All static variables
    //Database Version
    private static final int DATABASE_VERSION=1;

    // Database name
    private static final String DATABASE_NAME= "employeedb";

    // Todo list table name
    private static final String TABLE_TODO = "todo";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "date";
    public static final String KEY_STATUS = "status";



    public DBhelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);

    }

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    // Creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TODO_TABLE = "CREATE TABLE " + TABLE_TODO + "(" + KEY_ID
                + "INTEGER PRIMARY KEY," + KEY_TITLE + "TEXT," + KEY_DESCRIPTION + "TEXT,"
                + KEY_DATE+ "TEXT,"
                + KEY_STATUS + "INTEGER" + ")";
        db.execSQL(CREATE_TODO_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_TODO);

        // Create table again
        onCreate(db);

    }

    // All CRUD (Create, read , update, delete) operations

    public void addToDo(ToDo todo){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle()); // Todo title
        values.put(KEY_DESCRIPTION, todo.getDescription()); // Todo description
        values.put(KEY_DATE, todo.getDate()); // Todo date
        values.put(KEY_STATUS, todo.getStatus());// Todo Satus


// Inserting Row
        db.insert(TABLE_TODO, null, values);
        db.close(); // Closing database connection
    }
    // Getting single todo
    ToDo getToDo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TODO, new String[] { KEY_ID,
                        KEY_TITLE,KEY_DESCRIPTION,  KEY_DATE, KEY_STATUS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ToDo todo = new ToDo(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),
                Integer.parseInt(cursor.getString(4)));

// return todo
        return todo;

    }

    // Getting All todo's
    public List<ToDo> getAllToDos() {
        List<ToDo> todoList = new ArrayList<ToDo>();
// Select All Query
        String selectQuery = "SELECT * FROM todo ORDER BY title";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDo todo = new ToDo();
                todo.setId(Integer.parseInt(cursor.getString(0)));
                todo.setTitle(cursor.getString(1));
                todo.setDescription(cursor.getString(2));
                todo.setDate(cursor.getString(3));
                todo.setStatus(Integer.parseInt(cursor.getString(4)));
// Adding all things to do  to list
                todoList.add(todo);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return to do list
        return todoList;

    }

    // Updating single todo
    public int updateTodo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, todo.getTitle());
        values.put(KEY_DESCRIPTION, todo.getDescription());
        values.put(KEY_DATE, todo.getDate());
        values.put(KEY_STATUS, todo.getStatus());

// updating row
        return db.update(TABLE_TODO, values,KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });

    }

    // Deleting single todo task
    public void deleteToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TODO, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });
        db.close();
    }

    // Getting number of todo
    public int getToDoCount() {
        String countQuery = "SELECT * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

}

