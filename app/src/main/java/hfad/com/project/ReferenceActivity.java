package hfad.com.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.net.URI;

public class ReferenceActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
    }

    public void Pepeinfo(View view){
        Intent browserIntent=new Intent(intent.ACTION_VIEW, Uri.parse("http://knowyourmeme.com/memes/pepe-the-frog"));
        startActivity(browserIntent);

    }
    public void Dogeinfo(View view){
        Intent browserIntent=new Intent(intent.ACTION_VIEW, Uri.parse("http://knowyourmeme.com/memes/doge"));
        startActivity(browserIntent);

    }
    public void Sealinfo(View view){
        Intent browserIntent=new Intent(intent.ACTION_VIEW, Uri.parse("http://knowyourmeme.com/memes/awkward-moment-seal"));
        startActivity(browserIntent);

    }

}
