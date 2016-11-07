package hfad.com.project;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class UpgradeActivity extends MainActivity {
    public static final String MY_PREFS_NAME = "FileName";
    private double pepeCost;

    private Handler mHandler;

    private double AmountOfPoints;
    private int pepeLevel;

    private double dogeCost;

    private int dogeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade);

        TextView punten = (TextView)findViewById(R.id.punten);
        TextView pepeLevelText = (TextView) findViewById(R.id.pepeLevel);
        TextView pepePoints = (TextView) findViewById(R.id.pepepoints);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        AmountOfPoints = prefs.getInt("Points" , 0);
        pepeLevel = prefs.getInt("pepeLevel" , 0);
        pepeCost = prefs.getInt("pepeCost", 0);
        String pepeCostString = Integer.toString((int)pepeCost);

        String pepeLevelString = Integer.toString(pepeLevel);
        final String AmountOfPoint = Integer.toString((int)AmountOfPoints);
        punten.setText("Amount of points:  " + AmountOfPoint);
        pepeLevelText.setText("Level : " + pepeLevelString);
        pepePoints.setText(pepeCostString);

        TextView dogeLevelText = (TextView) findViewById(R.id.dogeLevel);
        TextView dogePoints = (TextView) findViewById(R.id.dogepoints);

        dogeLevel = prefs.getInt("dogeLevel", 0);
        dogeCost = prefs.getInt("dogeCost", 0);
        String dogeCostString = Integer.toString((int)dogeCost);

        String dogeLevelString = Integer.toString(dogeLevel);
        dogeLevelText.setText("Level : " + dogeLevelString);
        dogePoints.setText(dogeCostString);

        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try{
                Button pepeButton = (Button) findViewById(R.id.pepeLevelButton);
                Button dogeButton = (Button) findViewById(R.id.dogeLevelButton);
                if(AmountOfPoints < pepeCost){
                    pepeButton.setEnabled(false);
                }else{
                    pepeButton.setEnabled(true);
                }

           //     if(AmountOfPoints < dogeCost){
           //         dogeButton.setEnabled(false);
           //     }else{
           //         dogeButton.setEnabled(true);
           //     }
            } finally {
                mHandler.postDelayed(mStatusChecker, 10);
            }
        }
    };

    void startRepeatingTask(){
        mStatusChecker.run();
    }

    void stopRepeatingTask(){
        mHandler.removeCallbacks(mStatusChecker);

    }

    public void pepeLevelUp(View view){
        pepeCost = 100 * (pepeLevel * 1.3);
        AmountOfPoints = AmountOfPoints - pepeCost;
        pepeLevel = pepeLevel + 1;

        TextView pepepoints = (TextView) findViewById(R.id.pepepoints);
        TextView punten = (TextView)findViewById(R.id.punten);
        TextView pepeLevelText = (TextView) findViewById(R.id.pepeLevel);

        String AmountOfPoint = Integer.toString((int)AmountOfPoints);
        String pepeCostString = Integer.toString((int)pepeCost);
        String pepeLevelString = Integer.toString(pepeLevel);

        pepepoints.setText(pepeCostString);
        punten.setText(AmountOfPoint);
        pepeLevelText.setText("Level : " + pepeLevelString);

        int pepeCostInteger = (int)(pepeCost + 0.5d);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("pepeLevel", pepeLevel);
        editor.putInt("pepeCost", pepeCostInteger);
        editor.apply();
    }

    public void dogeLevelUp(View view){
        dogeCost = 1000 * (dogeLevel * 1.5);
        AmountOfPoints = AmountOfPoints - dogeCost;
        dogeLevel = dogeLevel + 1;

        TextView dogepoints = (TextView) findViewById(R.id.dogepoints);
        TextView punten = (TextView)findViewById(R.id.punten);
        TextView dogeLevelText = (TextView) findViewById(R.id.dogeLevel);

        String AmountOfPoint = Integer.toString((int)AmountOfPoints);
        String dogeCostString = Integer.toString((int)dogeCost);
        String dogeLevelString = Integer.toString(dogeLevel);

        dogepoints.setText(dogeCostString);
        punten.setText(AmountOfPoint);
        dogeLevelText.setText("Level : " + dogeLevelString);

        int dogeCostInteger = (int)(dogeCost + 0.5d);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("dogeLevel", dogeLevel);
        editor.putInt("dogeCost", dogeCostInteger);
        editor.apply();

    }
}