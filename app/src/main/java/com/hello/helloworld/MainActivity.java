package com.hello.helloworld;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hello.helloworld.viewwrappers.ColorSliders;
import com.hello.helloworld.viewwrappers.SliderWithText;

public class MainActivity extends AppCompatActivity {

    private ColorSliders colorSliders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }


    private void initControls(){

        final TextView test = findViewById(R.id.tv_color_text);

        colorSliders = new ColorSliders(this, new ColorSliders.Callback() {
            @Override
            public void onChange(String argb) {
                try {
                    test.setBackgroundColor(Color.parseColor(argb));
                }
                catch (Exception e){
                    Log.d("nplog","["+argb + "]" + argb.length() + ": " + e.getLocalizedMessage() );
                }
            }
        });

    }
}
