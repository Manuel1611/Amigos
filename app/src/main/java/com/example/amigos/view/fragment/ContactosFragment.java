package com.example.amigos.view.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.view.adapter.ContactosRecyclerAdapter;
import com.example.amigos.viewmodel.AmigosViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContactosFragment extends Fragment {

    private AmigosViewModel amigosViewModel;
    private RecyclerView recyclerView;
    List<Contacto> listaContactos;
    ContactosRecyclerAdapter adapter;

    public ContactosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton btAtras = view.findViewById(R.id.btAtrasContactos);

        final NavController navController = Navigation.findNavController(view);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_from_contactos_to_main);
            }
        });

        amigosViewModel = new ViewModelProvider(getActivity()).get(AmigosViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerContactos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listaContactos = new ArrayList<>();

        adapter = new ContactosRecyclerAdapter(listaContactos, getActivity(), view);
        recyclerView.setAdapter(adapter);
        getContactos();
    }

    private void getContactos() {

        Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            String nombre = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String telefono = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String imagen = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

            Contacto contacto = new Contacto(nombre, telefono, imagen);

            listaContactos.add(contacto);
            adapter.notifyDataSetChanged();
        }

    }

}