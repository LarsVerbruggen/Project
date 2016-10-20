package hfad.com.project;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private int AmountOfPoints = 0;


    public void OnTap(View view){
        AmountOfPoints = AmountOfPoints + 1;
        ImageButton pepe = (ImageButton) findViewById(R.id.pepe);
        pepe.setImageResource(R.drawable.pepe2);

        String AmountOfPoint = Integer.toString(AmountOfPoints);
        TextView punten = (TextView)findViewById(R.id.dps);
        punten.setText("Amount of points:  " + AmountOfPoint);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



}

