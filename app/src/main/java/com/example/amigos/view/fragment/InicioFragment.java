package com.example.amigos.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.amigos.R;

public class InicioFragment extends Fragment {

    private static final int REQUEST_CODE = 1;

    public InicioFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anim(view);

        Button btInicio = view.findViewById(R.id.btInicio);

        btInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForPerm();
            }
        });

    }

    private void askForPerm() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permReadPhone = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);
            int permReadCall = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG);
            int permReadContacts = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);

            if((permReadPhone == PackageManager.PERMISSION_GRANTED) && (permReadCall == PackageManager.PERMISSION_GRANTED) && (permReadContacts == PackageManager.PERMISSION_GRANTED)) {
                moveToMainFragment();

            }else if(permReadPhone == PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_CALL_LOG);

                } else {

                    requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);

                }
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_CONTACTS);

                } else {
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
                }
            }else if(permReadCall == PackageManager.PERMISSION_GRANTED) {

                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_PHONE_STATE);

                } else {

                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);

                }
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_CONTACTS);

                } else {

                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CODE);

                }
            }else if(permReadContacts == PackageManager.PERMISSION_GRANTED) {

                if((shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) && (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG))) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG);

                }else {

                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG}, REQUEST_CODE);

                }
            }else {

                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) && shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)
                        && shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS);

                }else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_PHONE_STATE);

                }else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CALL_LOG)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_CALL_LOG);

                }else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {

                    explainPermission(REQUEST_CODE, Manifest.permission.READ_CONTACTS);

                }else {

                    requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS}, REQUEST_CODE);
                }
            }
        }
    }

    private void explainPermission(int code, String... permissions) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Se necesitan permisos:");

        builder.setMessage("Esta aplicación requiere de ciertos permisos para funcionar. Por favor, acéptalos todos para continuar.");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermissions(permissions, code);
            }
        });

        builder.setNegativeButton(android.R.string.cancel, null);
        builder.show();
    }

    private void moveToMainFragment() {
        NavHostFragment.findNavController(InicioFragment.this).navigate(R.id.action_from_inicio_to_main);
    }

    private void anim(View view) {

        Animation top = AnimationUtils.loadAnimation(getContext(), R.anim.top);
        Animation left = AnimationUtils.loadAnimation(getContext(), R.anim.left);
        Animation right = AnimationUtils.loadAnimation(getContext(), R.anim.right);

        TextView tvInicio = view.findViewById(R.id.tvInicio);
        View vUno = view.findViewById(R.id.lineaUno);
        View vDos = view.findViewById(R.id.lineaDos);
        View vTres = view.findViewById(R.id.lineaTres);
        View vCuatro = view.findViewById(R.id.lineaCuatro);
        View vCinco = view.findViewById(R.id.lineaCinco);
        View vSeis = view.findViewById(R.id.lineaSeis);
        View vSiete = view.findViewById(R.id.lineaSiete);
        View vOcho = view.findViewById(R.id.lineaOcho);
        View vNueve = view.findViewById(R.id.lineaNueve);
        View vDiez = view.findViewById(R.id.lineaDiez);

        tvInicio.setAnimation(top);
        vUno.setAnimation(left);
        vDos.setAnimation(right);
        vTres.setAnimation(left);
        vCuatro.setAnimation(right);
        vCinco.setAnimation(left);
        vSeis.setAnimation(right);
        vSiete.setAnimation(left);
        vOcho.setAnimation(right);
        vNueve.setAnimation(left);
        vDiez.setAnimation(right);

    }
}