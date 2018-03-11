package design.wang.com.designpatterns;

/**
 * 知其然，而后知其所以然
 * 倔强小指，成名在望
 * 作者： Tomato
 * on 2018/3/11 0011.
 * design.wang.com.designpatterns
 * 功能、作用：
 */

public class VibrationSceneMode extends SceneMode{
    @Override
    public String getSceneModelName() {
        return "振动模式";
    }

    @Override
    public boolean isRing() {
        return false;
    }

    @Override
    public boolean isVibration() {
        return true;
    }
}
