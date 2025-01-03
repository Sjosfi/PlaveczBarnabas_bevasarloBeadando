import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nevEditText = findViewById(R.id.nevEditText);
        EditText egysegarEditText = findViewById(R.id.egysegarEditText);
        EditText mennyisegEditText = findViewById(R.id.mennyisegEditText);
        EditText mertekegysegEditText = findViewById(R.id.mertekegysegEditText);
        Button hozzaadasButton = findViewById(R.id.hozzaadasButton);

        hozzaadasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nev = nevEditText.getText().toString();
                String egysegarStr = egysegarEditText.getText().toString();
                String mennyisegStr = mennyisegEditText.getText().toString();
                String mertekegyseg = mertekegysegEditText.getText().toString();

                if (nev.isEmpty() || egysegarStr.isEmpty() || mennyisegStr.isEmpty() || mertekegyseg.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Minden mezőt helyesen tölts ki!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int egysegar;
                double mennyiseg;
                try {
                    egysegar = Integer.parseInt(egysegarStr);
                    mennyiseg = Double.parseDouble(mennyisegStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Hibás formátum!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Termekek termek = new Termekek(nev, egysegar, mennyiseg, mertekegyseg);

                RetrofitInstance.getApi().createTermek(termek).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(MainActivity.this, "Termék hozzáadva!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Hiba történt: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button listActivityButton = findViewById(R.id.listActivityButton);
        listActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        });
    }
}