package com.example.carteira.util;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.carteira.R;
import com.example.carteira.adapter.LancamentoAdapter;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Lancamento;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LancamentosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LancamentosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LancamentosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LancamentosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LancamentosFragment newInstance(String param1, String param2) {
        LancamentosFragment fragment = new LancamentosFragment();
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
        // Inflar o layout do fragmento
        View view = inflater.inflate(R.layout.fragment_lancamentos, container, false);

        // Pegar a ListView
        ListView listView = view.findViewById(R.id.listViewLancamentos);

        // Obter os lançamentos do DAO
        LancamentoDAO lancamentoDAO = new LancamentoDAO(getContext());
        List<Lancamento> lancamentos = lancamentoDAO.listarLancamentos();

        Activity activity = getActivity();
        LancamentoAdapter lancamentosAdapter = new LancamentoAdapter(lancamentos, activity);
        listView.setAdapter(lancamentosAdapter);

        return view;
    }

}