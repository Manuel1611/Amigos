package com.example.amigos.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.amigos.model.room.pojo.Amigos;

import java.util.List;

@Dao
public interface AmigosDao {

    @Insert
    long insert(Amigos amigos);

    @Update
    int update(Amigos amigos);

    @Delete
    int delete(Amigos amigos);

    @Query("delete from amigos where id = :id")
    int deleteId(long id);

    @Query("select * from amigos order by nombre")
    LiveData<List<Amigos>> getAll();

    @Query("select id from amigos where telefono = :telefono")
    long getIdAmigo(String telefono);
}
