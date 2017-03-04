package com.example.hammer.task_10_sqlite_asynctask_loader.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.Voice;

import com.example.hammer.task_10_sqlite_asynctask_loader.db.CRUDSQLite;

/**
 * Created by hammer on 03.03.2017.
 */

public class MyTask extends AsyncTask<Void, Void, Void> {
    public static final Integer DELETE  = 0;
    public static final Integer QUERY   = 1;
    public static final Integer UPDATE  = 2;
    public static final Integer INSERT  = 3;
    ContentResolver cr;

    public MyTask (Context c){
        cr = c.getContentResolver();
    }

//    @Override
//    protected Object doInBackground(Object... params) {
//        switch ((Integer) params[0]){
////            case DELETE:
////                cr.delete();
////                break;
////            case QUERY:
////                cr.query();
////                break;
////            case UPDATE:
////                cr.update();
////                break;
////            case INSERT:
////                cr.insert();
////                break;
//            default:
//                break;
//        }
//        return null;
//    }

    @Override
    protected Void doInBackground(Void... params) {
        switch ((Integer) params[0]){
//            case DELETE:
//                cr.delete();
//                break;
//            case QUERY:
//                cr.query();
//                break;
//            case UPDATE:
//                cr.update();
//                break;
//            case INSERT:
//                cr.insert();
//                break;
            default:
                break;
        }
        return null;
    }
}
