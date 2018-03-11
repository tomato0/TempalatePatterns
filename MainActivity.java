package design.wang.com.designpatterns;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private SceneMode mSceneMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.设置RingMode
        mSceneMode = new RingSceneMode();
        mSceneMode.setRingMusic("lemon tree");
        Log.d(TAG, mSceneMode.getSceneModelName());
        mSceneMode.called();

        //2.设置VibrationMode
        mSceneMode = new VibrationSceneMode();
        Log.d(TAG, mSceneMode.getSceneModelName());
        mSceneMode.called();

        //3.设置Ring + Vibration Mode
        mSceneMode = new RingAndVibrationSceneMode();
        mSceneMode.setRingMusic("lemon tree");
        Log.d(TAG, mSceneMode.getSceneModelName());
        mSceneMode.called();
    }
}
