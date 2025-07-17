package com.example.carteira.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carteira.R;
import com.example.carteira.model.Lancamento;

import java.util.List;

public class LancamentoAdapter extends BaseAdapter {

    private final List<Lancamento> lancamentos;
    private final Activity activity;

    public LancamentoAdapter(List<Lancamento> lancamentos, Activity activity) {
        this.lancamentos = lancamentos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lancamentos.size();
    }

    @Override
    public Object getItem(int i) {
        return lancamentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        // Reutiliza a view existente, se possível
        if (convertView != null) {
            view = convertView;
        } else {
            view = activity.getLayoutInflater().inflate(R.layout.lista_lancamentos, parent, false);
        }

        // Recupera o lançamento
        Lancamento lancamento = lancamentos.get(position);

        // Preenche os campos da view
        TextView descricao = view.findViewById(R.id.textViewLancamentoDescricaoLista);
        descricao.setText(lancamento.getDescricao());

        TextView categoria = view.findViewById(R.id.textViewLancamentoCategoriaLista);
        categoria.setText(lancamento.getCategoria());

        TextView valor = view.findViewById(R.id.textViewLancamentoValorLista);
        valor.setText(String.valueOf(lancamento.showValor()));

        // Muda a cor do valor baseado no tipo
        String tipo = lancamento.getTipo().trim().toLowerCase();

        if (tipo.equals("entrada")) {
            valor.setTextColor(activity.getResources().getColor(R.color.green));
        } else if (tipo.equals("saída")) {
            valor.setTextColor(activity.getResources().getColor(R.color.red));
        }

        TextView data = view.findViewById(R.id.textViewLancamentoDataLista);
        data.setText(lancamento.showDataFormatada());

        return view;
    }

}
