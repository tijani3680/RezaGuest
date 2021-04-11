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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentSuitesPriceBinding;
import com.tijani.rezaguestmvvm.model.PriceSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.view.adapters.AdapterSuitesPrice;
import com.tijani.rezaguestmvvm.viewmodel.SuiteVM;

import java.util.List;
import java.util.PrimitiveIterator;

public class FragmentSuitesPrice extends Fragment implements AdapterSuitesPrice.OnItemClick {
    FragmentSuitesPriceBinding binding;
    private RecyclerView recyclerView;
    private ImageView imgBack;
    private ProgressBar prg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_suites_price,container,false);
       imgBack = binding.imgFragmentSuitesPriceImgBack;
       recyclerView = binding.rclFragmentSuitesPriceRecycler;
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       recyclerView.setHasFixedSize(true);
       prg = binding.progressbarFragmentSuitesPrice;
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
        getSuitesPrice();
    }

    private void getSuitesPrice(){
        MutableLiveData<List<PriceSuites>> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).getSuitePrice();
        liveData.observe(requireActivity(), new Observer<List<PriceSuites>>() {
            @Override
            public void onChanged(List<PriceSuites> priceSuites) {
                prg.setVisibility(View.GONE);
                AdapterSuitesPrice adapter = new AdapterSuitesPrice(getContext(),priceSuites,FragmentSuitesPrice.this::onClickItemRecycler);
                recyclerView.setAdapter(adapter);

            }
        });
    }

    @Override
    public void onClickItemRecycler(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_edit_price);
        builder.setCancelable(false);
        Dialog dialog = builder.create();

        dialog.show();
        EditText  edtSpacialPrice = dialog.findViewById(R.id.edtSpacialPrice);
        EditText  edtSpacialMazadPrice = dialog.findViewById(R.id.edtSpacialMazadPrice);
        EditText  edtPrice = dialog.findViewById(R.id.edtPrice);
        EditText  edtMazadPrice = dialog.findViewById(R.id.edtMazadPrice);
        CardView cardBtnConfirm =  dialog.findViewById(R.id.cardDialog_custom_dialog_editPrice_btn_confirm);
        CardView cardBtnCancel =  dialog.findViewById(R.id.cardDialog_custom_dialog_editPrice_btn_cancel);

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spacialPrice = edtSpacialPrice.getText().toString();
                String spacialMazadPrice = edtSpacialMazadPrice.getText().toString();
                String price = edtPrice.getText().toString();
                String mazadPrice = edtMazadPrice.getText().toString();

                if (spacialPrice.isEmpty() || spacialMazadPrice.isEmpty() || price.isEmpty() || mazadPrice.isEmpty()){
                    Toast.makeText(getContext(), "پر کردن تمامی فیلدها الزامی است!", Toast.LENGTH_SHORT).show();
                }else{
                    updatePrice(id,spacialPrice,spacialMazadPrice,price,mazadPrice,dialog);

                }
            }
        });

    }

    private void updatePrice(String id,String spacialPrice,String spacialMazadPrice,String price,String  mazadPrice ,Dialog dialog){
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).updatePrice(id,spacialPrice,spacialMazadPrice,price,mazadPrice);
        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")){
                    dialog.dismiss();
                    Toast.makeText(getContext(), "قیمت سوئیت تغییر یافت", Toast.LENGTH_SHORT).show();
                    getSuitesPrice();
                }else {
                    Toast.makeText(getContext(), "خطای نا شناخته!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
