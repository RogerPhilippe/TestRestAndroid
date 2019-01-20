package br.com.philippesis.testrest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

import br.com.philippesis.testrest.Tasks.AuthTask;
import br.com.philippesis.testrest.utils.AsyncResponse;
import br.com.philippesis.testrest.utils.TimerTaskService;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private AuthTask authTask;

    private String mResult = null;

    private TextView tvResult;

    private long mExpirationTime = 30;

    private TimerTaskService timerTaskService;

    private Timer timerObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.idtvResult);

        Button btnStart = findViewById(R.id.idbtnStart);

        // Botão capturar Token
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("Time Current", String.valueOf(System.currentTimeMillis()));

                if (timerTaskService == null || timerTaskService.getmTimer() == 0) {

                    // Objetos para incrementar rotina de verificação da Expiration Time
                    timerTaskService = new TimerTaskService(mExpirationTime);
                    timerObj = new Timer();
                    timerObj.schedule(timerTaskService, 0, 1000);

                    authTask = new AuthTask(MainActivity.this);

                    authTask.delegate = MainActivity.this;

                    try {
                        authTask.execute("Admin", "admin");
                    } catch (Exception er) {
                        Log.e("Error", er.toString());
                    }

                    Log.i("AsyncTask", "AsyncTask sendo chamado Thread: " +
                            Thread.currentThread().getName());

                }

            }
        });

    }

    //this override the implemented method from asyncTask
    @Override
    public void processFinish(String result){
        //Here you will receive the result fired from async class
        //of onPostExecute(result) method.
        this.mResult = result;
        if(result != null) {
            tvResult.setText("Result: "+result);
        }
    }


}
