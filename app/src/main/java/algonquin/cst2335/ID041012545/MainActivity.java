package algonquin.cst2335.ID041012545;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import algonquin.cst2335.ID041012545.data.MainActivityViewModel;
import algonquin.cst2335.ID041012545.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    MainActivityViewModel viewModel;

    @Override  //This starts the application
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);



        binding = ActivityMainBinding.inflate(getLayoutInflater() ); // produces layout inflater
        binding.switch1.setChecked(true);

        binding.switch1.setOnClickListener((param1) ->{

            Toast.makeText(MainActivity.this, "This is the message on screen for switch", Toast.LENGTH_LONG).show();
        });

        new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b){

            }
        };

        setContentView(  binding.getRoot() ); //load stuff on screen


        binding.textview.setText(viewModel.editString);
        binding.button.setText(viewModel.editString);




        //loads buttons / text on screen
        setContentView(binding.getRoot()); //load stuff on screen
        //R means res, layout is the folder, activity-main is the file



        binding.textview.setText(viewModel.editString);

        binding.button.setOnClickListener((veeew) -> {

        });

        binding.button.setOnClickListener(v -> {
            viewModel.editString="You Clicked the button";

            binding.textview.setText(viewModel.editString);
            binding.button.setText(viewModel.editString);

//            viewModel.editString=binding.editString.getText().toString();
//            binding.textview.setText("You clicked the button");
        });
    };



};




