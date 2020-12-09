package com.example.amigos.model.room.pojo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "amigos", indices = {@Index(value = {"telefono"}, unique = true)})
public class Amigos {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "fechaNac")
    private String fechaNac;

    @NonNull
    @ColumnInfo(name = "telefono")
    private String telefono;

    public Amigos(long id, @NonNull String nombre, String fechaNac, @NonNull String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public Amigos(@NonNull String nombre, String fechaNac, @NonNull String telefono) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.telefono = telefono;
    }

    public Amigos(@NonNull String nombre, @NonNull String telefono) {
        this(0, nombre, "", telefono);
    }

    public Amigos() {
        this(0, "", "", "");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    @NonNull
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Amigos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

}