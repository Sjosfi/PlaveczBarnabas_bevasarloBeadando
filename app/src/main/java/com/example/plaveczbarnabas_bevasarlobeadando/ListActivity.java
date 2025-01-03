import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = findViewById(R.id.listView);

        RetrofitInstance.getApi().getTermekek().enqueue(new Callback<List<Termekek>>() {
            @Override
            public void onResponse(Call<List<Termekek>> call, Response<List<Termekek>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Termekek> termekek = response.body();
                    List<String> termekekList = new ArrayList<>();

                    for (Termekek termek : termekek) {
                        termekekList.add(termek.getNev() + "\nEgységár: " + termek.getEgysegar() + " Ft\nMennyiség: " + termek.getMennyiseg() + " " + termek.getMertekegyseg() + "\nBruttó ár: " + termek.getBruttoAr() + " Ft");
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivity.this, android.R.layout.simple_list_item_1, termekekList);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(ListActivity.this, TermekActivity.class);
                        intent.putExtra("termekId", termekek.get(position).getId());
                        startActivity(intent);
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Termekek>> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Hiba történt: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}