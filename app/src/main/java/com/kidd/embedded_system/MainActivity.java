package com.kidd.embedded_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.kidd.embedded_system.presenter.FCMEvent;
import com.kidd.embedded_system.presenter.UpdateFCMPresenter;
import com.kidd.embedded_system.presenter.UpdateFCMPresenterImpl;
import com.kidd.embedded_system.service.TempuratureAndGasChangeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    UpdateFCMPresenter presenter;
    TextView txt_gas;
    TextView txt_temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        txt_gas = findViewById(R.id.txt_gas);
        txt_temp = findViewById(R.id.txt_temp);

        if(getIntent().getExtras()!=null){
            double gas = getIntent().getDoubleExtra("gas",0);
            double temp = getIntent().getDoubleExtra("temp",0);

            txt_gas.setText(String.format("%.2f",gas)+" %");
            txt_temp.setText(String.format("%.2f",temp)+" ºC");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceivedUserAuthChangedEvent(TempuratureAndGasChangeEvent event) {
        String gas = String.format("%.2f",event.getGas());
        String temp = String.format("%.2f",event.getTempurature());

        txt_gas.setText(gas+" %");
        txt_temp.setText(temp+" ºC");
        //finish();
    }

}
