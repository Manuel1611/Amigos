package com.example.amigos.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.AmigosLlamadas;
import com.example.amigos.view.adapter.AmigosRecyclerAdapter;
import com.example.amigos.viewmodel.AmigosViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private AmigosViewModel amigosViewModel;
    private RecyclerView recyclerView;
    List<AmigosLlamadas> listaAmigosLlamadas;
    AmigosRecyclerAdapter adapter;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fabInsertar = view.findViewById(R.id.fabInsertar);

        fabInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_from_main_to_insertar);
            }
        });

        amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerAmigos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listaAmigosLlamadas = new ArrayList<>();

        adapter = new AmigosRecyclerAdapter(listaAmigosLlamadas, getActivity(), view);
        recyclerView.setAdapter(adapter);

        amigosViewModel.getLiveAmigosLlamadasList().observe(getActivity(), new Observer<List<AmigosLlamadas>>() {
            @Override
            public void onChanged(List<AmigosLlamadas> amigosLlamadas) {
                listaAmigosLlamadas.clear();
                listaAmigosLlamadas.addAll(amigosLlamadas);
                adapter.notifyDataSetChanged();
            }
        });

        Button btImportar = view.findViewById(R.id.btImportar);

        btImportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_from_main_to_contactos);
            }
        });
    }
}