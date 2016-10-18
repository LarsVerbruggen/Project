package hfad.com.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int AmountOfPoints = 0;


    public void OnTap(View view){
        AmountOfPoints = AmountOfPoints + 1;

    }
}
