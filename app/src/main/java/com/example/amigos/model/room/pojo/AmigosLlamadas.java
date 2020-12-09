package com.example.amigos.model.room.pojo;

import androidx.room.Embedded;

public class AmigosLlamadas {

    @Embedded
    private Amigos amigos;

    private long count;

    public Amigos getAmigos() {
        return amigos;
    }

    public void setAmigos(Amigos amigos) {
        this.amigos = amigos;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "NumeroLlamadas{" +
                "amigos=" + amigos +
                ", count=" + count +
                '}';
    }

}
