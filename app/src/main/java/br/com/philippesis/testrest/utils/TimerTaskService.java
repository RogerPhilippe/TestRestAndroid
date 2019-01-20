package br.com.philippesis.testrest.utils;

import android.util.Log;

import java.util.TimerTask;

public class TimerTaskService extends TimerTask {

    private long mTimer;

    public TimerTaskService(long timer) {
        mTimer = timer;
    }

    @Override
    public void run() {

        Log.i("mTimer", String.valueOf(mTimer));

        if(mTimer > 0) {
            mTimer--;
        } else {
            Log.i("mTimer", "TaskFinalizada");
            this.cancel();
        }

    }

    public long getmTimer() {
        return mTimer;
    }

}
