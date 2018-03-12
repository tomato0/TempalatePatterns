# TempalatePatterns
Java设计模式之模板设计模式
# Java设计模式，写漂亮的代码  
# ————模板方法设计模式
---  
### 简介：  
模板方法设计模式是Java设计模式中很简单、应用非常广泛的的一种设计模式，该模式体现了编程的抽象思想（抽象是所有子类的共性封装），  
仅仅使用了Java的继承机制。其实很可能在你写代码的过程中已经使用过了很多次这种模式，只是你还不太清楚那就是模板方法设计模式。  
接下来就让我们一起去看看，到底什么是模板方法设计模式呢？  
### 首先看一下描述：  
定义一个操作中的算法的框架，而将一些步骤延迟到了子类中。使得子类可以不改变一个算法的结构即可重定义该算法的某些步骤。
### UML类图描述：  
下图就是模板方法设计模式的UML类图描述，正如你所见，没错，就是这么简单，一目就能了然。
![TempalatePattern UML类图](https://github.com/tomato0/TempalatePatterns/blob/master/tempalate_patterns.png)  
其中AbstractClass叫做抽象模板类，实现了模板方法，定义了算法的骨架，它的方法分为两类：    
1. 基本方法：基本方法也叫作基本操作，是由子类实现的方法，并且在模板方法被调用.  
2. 模板方法：可以有一个或者几个，一般是一个具体方法，也就是一个框架，实现对基本方法的调度，完成固定的逻辑。  
具体类：ConcreteClass:实现抽象类中的抽象方法，完成完整的算法。  
---
### 下面通过一个简单的例子描述下模板方法模式的应用：  
本例子通过模拟手机情景模式的过程，简单描述下模板方法的应用：  
``` java
public abstract class SceneMode {
    //这里定义了一个抽象类，即模板方法模式的抽象模板类，把子类共有的一些操作抽象到该类中，情景模式普遍就是响铃和震动的操作，这里将通用的流程放到该类中，具体的配置通过其子类来完成
    private static final String TAG = "SceneMode";
    private static final String DEFAULT_RING = "little Star";
    private String mRing = DEFAULT_RING;
    private boolean isRing;
    private boolean isVibrating;
    public void goModel() {
       Log.d(TAG, "开启模式：");
    }
    //共性方法的抽象，由子类去实现具体的情景模式名称
    public abstract String getSceneModelName();

    public abstract boolean isRing();

    public abstract boolean isVibration();

    //钩子函数，外界条件改变，影响了模板函数的执行
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
```  
---
``` java
public class RingSceneMode extends SceneMode{
    //子类响铃模式，完成对应的配置，达到重定义父类called的动作
    @Override
    public String getSceneModelName() {
        return "响铃模式";
    }

    @Override
    public boolean isRing() {
        return true;
    }

    @Override
    public boolean isVibration() {
        return false;
    }
}
```
---
``` java
public class VibrationSceneMode extends SceneMode{
  //子类震动模式，完成对应的配置，达到重定义父类called的动作
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
```
---
``` java
如下为场景类，创建不同的模式来完成对应的模式匹配，实现特定模式达到的效果
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
```
---
### 模板方法模式的优点：  
1. 封装不变部分，扩展可变部分；  
2. 提取公共部分代码，便于维护；  
3. 行为由父类控制，子类实现。  
---
### 模板方法模式的使用场景  
1. 多个子类有公有的方法，并且逻辑基本相同时。  
2. 重要复杂的算法，可以把核心算法设计为模板方法，周边的相关细节功能则有各个子类实现。  
3. 重构时， 模板方法模式是一个经常使用的模式，把相同的代码抽取到父类中，然后通过钩子函数（外界条件改变，影响到模板方法的执行）约束其行为。
