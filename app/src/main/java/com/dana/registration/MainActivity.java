package com.dana.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.dana.registration.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initViews();
    }

    private void initViews() {
        email =  mBinding.edittext1.getText().toString();
        mBinding.activityButtonNavigation.setOnClickListener(view -> {
            Intent i = new Intent(this, SetProfileActivity.class);
            i.putExtra("1", email);
            startActivity(i);
        });
    }
}