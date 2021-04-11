package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentAbanReservationBinding;
import com.tijani.rezaguestmvvm.model.ReservInformation;
import com.tijani.rezaguestmvvm.view.adapters.AdapterAllReservAban;
import com.tijani.rezaguestmvvm.view.adapters.AdapterAllReservFarvardin;
import com.tijani.rezaguestmvvm.view.adapters.AdapterAllReservMordad;
import com.tijani.rezaguestmvvm.viewmodel.AbanVM;
import com.tijani.rezaguestmvvm.viewmodel.MordadVM;

import java.util.List;

public class FragmentAbanReservation extends Fragment implements AdapterAllReservAban.OnClickListener {
    FragmentAbanReservationBinding binding;
    private RecyclerView recyclerView;
    private AdapterAllReservAban adapter;
    private ProgressBar prg;
    private RelativeLayout relEmptyBox;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_aban_reservation,container,false);
        recyclerView = binding.recyclerReservation;
        prg = binding.progressbarFragmentFarvardinReservation;
        relEmptyBox = binding.relEmptyBox;

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setHasFixedSize(true);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MutableLiveData<List<ReservInformation>> liveData = new ViewModelProvider(requireActivity()).get(AbanVM.class).getAllReserv();
        liveData.observe(requireActivity(), new Observer<List<ReservInformation>>() {
            @Override
            public void onChanged(List<ReservInformation> reservInformations) {
                prg.setVisibility(View.GONE);
                if (reservInformations.isEmpty()){
                    relEmptyBox.setVisibility(View.VISIBLE);
                }else {
                    adapter = new AdapterAllReservAban(getContext(),reservInformations,FragmentAbanReservation.this::arrowItemOnClick);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void arrowItemOnClick(ReservInformation reservInformation) {
        Bundle bundle = new Bundle();
        bundle.putString("id",reservInformation.getId());
        bundle.putString("customerName",reservInformation.getCustomerName());
        bundle.putString("shenaseh",reservInformation.getCustomerId());
        bundle.putString("phone",reservInformation.getPhone());
        bundle.putString("reservDate",reservInformation.getReservDate());
        bundle.putString("reservistPerson",reservInformation.getReservistPerson());
        Navigation.findNavController(recyclerView).navigate(R.id.action_fragmentReservation_to_fragmentDetailsAban2,bundle);

    }
}