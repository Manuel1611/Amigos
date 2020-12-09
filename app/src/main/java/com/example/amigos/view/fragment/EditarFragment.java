package com.example.amigos.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.validar.ValidarDatos;
import com.example.amigos.viewmodel.AmigosViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class EditarFragment extends Fragment {

    private AmigosViewModel amigosViewModel;
    private TextInputEditText etNombre, etFechaNac;

    public EditarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

        Amigos amigos = amigosViewModel.getAmigos();

        ImageButton btAtras = view.findViewById(R.id.btAtrasEditar);
        etNombre = view.findViewById(R.id.etNombreEditar);
        etFechaNac = view.findViewById(R.id.etFechaNacEditar);
        Button btEditar = view.findViewById(R.id.btEditarAmigo);

        etNombre.setText(amigos.getNombre());

        if(amigos.getFechaNac().equals("No especificado")) {
            etFechaNac.setText(null);
        } else {
            etFechaNac.setText(amigos.getFechaNac());
        }

        final NavController navController = Navigation.findNavController(view);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_from_editar_to_ver);
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etNombre.getText())) {
                    Snackbar.make(btEditar, "El nombre es obligatorio", Snackbar.LENGTH_SHORT).show();

                } else if(ValidarDatos.validarNombre(etNombre.getText().toString())) {
                    Snackbar.make(btEditar, "El nombre introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNombre.setText("");

                } else {

                    if(etFechaNac.getText().toString().matches("")) {

                        String nombre = etNombre.getText().toString();

                        amigos.setNombre(nombre);

                        amigosViewModel.update(amigos);

                        NavHostFragment.findNavController(EditarFragment.this).navigate(R.id.action_from_editar_to_ver);

                    } else {

                        if(!ValidarDatos.validarFecha(etFechaNac.getText().toString())) {
                            Snackbar.make(btEditar, "La fecha de nacimiento no es válida", Snackbar.LENGTH_SHORT).show();
                            etFechaNac.setText("");
                        } else {

                            String nombre = etNombre.getText().toString();
                            String fechaNac = etFechaNac.getText().toString();

                            amigos.setNombre(nombre);
                            amigos.setFechaNac(fechaNac);

                            amigosViewModel.update(amigos);

                            NavHostFragment.findNavController(EditarFragment.this).navigate(R.id.action_from_editar_to_ver);

                        }
                    }
                }
            }
        });
    }
}