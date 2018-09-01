package com.hello.helloworld.viewwrappers;

import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.hello.helloworld.R;

public class SliderWithText {

    public interface Callback{
        void onChange(int progress);
    };

    private final SeekBar seekBar;
    private final EditText editText;
    private final Callback callback;

    public SliderWithText(View rootLayout, @ColorInt int thumbColor, Callback callback){

        this.callback = callback;

        seekBar = rootLayout.findViewById(R.id.seekBar_value);
        seekBar.getProgressDrawable().setColorFilter(thumbColor, PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(thumbColor, PorterDuff.Mode.SRC_IN);

        editText = rootLayout.findViewById(R.id.editText_value);

        initControls();
    }


    private void initControls(){

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editText.setText(""+progress);
                if(callback!=null) {
                    callback.onChange(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        editText.setText(""+seekBar.getProgress());
    }

    public int getValue(){
        return seekBar.getProgress();
    }

    /*
     Always returns two digits 00, 01, 0A etc.  the leading 0x is not included in returned string
     */
    public synchronized String hex(){
        String preFix = "";
        String hex = Integer.toHexString(seekBar.getProgress());

        if(hex.length()<2){
            preFix = "0";
        }

        return preFix+hex;
    }

}
