package com.example.amigos.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.viewmodel.AmigosViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactosRecyclerAdapter extends RecyclerView.Adapter<ContactosRecyclerAdapter.ContactosViewHolder> {

    AmigosViewModel amigosViewModel;
    Activity activity;
    List<Contacto> listaContactos;
    View view;

    public ContactosRecyclerAdapter(List<Contacto> listaContactos, Activity a, View v) {
        this.listaContactos = listaContactos;
        this.activity = a;
        this.view = v;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_contactos, parent, false);
        return new ContactosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {

        Contacto contacto = listaContactos.get(position);
        holder.tvNombre.setText(contacto.getNombre());
        holder.tvTelefono.setText(contacto.getTelefono());

        if(contacto.getImagen() != null) {
            Picasso.get().load(contacto.getImagen()).into(holder.imagen);
        } else {
            holder.imagen.setImageResource(R.drawable.noimagen);
        }

        final NavController navController = Navigation.findNavController(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigosViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AmigosViewModel.class);
                amigosViewModel.setContacto(contacto);

                navController.navigate(R.id.action_from_contactos_to_info);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvTelefono;
        ImageView imagen;

        public ContactosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombreContacto);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoContacto);
            imagen = itemView.findViewById(R.id.imgContacto);
        }
    }
}
