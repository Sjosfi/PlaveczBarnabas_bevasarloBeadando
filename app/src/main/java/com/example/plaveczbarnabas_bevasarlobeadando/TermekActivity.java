package com.example.bevasarloapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermekActivity extends AppCompatActivity {

    private EditText nevEditText, egysegarEditText, mennyisegEditText, mertekegysegEditText;
    private Button modositasButton, torlesButton, megseButton;
    private int termekId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termek);

        nevEditText = findViewById(R.id.nevEditText);
        egysegarEditText = findViewById(R.id.egysegarEditText);
        mennyisegEditText = findViewById(R.id.mennyisegEditText);
        mertekegysegEditText = findViewById(R.id.mertekegysegEditText);

        modositasButton = findViewById(R.id.modositasButton);
        torlesButton = findViewById(R.id.torlesButton);
        megseButton = findViewById(R.id.megseButton);

        termekId = getIntent().getIntExtra("termekId", -1);
        betoltTermekAdatok();

        modositasButton.setOnClickListener(v -> modositas());
        torlesButton.setOnClickListener(v -> torles());
        megseButton.setOnClickListener(v -> finish());
    }

    private void betoltTermekAdatok() {
        RetrofitInstance.getApi().getAllTermekek().enqueue(new Callback<List<Termekek>>() {
            @Override
            public void onResponse(Call<List<Termekek>> call, Response<List<Termekek>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Termekek termek : response.body()) {
                        if (termek.getId() == termekId) {
                            nevEditText.setText(termek.getNev());
                            egysegarEditText.setText(String.valueOf(termek.getEgységár()));
                            mennyisegEditText.setText(String.valueOf(termek.getMennyiseg()));
                            mertekegysegEditText.setText(termek.getMertekegyseg());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Termekek>> call, Throwable t) {
                Toast.makeText(TermekActivity.this, "Nem sikerült betölteni az adatokat.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void modositas() {
    }

    private void torles() {
    }
}
