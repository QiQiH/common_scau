package cn.edu.scau.common_scau;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import cn.edu.scau.common_util.AppUtil;

/**
 * Created by QiHuang on 2018/1/19.
 */

public class ScauActivityLiftCycleManager implements Application.ActivityLifecycleCallbacks {


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppUtil.activityCount++;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        AppUtil.activityCount--;
        if (AppUtil.activityCount == 0) {
            AppUtil.destroy();
        }
    }
}
