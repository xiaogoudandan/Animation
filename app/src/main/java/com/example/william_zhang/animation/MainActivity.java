package com.example.william_zhang.animation;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.william_zhang.animation.view.LoveIconView;
import com.example.william_zhang.animation.view.IcanView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Runnable{
    private Handler handler;
    private IcanView iconView;
    private int[] resIds = {
            R.drawable.live_icon_animal_bear,
            R.drawable.live_icon_animal_cat,
            R.drawable.live_icon_animal_pig,
            R.drawable.live_icon_candy_blue,
            R.drawable.live_icon_candy_orange,
            R.drawable.live_icon_candy_pink,
            R.drawable.live_icon_christmas_candy1,
            R.drawable.live_icon_christmas_candy2,
            R.drawable.live_icon_christmas_gift1,
            R.drawable.live_icon_christmas_gift2,
            R.drawable.live_icon_christmas_gift3,
            R.drawable.live_icon_christmas_gift4,
            R.drawable.live_icon_christmas_hat1,
            R.drawable.live_icon_christmas_hat2,
            R.drawable.live_icon_christmas_hat3,
            R.drawable.live_icon_christmas_ring1,
            R.drawable.live_icon_christmas_ring2,
            R.drawable.live_icon_christmas_ring3,
            R.drawable.live_icon_christmas_socks1,
            R.drawable.live_icon_christmas_socks2,
            R.drawable.live_icon_christmas_socks3,
            R.drawable.live_icon_christmas_socks4,
            R.drawable.live_icon_crown,
            R.drawable.live_icon_diamond_red,
            R.drawable.live_icon_diamond_blue,
            R.drawable.live_icon_diamond_yellow,
            R.drawable.live_icon_donut_blue,
            R.drawable.live_icon_donut_brown,
            R.drawable.live_icon_donut_pink,
            R.drawable.live_icon_flower_purple,
            R.drawable.live_icon_flower_red,
            R.drawable.live_icon_flower_yellow,
            R.drawable.live_icon_heart_1,
            R.drawable.live_icon_heart_2,
            R.drawable.live_icon_heart_3,
            R.drawable.live_icon_heart_4,
            R.drawable.live_icon_heart_5,
            R.drawable.live_icon_heart_6,
            R.drawable.live_icon_heart_7,
            R.drawable.live_icon_heart_8,
            R.drawable.live_icon_heart_9,
            R.drawable.live_icon_spring_coin1,
            R.drawable.live_icon_spring_coin2,
            R.drawable.live_icon_spring_coin3,
            R.drawable.live_icon_spring_fish1,
            R.drawable.live_icon_spring_fish2,
            R.drawable.live_icon_spring_fu1,
            R.drawable.live_icon_spring_fu2,
            R.drawable.live_icon_spring_lantern1,
            R.drawable.live_icon_spring_lantern2,
            R.drawable.live_icon_spring_lantern3,
            R.drawable.live_icon_spring_redenvelope1,
            R.drawable.live_icon_spring_redenvelope2,
            R.drawable.live_icon_spring_redenvelope3,
            R.drawable.live_icon_spring_yuanao1,
            R.drawable.live_icon_spring_yuanao2,
    };
    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconView = (IcanView) findViewById(R.id.ican);
        handler = new Handler();
    }

    public void stop(View view) {
        handler.removeCallbacksAndMessages(null);
    }


    public void load(View view)
    {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(this, 50);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(this);
        super.onDestroy();
    }

    @Override
    public void run() {
        iconView.addLoveIcon(resIds[random.nextInt(resIds.length)]);
        handler.postDelayed(this, 50);
    }
}
