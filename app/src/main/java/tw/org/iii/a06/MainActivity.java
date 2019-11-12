package tw.org.iii.a06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView clock;
    private ListView lapList;
    private Button btnLeft, btnRight;
    private boolean isRunning; //布林值預設值為false
    private UIHandler uiHandler;

    private Timer timer;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clock = findViewById(R.id.clock);
        lapList = findViewById(R.id.lapList);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);

        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");

        Log.v("az","start");
        uiHandler = new UIHandler();
        timer = new Timer();
        timer.schedule(new MyTask(), 0,10);  //時間單位為千分之一秒
    }

    //不做無意義的運行
    @Override
    public  void finish(){
        if(timer != null){
            timer.cancel();
            timer.purge();
            timer=null;
        }
    }
    private class MyTask extends  TimerTask {
        @Override
        public void run() {
            if (isRunning) {
                i++;
                Log.v("az", "i=" + i);
                uiHandler.sendEmptyMessage(0);

                //clock.setText(" "+i);}
            }
        }
    }

    private class UIHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg)
        {
            super.handleMessage(msg);
            clock.setText(toClockString());
        }
    }

    private String toClockString(){
        int hs = i % 100; //i除以100的餘數即hs
        int ts = i /100;  //總秒數
        int h = ts/(60*60); //h = 60*60
        int m = (ts - h*60*60)/60; //扣除時數後/60
        int s = ts%60;  //分鐘為總秒數/60的餘數
        return h+":"+ m+":"+s+":"+hs;
    }
    public void clickLeft(View view) {
        if (isRunning){
            //lap
        }else{
            //reset
        }
    }


    private void doLap(){

    }
    private void doReset(){

    }



    public void clickRight(View view) {
        isRunning = !isRunning;
        btnLeft.setText(isRunning?"Lap":"Reset");
        btnRight.setText(isRunning?"Stop":"Start");
    }
}
