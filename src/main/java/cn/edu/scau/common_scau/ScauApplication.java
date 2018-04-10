package cn.edu.scau.common_scau;

import android.app.Application;

/**
 * Created by QiHuang on 2018/2/8.
 */

public class ScauApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ScauActivityLiftCycleManager());
    }
}
