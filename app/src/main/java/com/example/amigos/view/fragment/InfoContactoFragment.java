package com.example.amigos.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.viewmodel.AmigosViewModel;
import com.squareup.picasso.Picasso;

public class InfoContactoFragment extends Fragment {

    Contacto contacto;

    public InfoContactoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btAtras = view.findViewById(R.id.btAtrasInfoContactos);

        final NavController navController = Navigation.findNavController(view);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_from_info_to_contactos);
            }
        });

        AmigosViewModel amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

        contacto = amigosViewModel.getContacto();

        ImageView imagen = view.findViewById(R.id.imgContactoInfo);
        TextView tvNombre = view.findViewById(R.id.tvNombreContactoInfo);
        TextView tvTelefono = view.findViewById(R.id.tvTelefonoContactoInfo);

        if(contacto.getImagen() != null) {
            Picasso.get().load(contacto.getImagen()).into(imagen);
        } else {
            imagen.setImageResource(R.drawable.noimagen);
        }

        tvNombre.setText(contacto.getNombre());
        tvTelefono.setText(contacto.getTelefono());

        Button btAdd = view.findViewById(R.id.btAddAmigoFromContact);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amigos amigos = new Amigos(tvNombre.getText().toString(), tvTelefono.getText().toString());
                amigosViewModel.insert(amigos);

                NavHostFragment.findNavController(InfoContactoFragment.this).navigate(R.id.action_from_info_to_main);
            }
        });
    }
}