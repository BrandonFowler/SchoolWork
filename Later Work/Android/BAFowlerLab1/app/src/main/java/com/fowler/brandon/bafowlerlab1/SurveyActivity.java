package com.fowler.brandon.bafowlerlab1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends AppCompatActivity implements View.OnClickListener{

    String Name = "";
    TextView Greeting;
    EditText Age;
    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        Intent callingIntent = getIntent();
        if(callingIntent != null) {
            Name = callingIntent.getStringExtra("Name");
        }
        Greeting = (TextView)findViewById(R.id.GreetingText);
        Greeting.setText(Greeting.getText().toString()+ " " + Name);
        Age = (EditText)findViewById(R.id.AgeEntry);
        Submit = (Button) findViewById(R.id.SubmitButton);
        Submit.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_survey, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 1, Brandon A. Fowler", Toast.LENGTH_SHORT).show() ;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        if(view == Submit) {
           if(Age.getText().toString().isEmpty()){
               Toast.makeText(getApplicationContext(), "Please Enter Your Age!",Toast.LENGTH_SHORT).show() ;
           }
            else{
               int ageInput = Integer.parseInt(Age.getText().toString());
               Intent Result = new Intent();
               Result.putExtra("age", ageInput);
               setResult(Activity.RESULT_OK, Result);
               finish();
           }
        }
    }
}
