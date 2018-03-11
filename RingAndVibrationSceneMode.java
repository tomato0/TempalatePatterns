package design.wang.com.designpatterns;

/**
 * 知其然，而后知其所以然
 * 倔强小指，成名在望
 * 作者： Tomato
 * on 2018/3/11 0011.
 * design.wang.com.designpatterns
 * 功能、作用：
 */

public class RingAndVibrationSceneMode extends SceneMode {
    @Override
    public String getSceneModelName() {
        return "震动 + 响铃";
    }

    @Override
    public boolean isRing() {
        return true;
    }

    @Override
    public boolean isVibration() {
        return true;
    }
}
