package com.hello.helloworld.viewwrappers;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import com.hello.helloworld.R;

import java.util.ArrayList;

public class ColorSliders {

    public interface Callback{
        void onChange(String argb);
    }

    private enum ColorPos {

        ALPHA(0,R.id.layout_group_slider_alpha,Color.BLACK),
        RED(1,R.id.layout_group_slider_red,Color.RED),
        GREEN(2,R.id.layout_group_slider_green,Color.GREEN),
        BLUE(3,R.id.layout_group_slider_blue,Color.BLUE);

        private final int pos;
        private final int layoutGroupId;
        private final int thumbColor;

        ColorPos(int pos,int layoutGroupId, int thumbColor){
            this.pos = pos;
            this.layoutGroupId = layoutGroupId;
            this.thumbColor = thumbColor;
        }
    };

    private ArrayList<SliderWithText> colorSliders = new ArrayList<>();
    private final Callback callback;

    private final SliderWithText.Callback internalCallback =  new SliderWithText.Callback() {
        @Override
        public void onChange(int progress) {
            if(callback!=null) {
                callback.onChange(strArgb());
            }
        }
    };

    public ColorSliders(Activity activity, Callback callback){

        this.callback = callback;

        for(ColorPos colorPos: ColorPos.values() ) {

            colorSliders.add(colorPos.pos,
                    new SliderWithText(activity.findViewById(colorPos.layoutGroupId),colorPos.thumbColor,internalCallback));
        }
    }

    public int argb(){
        return Color.argb(colorSliders.get(ColorPos.ALPHA.pos).getValue(),
                colorSliders.get(ColorPos.RED.pos).getValue(),
                colorSliders.get(ColorPos.GREEN.pos).getValue(),
                colorSliders.get(ColorPos.BLUE.pos).getValue());

    }

    /*
      Always returns String of length begining with # suitable to be used with Color.parseColor(int)
     */
    public synchronized String strArgb(){

        String strArgb =  "#"
        +colorSliders.get(ColorPos.ALPHA.pos).hex()
        +colorSliders.get(ColorPos.RED.pos).hex()
        +colorSliders.get(ColorPos.GREEN.pos).hex()
        +colorSliders.get(ColorPos.BLUE.pos).hex();

        return strArgb;
    }
}
