package com.mdtest.zhang.mdtest.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by zhangxx on 2017/2/13.
 * MDTest --> com.mdtest.zhang.mdtest.utils
 */

public class ProgressDialogUtils {

    private Context context;
    private ProgressDialog dialog;

    public void show(String msg){
        show(msg,true);
    }

    public void show(boolean cancelAble){
        show("请稍后...",cancelAble);
    }

    public void show(String msg,boolean cancelAble){
        show(null,msg,cancelAble);
    }

    public void show(String title,String msg,boolean cancelAble){
        dialog = new ProgressDialog(context);
        dialog.setIndeterminate(true);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(cancelAble);
        dialog.show();
    }

    public void hide(){
        if (dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener listener){
        dialog.setOnDismissListener(listener);
    }

}
