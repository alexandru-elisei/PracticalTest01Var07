package practicaltest01var07.eim.systems.cs.pub.ro.practicaltest01var07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var07SecondaryActivity extends AppCompatActivity {

    Button sum_button, product_button;
    EditText num1_text, num2_text, num3_text, num4_text;
    TextView result_text;

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

            switch (view.getId()) {
                case R.id.sum_button:
                    int sum = 0;
                    sum = num1 + num2 + num3 + num3;
                    Toast.makeText(getApplicationContext(), ""+sum, Toast.LENGTH_LONG).show();
                    break;
                case R.id.product_button:
                    int prod = 1;
                    prod = num1 * num2 * num3 * num3;
                    Toast.makeText(getApplicationContext(), ""+prod, Toast.LENGTH_LONG).show();
                    break;
            }

            Log.d(Constants.TAG, "" + num1 + " " + num2 + " " + num3 + " " + num4);

            /*
            if (leftNumberOfClicks + rightNumberOfClicks > Constants.NUMBER_OF_CLICKS_THRESHOLD
                    && serviceStatus == Constants.SERVICE_STOPPED) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Service.class);
                intent.putExtra(Constants.FIRST_NUMBER, leftNumberOfClicks);
                intent.putExtra(Constants.SECOND_NUMBER, rightNumberOfClicks);
                getApplicationContext().startService(intent);
                serviceStatus = Constants.SERVICE_STARTED;
            }
            */
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);

        sum_button = (Button)findViewById(R.id.sum_button);
        product_button = (Button)findViewById(R.id.product_button);
        ButtonClickListener listener = new PracticalTest01Var07SecondaryActivity.ButtonClickListener();
        sum_button.setOnClickListener(listener);
        product_button.setOnClickListener(listener);

        num1_text = (EditText)findViewById(R.id.num1);
        num2_text = (EditText)findViewById(R.id.num2);
        num3_text = (EditText)findViewById(R.id.num3);
        num4_text = (EditText)findViewById(R.id.num4);

        result_text = (TextView)findViewById(R.id.result);

        Intent intent = getIntent();
        if (intent != null) {
            int num1 = intent.getIntExtra(Constants.FIELD1, 0);
            int num2= intent.getIntExtra(Constants.FIELD2, 0);
            int num3 = intent.getIntExtra(Constants.FIELD3, 0);
            int num4 = intent.getIntExtra(Constants.FIELD4, 0);

            num1_text.setText(""+num1);
            num2_text.setText(""+num2);
            num3_text.setText(""+num3);
            num4_text.setText(""+num4);
        }
    }



}
