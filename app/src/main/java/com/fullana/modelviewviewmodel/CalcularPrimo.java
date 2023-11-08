package com.fullana.modelviewviewmodel;

public class CalcularPrimo {

    public static class CalculadorPrimo{
        public final int anyo;

        public CalculadorPrimo(int anyo) {
            this.anyo = anyo;
        }
    }
    public void esPrimo(CalculadorPrimo calculadorPrimo,Callback callback){
        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(1000);   // simular operacion de larga duracion (10s)

        } catch (InterruptedException e) {}
        //revisa si n es multiplo de 2
        int anyo = calculadorPrimo.anyo;
        if (anyo%2==0){
            callback.calculado(false);
            callback.cuandoFinaliceElCalculo();
            return;
        };
        //si no, solo revisa los impares
        for(int i=3;i*i<=anyo;i+=2) {
            if(anyo%i==0) {
                callback.calculado(false);
                callback.cuandoFinaliceElCalculo();
                return;
            }
        }
        callback.calculado(true);
        callback.cuandoFinaliceElCalculo();
    }
    interface Callback{
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
        void calculado(boolean primo);
    }
}
