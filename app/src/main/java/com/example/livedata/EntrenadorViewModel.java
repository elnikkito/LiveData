package com.example.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public class EntrenadorViewModel extends AndroidViewModel {

    Entrenador entrenador;
    private MediatorLiveData<Integer> ejercicioLiveData = new MediatorLiveData<>();
    private MediatorLiveData<String> repeticionLiveData = new MediatorLiveData<>();

    public EntrenadorViewModel(@NonNull Application application) {
        super(application);
        entrenador = new Entrenador();

        // Observa los cambios en `ordenLiveData` y actúa en consecuencia
        ejercicioLiveData.addSource(entrenador.ordenLiveData, orden -> {
            String ejercicio = orden.split(":")[0];
            int imagen;

            switch (ejercicio) {
                case "EJERCICIO1":
                default:
                    imagen = R.drawable.e1;
                    break;
                case "EJERCICIO2":
                    imagen = R.drawable.e2;
                    break;
                case "EJERCICIO3":
                    imagen = R.drawable.e3;
                    break;
                case "EJERCICIO4":
                    imagen = R.drawable.e4;
                    break;
                case "EJERCICIO5":
                    imagen = R.drawable.e1b;
                    break;
                case "EJERCICIO6":
                    imagen = R.drawable.e2b;
                    break;
                case "EJERCICIO7":
                    imagen = R.drawable.e3b;
                    break;
                case "EJERCICIO8":
                    imagen = R.drawable.e4b;
                    break;
            }
            ejercicioLiveData.setValue(imagen);
        });

        repeticionLiveData.addSource(entrenador.ordenLiveData, orden -> {
            String repeticion = orden.split(":")[1];
            repeticionLiveData.setValue(repeticion);
        });
    }

    LiveData<Integer> obtenerEjercicio() {
        return ejercicioLiveData;
    }

    LiveData<String> obtenerRepeticion() {
        return repeticionLiveData;
    }
}
