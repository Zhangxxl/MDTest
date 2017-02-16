package com.mdtest.zhang.mdtest.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.support.annotation.Nullable;

import java.util.Stack;

/**
 * Created by zhangxx on 2017/2/16.
 * MDTest --> com.mdtest.zhang.mdtest.utils
 */

public class ActManager {

    // Activity栈
    private static Stack<Activity> activityStack;
    // 单例模式
    private static ActManager instance;

    private ActManager() {
    }

    /**
     * 单一实例
     * @return 实例
     */
    public static synchronized ActManager getInstance() {
        if (instance == null) {
            instance = new ActManager();
        }
        activityStack = new Stack<Activity>();
        return instance;
    }

    /**
     * 添加Activity 到堆栈
     * @param activity 要添加的Activity
     * @return 添加结果
     */
    public boolean addActivity(Activity activity) {
        return activityStack.add(activity);
    }

    /**
     * 从栈中移除activity
     * @param activity 要移除的activity
     * @return 移除结果
     */
    public boolean removeActivity(Activity activity){
        return activityStack.remove(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     * @return Activity
     */
    @Nullable
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null)
            activity.finish();
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
                activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
