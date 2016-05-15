package com.filipkesteli.internalstoragetutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button b1, b2;
    TextView tv;
    EditText ed1;
    private String data;
    private String file = "mydata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListener();
    }

    private void initWidgets() {
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);

        ed1 = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
    }

    private void setupListener() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data = ed1.getText().toString();
                FileOutputStream fOut = null;
                try {
                    fOut = openFileOutput(file, MODE_WORLD_READABLE);
                    fOut.write(data.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fOut.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = openFileInput(file);
                    int c;
                    String temp = "";

                    while ((c = fileInputStream.read()) != -1) {
                        temp = temp + Character.toString((char) c);
                    }
                    tv.setText(temp);
                    Toast.makeText(getBaseContext(), "file read", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
