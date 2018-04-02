package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class PracticalTest01Var07Service extends Service {

    private ProcessingThread processingThread;

    public class ProcessingThread extends Thread {

        private Context context = null;
        private boolean isRunning = true;

        private Random random = new Random();

        public ProcessingThread(Context context) {
            this.context = context;
            Log.d(Constants.SERV, "constructor()");
        }

        @Override
        public void run() {
            Log.d(Constants.SERV, "Thread has started!");
            while (isRunning) {
                sendMessage();
                sleep();
            }
            Log.d(Constants.SERV, "Thread has stopped!");
        }

        private void sendMessage() {
            Intent intent = new Intent();
            intent.setAction(Constants.SET_TEXT);
            intent.putExtra(Constants.FIELD1, random.nextInt());
            intent.putExtra(Constants.FIELD2, random.nextInt());
            intent.putExtra(Constants.FIELD3, random.nextInt());
            intent.putExtra(Constants.FIELD4, random.nextInt());

            Log.d(Constants.SERV, "sending message");
            context.sendBroadcast(intent);
        }

        private void sleep() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }

        public void stopThread() {
            isRunning = false;
        }
    }

    public PracticalTest01Var07Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        processingThread = new ProcessingThread(this);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
