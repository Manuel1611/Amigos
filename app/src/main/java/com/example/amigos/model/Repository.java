package com.example.amigos.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.amigos.model.room.AmigosDatabase;
import com.example.amigos.model.room.dao.AmigosDao;
import com.example.amigos.model.room.dao.LlamadasDao;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.AmigosLlamadas;
import com.example.amigos.model.room.pojo.Llamadas;
import com.example.amigos.util.UtilThread;

import java.util.Calendar;
import java.util.List;

public class Repository {

    private AmigosDao amigosDao;
    private LlamadasDao llamadasDao;

    private LiveData<List<Amigos>> liveListAmigos;
    private LiveData<List<AmigosLlamadas>> liveListAmigosLlamadas;

    public Repository(Context context) {

        AmigosDatabase db = AmigosDatabase.getDb(context);

        amigosDao = db.getAmigosDao();
        llamadasDao = db.getLlamadasDao();

        liveListAmigos = amigosDao.getAll();
        liveListAmigosLlamadas = llamadasDao.getAllAmigosLlamadas();

    }

    public LiveData<List<Amigos>> getLiveListAmigos() {
        return liveListAmigos;
    }

    public LiveData<List<AmigosLlamadas>> getLiveListAmigosLlamadas() {
        return liveListAmigosLlamadas;
    }

    public void insert(Amigos amigos) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    amigosDao.insert(amigos);
                } catch (Exception e) {

                }
            }
        });
    }

    public void update(Amigos amigos) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    amigosDao.update(amigos);
                } catch (Exception e) {

                }
            }
        });
    }

    public void getIdAmigo(String telefono) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {

                Log.v("XYZ", "Telefono: " + telefono);

                Calendar calendar = Calendar.getInstance();

                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int ano = calendar.get(Calendar.YEAR);

                String fecha = dia + "/" + mes + "/" + ano;

                long id = amigosDao.getIdAmigo(telefono);

                Llamadas llamadas = new Llamadas(id, fecha);

                llamadasDao.insert(llamadas);
            }
        });
    }

    public void delete(long id) {
        UtilThread.threadExecutorPool.execute(new Runnable() {
            @Override
            public void run() {
                amigosDao.deleteId(id);
            }
        });
    }

}
