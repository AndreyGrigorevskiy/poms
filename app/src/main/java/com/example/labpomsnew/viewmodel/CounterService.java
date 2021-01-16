package com.example.labpomsnew.viewmodel;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.labpomsnew.view.ServicesAndIntentionsActivity;

import static java.lang.Thread.sleep;

public class CounterService extends Service {

    MyTask myTask;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        PendingIntent pendingIntent = intent.getParcelableExtra(ServicesAndIntentionsActivity.PENDING_INTENT_KEY);
        myTask = new MyTask(pendingIntent);
        startWork();
        return super.onStartCommand(intent, flags, startId);
    }

    void startWork()
    {
        Thread thread = new Thread(myTask, "t1");
        thread.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myTask.stop();
    }

    private class MyTask implements Runnable
    {

        private boolean exit;
        private PendingIntent pendingIntent;

        public MyTask(PendingIntent pendingIntent)
        {
            this.pendingIntent = pendingIntent;
        }

        @Override
        public void run() {
            talkToCreator(new Intent(), ServicesAndIntentionsActivity.COUNTER_START);

            exit = false;

            final int VERY_MUCH = 100000;
            for (int i = 0;i<VERY_MUCH && !exit; i++)
            {
                talkToCreator(new Intent().putExtra(ServicesAndIntentionsActivity.COUNTER_ANSWER_KEY,i),ServicesAndIntentionsActivity.COUNTER_ANSWER);

                try
                {
                    sleep(4000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                    break;
                }
            }
            talkToCreator(new Intent(),ServicesAndIntentionsActivity.COUNTER_FINISH);
        }

        void stop(){ exit = true;}

        void talkToCreator(Intent intent, int code)
        {
            try
            {
                pendingIntent.send(CounterService.this, code, intent);
            }
            catch (PendingIntent.CanceledException e)
            {
                e.printStackTrace();
            }
        }
    }


}
