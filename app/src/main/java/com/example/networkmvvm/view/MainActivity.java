package com.example.networkmvvm.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networkmvvm.R;
import com.example.networkmvvm.adapter.ShibeAdapter;
import com.example.networkmvvm.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private RecyclerView rvShibe;
    private Button btnLoad;
    private Button btnToggleView;
    private EditText etCount;
    private ShibeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvShibe = findViewById(R.id.rvShibies);
        rvShibe.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShibeAdapter();
        rvShibe.setAdapter(adapter);
        rvShibe.setHasFixedSize(true);

        btnLoad = findViewById(R.id.btnLoad);
        btnToggleView = findViewById(R.id.btnToggleView);
        etCount = findViewById(R.id.etCount);
        etCount.setText("5");
        viewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);

        setupObservers();
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count = etCount.getText().toString();
                viewModel.fetchShibeData(Integer.parseInt(count));
            }
        });

        btnToggleView.setOnClickListener(
                view -> {
                    if (rvShibe.getLayoutManager() instanceof GridLayoutManager) {
                        rvShibe.setLayoutManager(new LinearLayoutManager(this));
                        btnToggleView.setText("Grid");
                    } else {
                        rvShibe.setLayoutManager(new GridLayoutManager(this, 4));
                        btnToggleView.setText("List");
                    }
                }
        );
    }

    private void setupObservers() {
        viewModel.getShibesLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                if (urls != null) {
                    if (!urls.isEmpty())
                        adapter.setUrls(urls);
                    else
                        Toast.makeText(MainActivity.this, "NO DATA", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getErrorLiveData().observe(this, isError -> {
            if (!isError.isEmpty())
                Toast.makeText(this, isError, Toast.LENGTH_SHORT).show();
        });
    }
}
