package com.example.ninjawarrior;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btJugar, btPuntuacio, btSalir;
    private Toolbar menu;
    private Music music;
    private static String nickname;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences pref;
    private ArrayList<String> listNamePrueba = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        lisenerButton();
        if(MainActivity.getPrefMusic()){
            music.playMusic(this, music.backgroundMain());
        }
    }

    private void init() {
        music = Music.getInstance();
        btJugar = findViewById(R.id.btJugar);
        btPuntuacio = findViewById(R.id.btPuntuacio);
        btSalir = findViewById(R.id.btSalir);
        menu = findViewById(R.id.toolbar);
        setSupportActionBar(menu);
        sharedPreferences = getSharedPreferences("ninja_warrior_preference", Context.MODE_PRIVATE);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void getNamePlayer() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Nombre jugador");
        alert.setMessage("Por favor introduzca su nombre");
        final EditText input = new EditText(this);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                nickname = input.getText().toString();
            }
        });
        alert.show();
    }

    private void listPuntuacio() {
        listNamePrueba = recogerNickname();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNamePrueba);
        final ListView listPuntuacio = new ListView(this);
        listPuntuacio.setAdapter(adapter);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Game Score");
        alert.setView(listPuntuacio);
        alert.show();
    }

    private void lisenerButton() {
        btJugar.setOnClickListener(v -> startGame());
        btPuntuacio.setOnClickListener(v -> listPuntuacio());
        btSalir.setOnClickListener(v -> endProgram());
    }

    private void endProgram() {
        finish();
    }

    private ArrayList<String> recogerNickname() {
        ArrayList<String> listNickname = new ArrayList<>();
        Map<String, ?> claves = sharedPreferences.getAll();
        for (Map.Entry<String, ?> ele : claves.entrySet()) {
            listNickname.add(ele.getKey() + " " + ele.getValue().toString());
        }
        return listNickname;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, PreferencesController.class);
            startActivity(i);
            return true; // Cambiado a true para indicar que el evento fue manejado
        }
        return false;
    }

    public void startGame() {
        getNamePlayer();
        startActivity(new Intent(this, JocActivity.class));
    }

    public static String getPrefNinja() {
        if (pref != null) {
            return pref.getString("ninjaList", "0");
        }
        return "0";
    }

    public static String getPrefNumObjetivos() {
        //if (pref != null) {
            //return pref.getString("numObjetivos", "3");
        //}
        return "3";
    }

    public static boolean getPrefMusic() {
        if (pref != null) {
            return pref.getBoolean("cbMusic", true);
        }
        return true;
    }

    public static String getNickname() {
        return nickname;
    }


}
