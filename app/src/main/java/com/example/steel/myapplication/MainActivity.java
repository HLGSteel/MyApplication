package com.example.steel.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static String DATABASE_NAME = "chapter3";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "table1";
    public static String TITLE = "title";
    public static String BODY = "body";
    DatabaseHelper mOpenHelper;

    Button mCreateTable, mSelectTable, mInsertItem, mDeleteItem, mDeleteTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenHelper = new DatabaseHelper(this);

        mCreateTable = (Button)findViewById(R.id.bCreateTable);
        mCreateTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createTable();
            }
        });

        mSelectTable = (Button)findViewById(R.id.bSelectTable);
        mSelectTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showItems();
            }
        });

        mInsertItem = (Button)findViewById(R.id.bInsertItem);
        mInsertItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                insertItem();
            }
        });

        mDeleteItem = (Button)findViewById(R.id.bDeleteItem);
        mDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });

        mDeleteTable = (Button)findViewById(R.id.bDeleteTable);
        mDeleteTable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dropTable();
            }
        });
    }

    private void createTable(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "create table "+TABLE_NAME+"("+TITLE+" text not null, "+BODY+" text not null"+");";
        Log.i("chapter3:createDB=", sql);
        try{
            db.execSQL("drop table if exists "+TABLE_NAME);
            db.execSQL(sql);
            setTitle("创建数据表成功");
        }catch (SQLException e){
            setTitle("创建数据表失败");
        }
    }

    private void showItems(){
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        String col[] = {TITLE, BODY};
        Cursor cur = db.query(TABLE_NAME, col, null,null,null,null,null);
        Integer num = cur.getCount();
        setTitle(Integer.toString(num)+"条记录");
    }

    private void insertItem(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql1 = "insert into "+TABLE_NAME+"("+TITLE+","+BODY+") values('AA', 'good morning!');";
        String sql2 = "insert into "+TABLE_NAME+"("+TITLE+","+BODY+") values('BB', 'good morning!');";
        try {
            Log.i("chapter3:sql1=", sql1);
            Log.i("chapter3:sql2=", sql2);
            db.execSQL(sql1);
            db.execSQL(sql2);
            setTitle("insert succeed!");
        }catch (SQLException e){
            setTitle("insert failed!");
        }
    }

    private void deleteItem(){
        try{
            SQLiteDatabase db = mOpenHelper.getWritableDatabase();
            db.delete(TABLE_NAME, "title='AA'", null);
            setTitle("删除title='AA'的记录");
        }catch (SQLException e){

        }
    }

    private void dropTable(){
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        String sql = "drop table "+TABLE_NAME;
        try{
            db.execSQL(sql);
            setTitle("删除成功: "+sql);
        }catch (SQLException e){
            setTitle("删除失败");
        }
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE "+TABLE_NAME+"("+TITLE+" text not null, "+BODY+" text not null"+")";
            Log.i("chapter3:createDB=", sql);
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
