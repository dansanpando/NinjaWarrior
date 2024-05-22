package com.example.ninjawarrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

public class JocActivity extends AppCompatActivity {

    private static SharedPreferences sharedPreferences;
    private static Integer contador = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joc);

    }

    public static void dialegFinal(final JocActivity activity) {
        AlertDialog.Builder alertaBuilder = new AlertDialog.Builder(activity);
        final TextView tvResult = new TextView(activity);
        tvResult.setText(MainActivity.getNickname() + ": " + contador * 10 + " punts");
        tvResult.setTextSize(24);
        tvResult.setGravity(Gravity.CENTER);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tvResult.setLayoutParams(layoutParams);
        int padding = 50;
        tvResult.setPadding(padding, padding, padding, padding);

        alertaBuilder.setView(tvResult);
        alertaBuilder.setPositiveButton("Tornar al menu",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        activity.finish();
                    }
                });
        AlertDialog alertaDialog = alertaBuilder.create();
        alertaDialog.show();
    }

    public static void setContador(Integer contador) {
        JocActivity.contador = contador;
    }

    public static Integer getContador() {
        return contador;
    }
}