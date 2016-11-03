package hfad.com.project;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.content.Intent;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static hfad.com.project.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    MediaPlayer myMusic;
    Intent intent;
    private int AmountOfPoints = 0;

    public static final String MY_PREFS_NAME = "FileName";


    @Override
    protected void onPause(){
        super.onPause();
        myMusic.release();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
        TextView punten = (TextView)findViewById(R.id.ppc);

        pepe.setBackgroundColor(Color.RED);
        myMusic = MediaPlayer.create(this, R.raw.giveallup);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        AmountOfPoints = prefs.getInt("Points" , 0);
        punten.setText("Amount of points:  " + AmountOfPoints);
    }



    public void OnGeluid(View view){
        ImageButton aan = (ImageButton) findViewById(R.id.geluid);

        if(myMusic.isPlaying()){
            myMusic.pause();
            aan.setImageResource(R.drawable.geluid_uit);
        } else {
            myMusic.start();
            myMusic.setLooping(true);
            aan.setImageResource(R.drawable.geluid_aan);
        }

    }

    public void OnTap(View view){
        AmountOfPoints = AmountOfPoints + 1;
        ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
        pepe.setImageResource(R.drawable.pepe2);

        String AmountOfPoint = Integer.toString(AmountOfPoints);
        TextView punten = (TextView)findViewById(R.id.ppc);
        punten.setText("Amount of points:  " + AmountOfPoint);
        pepe.setBackgroundColor(Color.GREEN);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Home_Screen:
                intent = new Intent(this, MainActivity.class);
                finish();
                break;
            case R.id.Upgrade_Screen:
                intent = new Intent(this, UpgradeActivity.class);
                finish();
                break;
            case R.id.Refrences_List:
                intent = new Intent(this, ReferenceActivity.class);
                finish();
                break;
        }
        startActivity(intent);
        return true;
    }

    public void SaveGame(View view){
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("Points", AmountOfPoints);
        editor.apply();
    }

}

