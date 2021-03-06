package hfad.com.project;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.content.Intent;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;;

import static hfad.com.project.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
    MediaPlayer myMusic;
    Intent intent;
    private int AmountOfPoints = 0;
    private int pepeLevel = 1;
    private boolean Countdown;


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
        pepeLevel = prefs.getInt("pepeLevel" , 1);
        punten.setText("Amount of points:  " + AmountOfPoints);


    }

    public void OnGeluid(View view){
        ImageButton aan = (ImageButton) findViewById(R.id.geluid);
        aan.setBackgroundColor(Color.WHITE);
        aan.setSoundEffectsEnabled(false);
        if(myMusic.isPlaying()){
            myMusic.pause();
            aan.setImageResource(R.drawable.muziek_uit);
        } else {
            myMusic.start();
            myMusic.setLooping(true);
            aan.setImageResource(R.drawable.muziek_aan);
        }
    }

    public void OnTap(View view) {
        AmountOfPoints = AmountOfPoints + pepeLevel;
        ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
        pepe.setImageResource(R.drawable.pepe2);

        String AmountOfPoint = Integer.toString(AmountOfPoints);
        TextView punten = (TextView) findViewById(R.id.ppc);
        punten.setText("Amount of points:  " + AmountOfPoint);
        pepe.setBackgroundColor(Color.GREEN);
        pepe.setSoundEffectsEnabled(false);

        if(Countdown == false){
            CountDownTimer cdt;
            cdt = new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished){
                    Countdown = true;
                    ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
                    pepe.setImageResource(R.drawable.pepe2);
                    pepe.setBackgroundColor(Color.GREEN);
                }
                public void onFinish() {
                    Countdown = false;
                    ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
                    pepe.setImageResource(R.drawable.pepe);
                    pepe.setBackgroundColor(Color.RED);
                }
            };
            cdt.cancel();
            cdt.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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

    public void SaveGame(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("Points", AmountOfPoints);
        editor.apply();
    }

}
