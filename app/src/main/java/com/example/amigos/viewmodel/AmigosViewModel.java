package com.example.amigos.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.amigos.model.Repository;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.AmigosLlamadas;
import com.example.amigos.model.room.pojo.Contacto;
import com.example.amigos.view.adapter.ContactosRecyclerAdapter;

import java.util.List;

public class AmigosViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<Amigos>> liveAmigosList;
    private LiveData<List<AmigosLlamadas>> liveAmigosLlamadasList;
    private Amigos amigos;
    private long cont;
    private Contacto contacto;
    private AmigosLlamadas amigosLlamadas;

    public AmigosViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        liveAmigosList = repository.getLiveListAmigos();
        liveAmigosLlamadasList = repository.getLiveListAmigosLlamadas();
    }

    public LiveData<List<Amigos>> getLiveAmigosList() {
        return liveAmigosList;
    }

    public LiveData<List<AmigosLlamadas>> getLiveAmigosLlamadasList() {
        return liveAmigosLlamadasList;
    }

    public void insert(Amigos amigos) {
        repository.insert(amigos);
    }

    public void update(Amigos amigos) {
        repository.update(amigos);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public Amigos getAmigos() {
        return this.amigos;
    }

    public void setAmigos(Amigos amigos) {
        this.amigos = amigos;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public AmigosLlamadas getAmigosLlamadas() {
        return amigosLlamadas;
    }

    public void setAmigosLlamadas(AmigosLlamadas amigosLlamadas) {
        this.amigosLlamadas = amigosLlamadas;
    }

    public void getContactos(Context c, List<Contacto> listaContactos, ContactosRecyclerAdapter adapter) {
        repository.getContactos(c, listaContactos, adapter);
    }

    public long getCont() {
        return cont;
    }

    public void setCont(long cont) {
        this.cont = cont;
    }
}
