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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentCustomerBlackListBinding;
import com.tijani.rezaguestmvvm.model.BlockCustomer;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.view.adapters.AdapterBlockCustomer;
import com.tijani.rezaguestmvvm.viewmodel.CustomerVM;

import java.util.ArrayList;
import java.util.List;


public class FragmentCustomerBlackList extends Fragment implements AdapterBlockCustomer.OnClick {
    FragmentCustomerBlackListBinding binding;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterBlockCustomer adapter;
    private ProgressBar progressBar, progressBarDialog;
    private List<BlockCustomer> blockCustomerList = new ArrayList<>();
    private RelativeLayout relEmptyBox;

    private CardView btnConfirm, btnCancel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_black_list, container, false);
        toolbar = binding.toolbarCustomerBlackList;
        recyclerView = binding.rclFragmentCustomerBlackList;
        progressBar = binding.progressbarFragmentCustomerBlackList;
        relEmptyBox = binding.relFragmentBlockCustomerEmptyBox;

        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBlockListCustomer();

        toolbar.inflateMenu(R.menu.menu_items);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        newText = newText.toLowerCase();
                        ArrayList<BlockCustomer> newArrayList = new ArrayList<>();

                        for (BlockCustomer blockCustomer : blockCustomerList) {
                            String customerName = blockCustomer.getCustomerName();
                            String shenaseh = blockCustomer.getIdPerson();
                            String phone = blockCustomer.getPhone();

                            if (customerName.contains(newText) || shenaseh.contains(MyUtils.persianToEnglish(newText)) || phone.contains(MyUtils.persianToEnglish(newText))) {
                                newArrayList.add(blockCustomer);
                            }

                        }

                        adapter.setFilter(newArrayList);


                        return true;
                    }
                });

                return true;
            }

        });


    }

    private void getBlockListCustomer() {

        MutableLiveData<List<BlockCustomer>> blockCustomrLiveData = new ViewModelProvider(requireActivity()).get(CustomerVM.class).getBlockCustomer();

        blockCustomrLiveData.observe(requireActivity(), new Observer<List<BlockCustomer>>() {
            @Override
            public void onChanged(List<BlockCustomer> blockCustomers) {
                progressBar.setVisibility(View.GONE);
                blockCustomerList =blockCustomers;
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setHasFixedSize(true);

                adapter = new AdapterBlockCustomer(getContext(), blockCustomers, FragmentCustomerBlackList.this::onClick);
                recyclerView.setAdapter(adapter);

                if (blockCustomers.isEmpty()){
                    relEmptyBox.setVisibility(View.VISIBLE);
                }


            }
        });
    }

    @Override
    public void onClick(BlockCustomer blockCustomer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_delet_from_black_list);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnConfirm = dialog.findViewById(R.id.cardDialog_customLayoutDeleteFromBlackList_btn_confirm);
        btnCancel = dialog.findViewById(R.id.cardDialog_customLayoutDeleteFromBlackList_btn_cancel);
        progressBarDialog = dialog.findViewById(R.id.customDialogBlackList_progressbar);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBarDialog.setVisibility(View.VISIBLE);

                CustomerVM viewModel = new ViewModelProvider(requireActivity()).get(CustomerVM.class);
                viewModel.deleteFromBlackList(blockCustomer.getCustomerId());
                viewModel.deleteFromBlackListLiveData.observe(requireActivity(), new Observer<Status>() {
                    @Override
                    public void onChanged(Status status) {
                        progressBarDialog.setVisibility(View.GONE);
                        dialog.dismiss();

                        if (status.getStatus().equals("success")) {
                            Toast.makeText(getContext(), "مشتری از لیست سیاه حذف شد", Toast.LENGTH_SHORT).show();
                            getBlockListCustomer();
                        } else {
                            Toast.makeText(getContext(), "متاسفانه خطایی رخ داده است!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });


    }

}