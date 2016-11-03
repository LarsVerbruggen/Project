package hfad.com.project;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UpgradeActivity extends MainActivity {

    public static final String MY_PREFS_NAME = "FileName";
    private int AmountOfPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);
        TextView punten = (TextView)findViewById(R.id.punten);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        AmountOfPoints = prefs.getInt("Points" , 0);
        String AmountOfPoint = Integer.toString(AmountOfPoints);
        punten.setText("Amount of points:  " + AmountOfPoint);
    }







}
