package com.fowler.brandon.bafowlerlab1;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class BAFowlerLab1 extends TracerActivity implements View.OnClickListener{

    Button Survey;
    Button Website;
    EditText NameEntry;
    TextView Results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bafowler_lab1);
        Survey = (Button) findViewById(R.id.SurveyButton);
        Survey.setOnClickListener(this);
        Website = (Button) findViewById(R.id.WebsiteButton);
        Website.setOnClickListener(this);
        Results = (TextView)findViewById(R.id.Results);
        Intent intent = getIntent();
        if(intent != null){
            handleIntent(intent);
        }
    }

    public void handleIntent(Intent intent){
        if(intent.getAction() == Intent.ACTION_SEND  && intent.getType().startsWith("text/")){
            Results.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }

    @Override
    public void onClick(View view){
        if(view == Survey) {
            NameEntry = (EditText) findViewById(R.id.nameEntry);
            String StringName = NameEntry.getText().toString();
            if(StringName.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please Enter Your Name!",Toast.LENGTH_SHORT).show() ;
            }
            else{
                Intent SurveyActivity = new Intent(this, SurveyActivity.class);
                SurveyActivity.putExtra("Name", StringName);
                startActivityForResult(SurveyActivity, 1);
            }
        }
        if(view == Website){
            Intent OpenWebSite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/site/pschimpf99/"));
            startActivity(OpenWebSite);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bafowler_lab1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 1, Brandon A. Fowler",Toast.LENGTH_SHORT).show() ;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        int age = -1;
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK && data != null){
                age = data.getIntExtra("age", -1);
                if(age >= 0 && age < 40){
                    Results.setText("You’re under 40, so you’re trustworthy");
                }
                else if(age >= 40){
                    Results.setText("You’re NOT under 40, so you’re NOT trustworthy");
                }
                else{
                    Results.setText("Something went wrong! Please re-take the survey and enter a valid age!");
                }
            }
            else{
                Results.setText("Something went wrong! Please re-take the survey and enter a valid age!");
            }
        }
    }
}
