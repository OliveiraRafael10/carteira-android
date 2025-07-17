package com.example.carteira;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.util.CadastrarLancamentoFragment;
import com.example.carteira.util.Constantes;
import com.example.carteira.util.LancamentosFragment;
import com.example.carteira.util.RelatorioFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        ImageView ivListar = findViewById(R.id.ivListar);
        ImageView ivCadastrar = findViewById(R.id.ivCadastrar);
        ImageView ivRelatorio = findViewById(R.id.ivRelatorio);
        TextView txtTitulo = findViewById(R.id.txtTitulo);

        ivListar.setOnClickListener(v -> {
            // Troca fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, new LancamentosFragment())
                    .commit();

            //Atualiza o titulo
            txtTitulo.setText("Lançamentos");

            // Atualiza imagens
            ivListar.setImageResource(R.drawable.currency_exchange_activated);
            ivCadastrar.setImageResource(R.drawable.money_bag);
            ivRelatorio.setImageResource(R.drawable.summarize);
        });

        ivCadastrar.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, new CadastrarLancamentoFragment())
                    .commit();

            //Atualiza o titulo
            txtTitulo.setText("Cadastar lançamento");

            ivListar.setImageResource(R.drawable.currency);
            ivCadastrar.setImageResource(R.drawable.money_bag_activated);
            ivRelatorio.setImageResource(R.drawable.summarize);
        });

        ivRelatorio.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_fragment, new RelatorioFragment())
                    .commit();

            //Atualiza o titulo
            txtTitulo.setText("Relatório");

            ivListar.setImageResource(R.drawable.currency);
            ivCadastrar.setImageResource(R.drawable.money_bag);
            ivRelatorio.setImageResource(R.drawable.summarize_activated);
        });
    }
}