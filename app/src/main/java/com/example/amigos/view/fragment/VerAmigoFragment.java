package com.example.amigos.view.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.AmigosLlamadas;
import com.example.amigos.viewmodel.AmigosViewModel;

public class VerAmigoFragment extends Fragment {

    AmigosViewModel amigosViewModel;
    Amigos amigos;
    private long cont;
    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvFechaNac;

    public VerAmigoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ver_amigo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btAtras = view.findViewById(R.id.btAtrasVerAmigo);

        final NavController navController = Navigation.findNavController(view);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_from_ver_to_main);
            }
        });

        amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

        amigos = amigosViewModel.getAmigos();
        cont = amigosViewModel.getCont();

        tvNombre = view.findViewById(R.id.tvNombreVer);
        tvTelefono = view.findViewById(R.id.tvTelefonoVer);
        tvFechaNac = view.findViewById(R.id.tvFechaNacVer);
        TextView tvNumeroLlamadas = view.findViewById(R.id.tvNumeroLlamadasVer);

        tvNombre.setText(amigos.getNombre());
        tvTelefono.setText(amigos.getTelefono());

        if(!(amigos.getFechaNac().matches(""))) {
            tvFechaNac.setText(amigos.getFechaNac());
        } else {
            tvFechaNac.setText(R.string.fecha_no_especificada);
        }

        tvNumeroLlamadas.setText("Llamadas: " + cont);

        long id = amigos.getId();

        Button btEditar = view.findViewById(R.id.btEditar);
        Button btBorrar = view.findViewById(R.id.btBorrar);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar(id, view);
            }
        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrar(id, view);
            }
        });

    }

    private void editar(long id, View view) {

        final NavController navController = Navigation.findNavController(view);

        Amigos ami = new Amigos(tvNombre.getText().toString(), tvFechaNac.getText().toString(), tvTelefono.getText().toString());
        ami.setId(id);
        amigosViewModel.setAmigos(ami);

        navController.navigate(R.id.action_from_ver_to_editar);

    }

    private void borrar(long id, View view) {

        final NavController navController = Navigation.findNavController(view);

        Dialog dialog = new Dialog(getContext());

        dialog.setContentView(R.layout.borrar_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton btCerrarDialog = dialog.findViewById(R.id.ivCerrarBorrar);
        Button btConfirmarBorrar = dialog.findViewById(R.id.btConfirmarBorrar);
        dialog.show();

        btCerrarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        btConfirmarBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amigosViewModel.delete(id);

                dialog.dismiss();

                navController.navigate(R.id.action_from_ver_to_main);

            }
        });
    }
}