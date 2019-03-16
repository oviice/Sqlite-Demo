package com.ovi.sqlitedemo;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,name,phnNum;
    Button add,view,delete;
    DatabaseSqlite myDb;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateAll();
        myDb=new DatabaseSqlite(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewData();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.delete();
            }
        });
    }




    public void ViewData() {
        textView.setText("");
        Cursor res=myDb.getAllData();
        if(res.getCount() == 0) {
            // show message
            textView.setText("No Data Found");
            return;
        }

        while (res.moveToNext()){
            textView.append("ID:"+res.getString(0)+"\n");
            textView.append("Name:"+res.getString(1)+"\n");
            textView.append("Phone Number:"+res.getString(2)+"\n\n");


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void AddData() {
        boolean isInsert=(myDb.insertData(id.getText().toString(),name.getText().toString(),phnNum.getText().toString()));
        if (isInsert=true){
            Toast.makeText(MainActivity.this,"Data insert",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this,"Data not Insert",Toast.LENGTH_SHORT).show();
        }
    }

    public void initiateAll(){
        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        phnNum=findViewById(R.id.PhoneNumber);
        add=findViewById(R.id.addButton);
        view=findViewById(R.id.View);
        textView=findViewById(R.id.textView);
        delete=findViewById(R.id.delete);
    }

}
