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
    boolean isCounterRunning  = false;
    public static final String MY_PREFS_NAME = "FileName";
    private double pepeCost;

    private int mInterval = 5000;
    private Handler mHandler;

    private double AmountOfPoints;
    private int pepeLevel;
    CountDownTimer mCountDownTimer;

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
}