package gdut.bsx.netmonitor.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import gdut.bsx.netmonitor.NetChangeObserver;
import gdut.bsx.netmonitor.NetMonitor;

public class MainActivity extends AppCompatActivity implements NetChangeObserver, View.OnClickListener {

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_register).setOnClickListener(this);
        findViewById(R.id.bt_unregister).setOnClickListener(this);
        findViewById(R.id.bt_check_network).setOnClickListener(this);
        tvInfo = findViewById(R.id.tv_info);
    }

    @Override
    public void onNetConnected() {
        tvInfo.setText("网络已连接");
        Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetDisConnect() {
        tvInfo.setText("网络连接已断开");
        Toast.makeText(this, "网络连接已断开", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                NetMonitor.getInstance().registerObserver(this);
                break;
            case R.id.bt_unregister:
                NetMonitor.getInstance().removeObserver(this);
                break;
            case R.id.bt_check_network:
                NetMonitor.getInstance().checkNetworkState(this);
                break;
        }
    }
}
