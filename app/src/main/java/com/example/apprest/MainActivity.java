package com.example.apprest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText multiline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiline = findViewById(R.id.listaMultiline);
    }

    public void btnEnviar(View view) {

        getRetrofit();
    }

    public void getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api-uat.kushkipagos.com").addConverterFactory(GsonConverterFactory.create()).build();

        Bank interfazBanck = retrofit.create(Bank.class);
        Call<List<DataBank>> bancos = interfazBanck.getListAll("da84e6bf7dcb4b2d931d88b015de20f7");

        bancos.enqueue(new Callback<List<DataBank>>() {
            @Override
            public void onResponse(Call<List<DataBank>> call, Response<List<DataBank>> response) {
                String cadena = "";
                try {
                    if (response.isSuccessful()) {

                        List<DataBank> lista = response.body();
                        Log.i("Logs", "Length: " + lista.size());

                        for (DataBank data : lista) {
                            cadena += "AGENCIA BANCARIA " + data.getCode() + ":\n " + data.getName() + "\n\n";
                        }
                    }
                    multiline.append(cadena);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DataBank>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"ERROR FATAL..", Toast.LENGTH_SHORT).show();

            }
        });
    }
}