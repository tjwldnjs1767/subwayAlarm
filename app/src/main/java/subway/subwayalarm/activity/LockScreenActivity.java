package subway.subwayalarm.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.WindowManager;
import android.widget.Toast;

import library.slidebutton.SlideButton;
import subway.subwayalarm.R;


public class LockScreenActivity extends Activity {
    Vibrator vibrator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock_screen);

        SlideButton slideButton = (SlideButton) findViewById(R.id.slide_btn);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {0, 200, 500};

        vibrator.vibrate(pattern, 0);

        slideButton.setText("밀어서 알람해제");
        slideButton.setSlideButtonListener(new SlideButton.SlideButtonListener() {
            @Override
            public void onSlide() {
                vibrator.cancel();
                finish();
            }
        });

        slideButton.setOnSlideChangeListener(new SlideButton.OnSlideChangeListener() {
            @Override
            public void onSlideChange(float position) {
            }
        });
    }

    @Override
    public void onBackPressed() { }
}