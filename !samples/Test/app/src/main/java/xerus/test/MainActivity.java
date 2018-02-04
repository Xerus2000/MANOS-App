package xerus.test;

import android.hardware.Sensor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView mainText;

    Handler handler;

    public int curtab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //handler.removeCallbacksAndMessages(null);
            curtab = item.getItemId();
            switch (curtab) {
                case R.id.navigation_home:
                    mainText.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mainText.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mainText.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        curtab = R.id.navigation_home;

        Sensors.init(this);
        Sensors.register(Sensor.TYPE_MAGNETIC_FIELD, v -> {
            if(curtab == R.id.navigation_home)
                mainText.append(Arrays.toString(v.values));
        }, 1000000);
        //handler = new Handler(Looper.getMainLooper());
    }

}
