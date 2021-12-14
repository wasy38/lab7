package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String fileName = "content.txt";
    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonSaveClick(View view) {
        try {
            EditText textBox = (EditText) findViewById(R.id.save_text);
            String text = textBox.getText().toString();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Текстовый файл успешно сохранён!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Файл не найден!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Ошибка сохранения файла!", Toast.LENGTH_SHORT).show();

        }
    }

    public void buttonOpenClick(View view) {
        try
        {
            FileInputStream fin = new FileInputStream(file);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            TextView textView = (TextView) findViewById(R.id.open_text);
            textView.setText(text);
            fin.close();
        } catch (IOException ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}