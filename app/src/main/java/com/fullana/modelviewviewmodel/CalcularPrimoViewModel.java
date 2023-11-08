package com.fullana.modelviewviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CalcularPrimoViewModel extends AndroidViewModel {
    Executor executor;
    CalcularPrimo calcularPrimo;

    MutableLiveData<Boolean> anyoM = new MutableLiveData<>();
    MutableLiveData<Boolean> calculando = new MutableLiveData<>();


    public CalcularPrimoViewModel(@NonNull Application application) {
        super(application);
        executor = Executors.newSingleThreadExecutor();
        calcularPrimo = new CalcularPrimo();
    }

    public void calcular(int anyo){

        final CalcularPrimo.CalculadorPrimo calculadorPrimo = new CalcularPrimo.CalculadorPrimo(anyo);
        executor.execute(() ->{
            calcularPrimo.esPrimo(calculadorPrimo, new CalcularPrimo.Callback() {
                @Override
                public void cuandoEmpieceElCalculo() {
                    calculando.postValue(true);
                }

                @Override
                public void cuandoFinaliceElCalculo() {
                    calculando.postValue(false);
                }

                @Override
                public void calculado(boolean y) {
                    anyoM.postValue(y);
                }
            });
        });
    }
}
