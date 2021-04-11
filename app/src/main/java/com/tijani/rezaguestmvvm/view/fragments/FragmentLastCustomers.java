package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentLastCustomersBinding;
import com.tijani.rezaguestmvvm.model.LastCustomer;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.view.adapters.AdapterLastCustomer;
import com.tijani.rezaguestmvvm.viewmodel.CustomerVM;

import java.util.ArrayList;
import java.util.List;

public class FragmentLastCustomers extends Fragment implements AdapterLastCustomer.Click {
    FragmentLastCustomersBinding binding;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private List<LastCustomer> listCustomers = new ArrayList<>();
    private AdapterLastCustomer adapterLastCustomer;
    private ProgressBar progressBar, progressBarDialog;
    private EditText edtDesAddBlackList;
    private CardView cardConfirm;
    private CardView cardCancel;
    private RelativeLayout relEmptyBox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_last_customers, container, false);
        toolbar = binding.toolbarLastCustomer;
        recyclerView = binding.rclFragmentLastCustomersRecyclerview;
        progressBar = binding.progressbarFragmentLastCustomer;
        relEmptyBox = binding.relFragmentLastCustomerEmptyBox;
        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getLastCustomer();
        toolbar.inflateMenu(R.menu.menu_items);

        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.action_search) {
                androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) MenuItemCompat.getActionView(menuItem);
                searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {

                        newText = newText.toLowerCase();

                        ArrayList<LastCustomer> newArrayList = new ArrayList<>();
                        for (LastCustomer lastCustomer : listCustomers) {
                            String customerName = lastCustomer.getCustomerName();
                            String shenaseh = lastCustomer.getIdPerson();
                            String phone = lastCustomer.getPhone();
                            if (customerName.contains(newText) || shenaseh.contains(MyUtils.persianToEnglish(newText)) || phone.contains(MyUtils.persianToEnglish(newText))) {
                                newArrayList.add(lastCustomer);
                            }
                        }

                        adapterLastCustomer.setFilter(newArrayList);


                        return true;
                    }
                });

            }
            return true;
        });

    }


    @Override
    public void onClickItem(LastCustomer lastCustomer) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_blacklist);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        dialog.show();

        edtDesAddBlackList = dialog.findViewById(R.id.edtCustomDialogBlackList);
        cardConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_addBlackList_btn_confirm);
        cardCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_addBlackList_btn_cancel);
        progressBarDialog = dialog.findViewById(R.id.customDialogBlackList_progressbar);

        cardCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        cardConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarDialog.setVisibility(View.VISIBLE);
                CustomerVM customerVM = new ViewModelProvider(requireActivity()).get(CustomerVM.class);
                customerVM.addToBlackList(lastCustomer.getCustomerId(), edtDesAddBlackList.getText().toString());
                customerVM.addToBlackListLiveData.observe(requireActivity(), new Observer<Status>() {
                    @Override
                    public void onChanged(Status status) {
                        progressBarDialog.setVisibility(View.GONE);
                        dialog.dismiss();

                        if (status.getStatus().equals("success")) {
                            Toast.makeText(getContext(), "مشتری به لیست سیاه اضافه شد", Toast.LENGTH_SHORT).show();
                            getLastCustomer();
                        } else {
                            Toast.makeText(getContext(), "عملیات با خطا مواجه شد!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }

    private void getLastCustomer() {

        MutableLiveData<List<LastCustomer>> liveData = new ViewModelProvider(requireActivity()).get(CustomerVM.class).getLastCustomer();

        liveData.observe(requireActivity(), new Observer<List<LastCustomer>>() {
            @Override
            public void onChanged(List<LastCustomer> lastCustomers) {
                progressBar.setVisibility(View.GONE);
                listCustomers = lastCustomers;

                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setHasFixedSize(true);

                adapterLastCustomer = new AdapterLastCustomer(getContext(), lastCustomers, FragmentLastCustomers.this::onClickItem);
                recyclerView.setAdapter(adapterLastCustomer);
                if (lastCustomers.isEmpty()){
                    relEmptyBox.setVisibility(View.VISIBLE);

                }

            }
        });

    }

}