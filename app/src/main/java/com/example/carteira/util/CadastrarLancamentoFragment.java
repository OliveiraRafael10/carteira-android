package com.example.carteira.util;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.carteira.R;
import com.example.carteira.dao.CategoriaDAO;
import com.example.carteira.dao.LancamentoDAO;
import com.example.carteira.model.Categoria;
import com.example.carteira.model.Lancamento;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CadastrarLancamentoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CadastrarLancamentoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Spinner spinnerCategoria;
    private String tipoLancamento;
    private String categoriaSelecionada;
    private CategoriaDAO categoriaDAO;
    private ArrayAdapter<Categoria> categoriaAdapter;

    public CadastrarLancamentoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CadastrarLancamentoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CadastrarLancamentoFragment newInstance(String param1, String param2) {
        CadastrarLancamentoFragment fragment = new CadastrarLancamentoFragment();
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
        View view = inflater.inflate(R.layout.fragment_cadastrar_lancamento, container, false);

        categoriaDAO = new CategoriaDAO(requireContext());

        spinnerCategoria = view.findViewById(R.id.spinnerCategoria);
        ImageButton ibAddCategoria = view.findViewById(R.id.imageButtonAddCategoria);
        Button btCadastrar = view.findViewById(R.id.buttonCadastrar);

        RadioButton rdEntrada = view.findViewById(R.id.radioButtonEntrada);
        RadioButton rdSaida = view.findViewById(R.id.radioButtonSaida);

        EditText etDescricao = view.findViewById(R.id.editTextDescricao);
        EditText etValor = view.findViewById(R.id.editTextValor);

        // Radio Buttons
        rdEntrada.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) tipoLancamento = "Entrada";
        });
        rdSaida.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) tipoLancamento = "Saída";
        });

        // Botão Adicionar Categoria
        ibAddCategoria.setOnClickListener(v -> cadastrarCategoria());

        // Botão Cadastrar Lançamento
        btCadastrar.setOnClickListener(v -> {
            String descricao = etDescricao.getText().toString();
            double valor = Double.parseDouble(etValor.getText().toString());
            Calendar calendar = Calendar.getInstance();
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH) + 1;
            int ano = calendar.get(Calendar.YEAR);

            Lancamento lancamento = new Lancamento(0, categoriaSelecionada, descricao, tipoLancamento, valor, dia, mes, ano);
            LancamentoDAO lDAO = new LancamentoDAO(requireContext());
            lDAO.inserirLancamento(lancamento);

            Toast.makeText(requireContext(), "Lançamento Cadastrado", Toast.LENGTH_SHORT).show();

            // Aqui você pode fazer a troca de fragmento de volta para lista, se quiser
        });

        loadCategorias();

        return view;
    }

    private void loadCategorias() {
        List<Categoria> categorias = categoriaDAO.listarCategorias();
        categoriaAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, categorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(categoriaAdapter);

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaSelecionada = categorias.get(position).toString();
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void cadastrarCategoria() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Cadastrar Categoria");

        EditText etCategoria = new EditText(requireContext());
        etCategoria.setHint("Nome da categoria");

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);
        layout.addView(etCategoria);
        builder.setView(layout);

        builder.setPositiveButton("Cadastrar", (dialog, which) -> {
            String nome = etCategoria.getText().toString();
            if(nome.isEmpty()){
                Toast.makeText(requireContext(), "Erro ! Nome vazio", Toast.LENGTH_SHORT).show();
            }
            else{
                categoriaDAO.inserirCategoria(new Categoria(0, nome));
                Toast.makeText(requireContext(), "Categoria cadastrada com sucesso !", Toast.LENGTH_SHORT).show();
                reloadSpinner();
            }
        });

        builder.show();
    }

    private void reloadSpinner() {
        categoriaAdapter.clear();
        categoriaAdapter.addAll(categoriaDAO.listarCategorias());
        categoriaAdapter.notifyDataSetChanged();
    }
}