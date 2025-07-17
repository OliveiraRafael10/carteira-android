package com.example.carteira.util;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carteira.R;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Lancamento;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RelatorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RelatorioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RelatorioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment relatorio.
     */
    // TODO: Rename and change types and number of parameters
    public static RelatorioFragment newInstance(String param1, String param2) {
        RelatorioFragment fragment = new RelatorioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relatorio, container, false);

        TextView txtSaldoEntradas = view.findViewById(R.id.txtSaldoEntradas);
        TextView txtSaldoSaidas = view.findViewById(R.id.txtSaldoSaidas);
        TextView txtSaldoAtual = view.findViewById(R.id.txtTotalSaldoAtual);
        Double entradas = 0.0;
        Double saidas = 0.0;
        Double saldo = 0.0;

        LancamentoDAO lancamentoDAO = new LancamentoDAO(getContext());
        List<Lancamento> lancamentos = lancamentoDAO.listarLancamentos();

        for (Lancamento lancamento : lancamentos) {
            String tipo = lancamento.getTipo().trim().toLowerCase();

            if (tipo.equals("entrada")) {
                entradas += lancamento.getValor();
            } else if (tipo.equals("saída")) {
                saidas += lancamento.getValor();
            }
        }

        saldo = entradas - saidas;

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        txtSaldoEntradas.setText(nf.format(entradas));
        txtSaldoSaidas.setText(nf.format(saidas));
        txtSaldoAtual.setText(nf.format(saldo));

        return view;
    }
}