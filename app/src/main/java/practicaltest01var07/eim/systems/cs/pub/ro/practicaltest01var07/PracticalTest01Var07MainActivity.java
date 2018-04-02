package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var07MainActivity extends AppCompatActivity {

    boolean service_started;

    Button set_button;
    EditText num1_text, num2_text, num3_text, num4_text;

    private IntentFilter intentFilter = new IntentFilter();


    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            String snum1 = num1_text.getText().toString();
            int num1;
            try {
                num1 = Integer.parseInt(snum1);
            } catch(NumberFormatException nfe) {
                return;
            }

            String snum2 = num2_text.getText().toString();
            int num2;
            try {
                num2 = Integer.parseInt(snum2);
            } catch(NumberFormatException nfe) {
                return;
            }

            String snum3 = num3_text.getText().toString();
            int num3;
            try {
                num3 = Integer.parseInt(snum3);
            } catch(NumberFormatException nfe) {
                return;
            }

            String snum4 = num4_text.getText().toString();
            Log.d(Constants.TAG, snum4);
            int num4;
            try {
                num4 = Integer.parseInt(snum4);
            } catch(NumberFormatException nfe) {
                return;
            }

            Log.d(Constants.TAG, "" + num1 + " " + num2 + " " + num3 + " " + num4);

            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);
            intent.putExtra(Constants.FIELD1, num1);
            intent.putExtra(Constants.FIELD2, num2);
            intent.putExtra(Constants.FIELD3, num3);
            intent.putExtra(Constants.FIELD4, num4);
            startActivity(intent);
        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int num1 = intent.getIntExtra(Constants.FIELD1, 0);
            int num2 = intent.getIntExtra(Constants.FIELD2, 0);
            int num3 = intent.getIntExtra(Constants.FIELD3, 0);
            int num4 = intent.getIntExtra(Constants.FIELD4, 0);

            Log.d(Constants.TAG, "main received");

            num1_text.setText(Integer.toString(num1));
            num2_text.setText(Integer.toString(num2));
            num3_text.setText(Integer.toString(num3));
            num4_text.setText(Integer.toString(num4));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);

        set_button = (Button)findViewById(R.id.set_button);
        ButtonClickListener listener = new ButtonClickListener();
        set_button.setOnClickListener(listener);

        num1_text = (EditText)findViewById(R.id.num1);
        num2_text = (EditText)findViewById(R.id.num2);
        num3_text = (EditText)findViewById(R.id.num3);
        num4_text = (EditText)findViewById(R.id.num4);

        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
        getApplicationContext().startService(intent);
        this.service_started = true;
        Log.d(Constants.TAG, "started service");

        intentFilter.addAction(Constants.SET_TEXT);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.FIELD1, num1_text.getText().toString());
        savedInstanceState.putString(Constants.FIELD2, num2_text.getText().toString());
        savedInstanceState.putString(Constants.FIELD3, num3_text.getText().toString());
        savedInstanceState.putString(Constants.FIELD4, num4_text.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        num1_text.setText(savedInstanceState.getString(Constants.FIELD1, "0"));
        num2_text.setText(savedInstanceState.getString(Constants.FIELD2, "0"));
        num3_text.setText(savedInstanceState.getString(Constants.FIELD3, "0"));
        num4_text.setText(savedInstanceState.getString(Constants.FIELD4, "0"));
    }

    protected void onDestroy() {
        if (this.service_started) {
            Intent intent = new Intent(this, PracticalTest01Var07Service.class);
            stopService(intent);
        }
        super.onDestroy();
    }
}
