package com.tijani.rezaguestmvvm.view.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemRecyclerBlackListCustomerBinding;
import com.tijani.rezaguestmvvm.model.BlockCustomer;
import com.tijani.rezaguestmvvm.model.LastCustomer;

import java.util.ArrayList;
import java.util.List;

public class AdapterBlockCustomer extends RecyclerView.Adapter<AdapterBlockCustomer.BlockCustomrViewHolder> {
    private Context context;
    private List<BlockCustomer> blockCustomerList;
    private OnClick listener;


    public AdapterBlockCustomer(Context context, List<BlockCustomer> blockCustomerList, OnClick listener) {
        this.context = context;
        this.blockCustomerList = blockCustomerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BlockCustomrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerBlackListCustomerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_black_list_customer,parent,false);
        return new BlockCustomrViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockCustomrViewHolder holder, int position) {

        BlockCustomer blockCustomer = blockCustomerList.get(position);
        holder.binding.setBlockCustomerInformation(blockCustomer);
        holder.imgDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("customerName",blockCustomer.getCustomerName());
                bundle.putString("description",blockCustomer.getDescription());

                Navigation.findNavController(holder.imgDescription).navigate(R.id.action_fragmentCustomerBlackList_to_fragmentDetailsBlockCustomer,bundle);

            }
        });

        holder.relBtnDeletFromBlockList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(blockCustomer);
            }
        });

    }

    @Override
    public int getItemCount() {
        return blockCustomerList.size();
    }


    public class BlockCustomrViewHolder extends RecyclerView.ViewHolder{
        ItemRecyclerBlackListCustomerBinding binding;
        ImageView imgDescription;
        RelativeLayout relBtnDeletFromBlockList;

        public BlockCustomrViewHolder(@NonNull ItemRecyclerBlackListCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            imgDescription = binding.imgArrowDescription;
            relBtnDeletFromBlockList = binding.relItemRecyclerBlackListCustomerDeletFromBlackList;



        }
    }

    public interface OnClick{
        void onClick(BlockCustomer blockCustomer);
    }


    public void setFilter(ArrayList<BlockCustomer> arrayList) {


        blockCustomerList = new ArrayList<>();
        blockCustomerList.addAll(arrayList);
        notifyDataSetChanged();

    }

}
