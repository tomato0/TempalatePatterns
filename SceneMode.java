package design.wang.com.designpatterns;

import android.util.Log;

/**
 * 知其然，而后知其所以然
 * 倔强小指，成名在望
 * 作者： Tomato
 * on 2018/3/11 0011.
 * design.wang.com.designpatterns
 * 功能、作用：
 */

public abstract class SceneMode {

    private static final String TAG = "SceneMode";
    private static final String DEFAULT_RING = "little Star";
    private String mRing = DEFAULT_RING;
    private boolean isRing;
    private boolean isVibrating;
    public void goModel() {
       Log.d(TAG, "开启模式：");
    }
    public abstract String getSceneModelName();

    public abstract boolean isRing();

    public abstract boolean isVibration();

    public void setRingMusic(String ringName) {
        this.mRing = ringName;
    };

    private void playRing() {
        this.isRing = true;
        Log.d(TAG, "play: " + mRing);
    }

    private void vibrating() {
        this.isVibrating = true;
        Log.d(TAG, "vibrating");
    }

    private void startRing() {
        if (isRing()) playRing();
    }

    private void startVibrating() {
        if (isVibration()) vibrating();
    }

    private void stopRing() {
        if (isRing) {
            Log.d(TAG, "停止响铃");
            this.isRing = false;
        }
    }

    private void stopVibration() {
        if (isVibrating) {
            Log.d(TAG, "停止震动");
            this.isVibrating = false;
        }
    }

    public final void called() {
        //为了防止恶意的操作，一般模板方法都加上final关键字，不允许被重写
        Log.d(TAG, "called: 被呼叫");
        new Thread() {
            @Override
            public void run() {
                startRing();
                startVibrating();
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopRing();
                stopVibration();
            }
        }.start();
    }
}
