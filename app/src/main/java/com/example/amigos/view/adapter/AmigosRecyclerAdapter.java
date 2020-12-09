package com.example.amigos.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amigos.R;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.AmigosLlamadas;
import com.example.amigos.viewmodel.AmigosViewModel;

import java.util.List;

public class AmigosRecyclerAdapter extends RecyclerView.Adapter<AmigosRecyclerAdapter.AmigosViewHolder> {

    AmigosViewModel amigosViewModel;
    Activity activity;
    List<AmigosLlamadas> listaAmigosLlamadas;
    View view;

    public AmigosRecyclerAdapter(List<AmigosLlamadas> listaAmigosllamadas, Activity a, View v) {
        this.listaAmigosLlamadas = listaAmigosllamadas;
        this.activity = a;
        this.view = v;
    }

    @NonNull
    @Override
    public AmigosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_amigos, parent, false);
        return new AmigosRecyclerAdapter.AmigosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmigosViewHolder holder, int position) {

        amigosViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AmigosViewModel.class);

        Amigos amigos = listaAmigosLlamadas.get(position).getAmigos();

        holder.tvNombre.setText(amigos.getNombre());
        holder.tvTelefono.setText(amigos.getTelefono());

        final NavController navController = Navigation.findNavController(view);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amigosViewModel.setAmigos(amigos);
                amigosViewModel.setCont(listaAmigosLlamadas.get(position).getCount());

                navController.navigate(R.id.action_from_main_to_ver);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAmigosLlamadas.size();
    }

    public class AmigosViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvTelefono;

        public AmigosViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombreAmigo);
            tvTelefono = itemView.findViewById(R.id.tvTelefonoAmigo);

        }
    }
}
