package com.example.amigos.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "llamadas")
public class Llamadas {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "idamigo")
    private long idamigo;

    @NonNull
    @ColumnInfo(name = "fechaLlamada")
    private String fechaLlamada;

    public Llamadas(long id, long idamigo, @NonNull String fechaLlamada) {
        this.id = id;
        this.idamigo = idamigo;
        this.fechaLlamada = fechaLlamada;
    }

    public Llamadas(long idamigo, @NonNull String fechaLlamada) {
        this.idamigo = idamigo;
        this.fechaLlamada = fechaLlamada;
    }

    public Llamadas() {
        this(0, 0, "");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdamigo() {
        return idamigo;
    }

    public void setIdamigo(long idamigo) {
        this.idamigo = idamigo;
    }

    @NonNull
    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(@NonNull String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamadas{" +
                "id=" + id +
                ", idamigo=" + idamigo +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                '}';
    }
}
