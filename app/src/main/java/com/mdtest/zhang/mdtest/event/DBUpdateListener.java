package com.mdtest.zhang.mdtest.event;


import android.database.sqlite.SQLiteDatabase;

import com.jayfeng.lesscode.core.AppLess;
import com.litesuits.orm.db.assit.SQLiteHelper;

/**
 * Created by zhangxx on 2017/2/15.
 * MDTest --> com.mdtest.zhang.mdtest.event
 */

public class DBUpdateListener implements SQLiteHelper.OnUpdateListener {
    @Override
    public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i< AppLess.$vercode()&&i1== AppLess.$vercode()){
        }
    }
}
