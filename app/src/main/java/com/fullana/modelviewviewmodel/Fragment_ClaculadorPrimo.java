package com.fullana.modelviewviewmodel;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fullana.modelviewviewmodel.databinding.FragmentClaculadorPrimoBinding;
public class Fragment_ClaculadorPrimo extends Fragment {

    FragmentClaculadorPrimoBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentClaculadorPrimoBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CalcularPrimoViewModel calcularPrimoViewModel = new ViewModelProvider(this).get(CalcularPrimoViewModel.class);
        calcularPrimoViewModel.calculando.observe(getViewLifecycleOwner(),(g)->{
            if(g) {
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.textView.setVisibility(View.GONE);
            }else{
                binding.progressBar.setVisibility(View.GONE);
                binding.textView.setVisibility(View.VISIBLE);
            }
        });
        binding.button.setOnClickListener((l) -> {
            calcularPrimoViewModel.calcular(Integer.parseInt(binding.editTextNumber.getText().toString()));
        });
        calcularPrimoViewModel.anyoM.observe(getViewLifecycleOwner(),(g)->{
            if(g) {
                System.out.println("paso por primo");
                binding.textView.setText("Es primo");
            }else {
                System.out.println("No es primo");
                binding.textView.setText("No es primo");
            }
        });
    }
}