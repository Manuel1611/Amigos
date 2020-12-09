package com.example.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.amigos.model.room.pojo.Llamadas;
import com.example.amigos.model.room.pojo.AmigosLlamadas;

import java.util.List;

@Dao
public interface LlamadasDao {

    @Insert
    long insert(Llamadas llamadas);

    @Query("select a.id, a.nombre, a.telefono, a.fechaNac, count(l.id) count from amigos a left join llamadas l on a.id = l.idamigo group by a.id, a.nombre, a.telefono, a.fechaNac order by nombre")
    LiveData<List<AmigosLlamadas>> getAllAmigosLlamadas();

}
