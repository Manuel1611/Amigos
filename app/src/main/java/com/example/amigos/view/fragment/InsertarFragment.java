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

public class InsertarFragment extends Fragment {

    private AmigosViewModel amigosViewModel;
    private TextInputEditText etNombre, etTelefono, etFechaNac;

    public InsertarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insertar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btAtras = view.findViewById(R.id.btAtrasInsertar);
        etNombre = view.findViewById(R.id.etNombreInsertar);
        etTelefono = view.findViewById(R.id.etTelefonoInsertar);
        etFechaNac = view.findViewById(R.id.etFechaNacInsertar);
        Button btAdd = view.findViewById(R.id.btAddAmigoFromInsert);

        final NavController navController = Navigation.findNavController(view);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_from_insertar_to_main);
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

                if (TextUtils.isEmpty(etNombre.getText()) || TextUtils.isEmpty(etTelefono.getText())) {
                    Snackbar.make(btAdd, "El nombre y teléfono son obligatorios", Snackbar.LENGTH_SHORT).show();

                } else if(ValidarDatos.validarNombre(etNombre.getText().toString())) {
                    Snackbar.make(btAdd, "El nombre introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNombre.setText("");

                } else if(!ValidarDatos.validarTelefono(etTelefono.getText().toString())) {
                    Snackbar.make(btAdd, "El teléfono introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etTelefono.setText("");

                } else {

                    if(etFechaNac.getText().toString().matches("")) {

                        String nombre = etNombre.getText().toString();
                        String telefono = etTelefono.getText().toString();

                        Amigos amigos = new Amigos(nombre, telefono);

                        amigosViewModel.insert(amigos);

                        NavHostFragment.findNavController(InsertarFragment.this).navigate(R.id.action_from_insertar_to_main);
                    } else {

                        if(!ValidarDatos.validarFecha(etFechaNac.getText().toString())) {
                            Snackbar.make(btAdd, "La fecha de nacimiento no es válida", Snackbar.LENGTH_SHORT).show();
                            etFechaNac.setText("");
                        } else {

                            String nombre = etNombre.getText().toString();
                            String telefono = etTelefono.getText().toString();
                            String fechaNac = etFechaNac.getText().toString();

                            Amigos amigos = new Amigos(nombre, fechaNac, telefono);

                            amigosViewModel.insert(amigos);

                            NavHostFragment.findNavController(InsertarFragment.this).navigate(R.id.action_from_insertar_to_main);
                        }
                    }
                }
            }
        });
    }
}