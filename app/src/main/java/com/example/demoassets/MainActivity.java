package com.example.demoassets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView ivDemo;
    Button btnGetData;
    TextView tvDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        ivDemo = findViewById(R.id.iv_demo);
        btnGetData = findViewById(R.id.btn_get_data);
        tvDemo = findViewById(R.id.tv_demo);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromAssets();
            }
        });
    }

    private void getDataFromAssets() {
        AssetManager assetManager = getAssets();

        try {
            InputStream input = assetManager.open("ic_hocap.png");
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            ivDemo.setImageBitmap(bitmap);
            input.close();

            input = assetManager.open("demo.txt");
            String result = "";
            int len;
            byte buff[] = new byte[1024];
            while ((len = input.read(buff)) > 0) {
                result += new String(buff, 0, len);
            }
            input.close();
            tvDemo.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}