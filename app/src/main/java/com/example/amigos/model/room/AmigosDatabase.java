package com.example.amigos.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.amigos.model.room.dao.AmigosDao;
import com.example.amigos.model.room.dao.LlamadasDao;
import com.example.amigos.model.room.pojo.Amigos;
import com.example.amigos.model.room.pojo.Llamadas;

@Database(entities = {Amigos.class, Llamadas.class}, version = 1, exportSchema = false)
public abstract class AmigosDatabase extends RoomDatabase {

    public abstract AmigosDao getAmigosDao();
    public abstract LlamadasDao getLlamadasDao();

    private static volatile AmigosDatabase INSTANCE;

    public static synchronized AmigosDatabase getDb(final Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AmigosDatabase.class, "dbamigos.sqlite").build();
        }
        return INSTANCE;
    }

}
