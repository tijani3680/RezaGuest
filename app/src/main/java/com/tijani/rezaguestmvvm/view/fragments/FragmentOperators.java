package com.tijani.rezaguestmvvm.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentOperatorsBinding;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.model.Users;
import com.tijani.rezaguestmvvm.view.adapters.AdapterOperators;
import com.tijani.rezaguestmvvm.viewmodel.OperatorVM;

import java.util.List;

public class FragmentOperators extends Fragment implements AdapterOperators .OnClickItemRecycler {
    FragmentOperatorsBinding binding;
    private RecyclerView recyclerView;
    private ProgressBar prg;
    private RelativeLayout relEmptyBox;
    private ImageView imgBack;
    private AdapterOperators adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_operators, container, false);
        recyclerView = binding.recyclerOperators;
        prg = binding.progressbar;
        relEmptyBox = binding.relEmptyBox;
        imgBack =binding.imgFragmentOperatorsImgBack;

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imgBack).popBackStack();
            }
        });
        getOperators();

    }

    private void getOperators(){
        MutableLiveData<List<Users>> liveData = new ViewModelProvider(requireActivity()).get(OperatorVM.class).getOperators();
        liveData.observe(requireActivity(), new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                prg.setVisibility(View.GONE);
                adapter = new AdapterOperators(getContext(),users,FragmentOperators.this::deleteOperator);
                recyclerView.setAdapter(adapter);
                if (users.isEmpty()){
                    relEmptyBox.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public void deleteOperator(String id) {
        showDialog(id);


    }

    private void delete(String id,Dialog dialog){
        OperatorVM operatorVM = new ViewModelProvider(requireActivity()).get(OperatorVM.class);
        operatorVM.deleteOperator(id);
        operatorVM.deleteOperatorLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    getOperators();
                }else {
                    Toast.makeText(getContext(), "خطای ناشناخته", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void showDialog(String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_delete_operator);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        CardView btnCancel,btnConfirm;
        btnCancel = dialog.findViewById(R.id.cardDialog_customLayout_btn_cancel);
        btnConfirm = dialog.findViewById(R.id.cardDialog_customLayout_btn_confirm);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(id,dialog);



            }
        });

    }

}