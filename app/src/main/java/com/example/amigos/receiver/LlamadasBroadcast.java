package com.example.amigos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.example.amigos.model.Repository;

public class LlamadasBroadcast extends BroadcastReceiver {

    private Repository repository;
    private String numero;

    @Override
    public void onReceive(Context context, Intent intent) {
        String estado = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        repository = new Repository(context);

        if(estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            numero = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            repository.getIdAmigo(numero);

        }
    }
}