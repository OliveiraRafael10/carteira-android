package com.example.carteira;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carteira.adapter.LancamentoAdapter;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView ivLancamento = findViewById(R.id.ivLancamento);
        ivLancamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLancamento = new Intent(HomeActivity.this, LancamentoActivity.class);
                startActivity(telaLancamento);
            }
        });

        ImageView ivRelatorio = findViewById(R.id.imageView3);
        ivRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRelatorio();
            }
        });

        loadLancamentos();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadLancamentos();
    }

    private void loadLancamentos() {
        LancamentoDAO lancamentoDAO = new LancamentoDAO(this);
        List<Lancamento> lancamentos = lancamentoDAO.listarLancamentos();
        LancamentoAdapter lancamentosAdapter = new LancamentoAdapter(lancamentos, HomeActivity.this);

        ListView listViewLancamentos = findViewById(R.id.listViewLancamentos);
        listViewLancamentos.setAdapter(lancamentosAdapter);
    }

    private void mostrarRelatorio() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_relatorio, null);

        // Pega os TextViews do layout
        TextView entradasView = view.findViewById(R.id.txtSaldoEntradas);
        TextView saidasView = view.findViewById(R.id.txtSaldoSaidas);
        TextView saldoView = view.findViewById(R.id.txtTotalSaldoAtual);

        // Pega os dados do banco
        LancamentoDAO dao = new LancamentoDAO(this);
        double totalEntradas = dao.buscarTotalPorTipo("Entrada");
        double totalSaidas = dao.buscarTotalPorTipo("Saída");
        double saldo = totalEntradas - totalSaidas;

        // Formata os valores
        entradasView.setText(String.format("R$ %.2f", totalEntradas));
        saidasView.setText(String.format("R$ %.2f", totalSaidas));
        saldoView.setText(String.format("R$ %.2f", saldo));

        // Cria o dialog
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false) // impede fechar tocando fora
                .setPositiveButton("Fechar", null)
                .create();

        ImageButton btnNovo = view.findViewById(R.id.btnNovoLançamento);
        ImageButton btnMenu = view.findViewById(R.id.btnMenuInicial);

        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LancamentoActivity.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fecha o AlertDialog
                dialog.dismiss();
            }
        });

        // Mostra o dialog
        dialog.show();

    }

}