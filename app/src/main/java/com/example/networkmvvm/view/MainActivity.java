package com.example.networkmvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.networkmvvm.R;
import com.example.networkmvvm.viewmodel.MainViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ImageView tvDisplay;
    private Button btnLoad;
    private EditText etCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Setting up view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = findViewById(R.id.tvDisplay);
        btnLoad = findViewById(R.id.btnLoad);
        etCount = findViewById(R.id.etCount);
        etCount.setText("5");

        // Initializing viewModel instance
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);

        setupObservers();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = etCount.getText().toString();
                viewModel.fetchShibeData(Integer.parseInt(count));
            }
        });
    }

    private void setupObservers() {
        viewModel.getShibesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (strings != null) {
                    if (strings.isEmpty()) {
                        //tvDisplay.setText("EMPTY LIST");
                    }
                    else{
                        for(int i = 0; i < strings.size(); i++ ){
                            //tvDisplay.setText(strings.get(i));
                            Picasso.get().load(strings.get(i)).into(tvDisplay);

                        }
                    }
                }
            }
        });


        viewModel.getErrorLiveData().observe(this, isError -> {
            if (!isError.isEmpty())
                Toast.makeText(this, isError, Toast.LENGTH_SHORT).show();
        });
    }
}
