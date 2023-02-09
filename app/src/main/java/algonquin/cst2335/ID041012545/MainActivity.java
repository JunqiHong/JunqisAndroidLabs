package algonquin.cst2335.ID041012545;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override  //This starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);

        //loads buttons / text on screen
        setContentView(R.layout.activity_main);
        //R means res, layout is the folder, activity-main is the file
        ImageView imgView = findViewById(R.id.imageView);
        Switch sw = findViewById(R.id.switch1);
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imgView = findViewById(R.id.imageView);
            sw = findViewById(R.id.switch1);

            sw.setOnCheckedChangeListener((btn, isChecked) -> {

            });
        }


    }
}