package com.happykrafts.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.happykrafts.sqlitedemo.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    private EditText mName,mSurname,mMarks,mId;
    private Button mSave, mView,mUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DatabaseHelper(this);


        mName = findViewById(R.id.etName);
        mSurname = findViewById(R.id.etSurname);
        mMarks = findViewById(R.id.etMarks);
        mSave = findViewById(R.id.btnSave);
        mView = findViewById(R.id.viewData);
        mId = findViewById(R.id.etId);
        mUpdate = findViewById(R.id.btnUpdate);


        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isInserted=  myDb.insertData(mName.getText().toString(),
                        mSurname.getText().toString(),
                        mMarks.getText().toString());

              if(isInserted= true){
                  Toast.makeText(getApplicationContext(),"Inserted Sucessfully",Toast.LENGTH_SHORT).show();
              }
              else {
                  Toast.makeText(getApplicationContext(),"Insertition Failed",Toast.LENGTH_SHORT).show();
              }

            }
        });


        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showData();
            }
        });

    }



    //To view the Data from the db.

    public void showData() {
        Cursor res = myDb.getAllData();
        if(res.getCount()==0){

            Dialog("Error","No Data Found");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id : "+res.getString(0)+"\n");
            buffer.append("Name : "+res.getString(1)+"\n");
            buffer.append("Surname : "+res.getString(2)+"\n");
            buffer.append("Marks : "+res.getString(3)+"\n");
        }
        Dialog("Data",buffer.toString());
    }

    public void Dialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private void update() {

        boolean isUpdate = myDb.updateData(mId.getText().toString(),
                mName.getText().toString(),mSurname.getText().toString(),
                mMarks.getText().toString());

        if(isUpdate ==true)
            Toast.makeText(getApplicationContext(),"Updated Sucessfully",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Not Updated",Toast.LENGTH_SHORT).show();
    }



}
