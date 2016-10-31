package hfad.com.project;


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
        pepe.setBackgroundColor(Color.RED);
        myMusic = MediaPlayer.create(this, R.raw.giveallup);


    }

    private int AmountOfPoints = 0;

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
        TextView punten = (TextView)findViewById(R.id.pps);
        punten.setText("Amount of points:  " + AmountOfPoint);
        pepe.setBackgroundColor(Color.GREEN);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Intent intent;

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.Home_Screen:
                intent = new Intent(this, MainActivity.class);
                finish();
            case R.id.Upgrade_Screen:
                intent = new Intent(this, UpgradeActivity.class);
                finish();
            case R.id.Refrences_List:
                intent = new Intent(this, ReferenceActivity.class);
                finish();
        }
        startActivity(intent);
        return true;
    }

}

