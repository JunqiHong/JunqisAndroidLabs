package algonquin.cst2335.ID041012545;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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




