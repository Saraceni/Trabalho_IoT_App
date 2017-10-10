package com.app.bluetoothcontroller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.bluetoothcontroller.bluetooth.BluetoothActivityExtension;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends BluetoothActivityExtension {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private View ledView;
    private String lastState = "0";

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.pesquisa_disp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisarDispositivos();
            }
        });

        ledView = findViewById(R.id.led_view);

    }

    @Override
    public void onRecebeMsg(String msg){
        Log.i(TAG, "Recebeu " + msg);
        if(!msg.equals(lastState)) {
            lastState = msg;
            if (msg.equals("1")) {
                ledView.setBackgroundColor(Color.RED);
            } else if (msg.equals("0")) {
                ledView.setBackgroundColor(Color.DKGRAY);
            }

            try {
                String state = msg.equals("1") ? "Ligado" : "Desligado";
                post("https://fierce-island-95774.herokuapp.com/newData", String.format("{ \"status\": \"%s\" }", state));
            } catch (Exception exc) {
                Log.e(TAG, exc.getMessage());
            }
        }
    }

    private void post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().toString());
            }
        });
    }
}
