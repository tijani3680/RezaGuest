package com.tijani.rezaguestmvvm.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemRecyclerOperatorsBinding;
import com.tijani.rezaguestmvvm.model.Users;

import java.util.List;

public class AdapterOperators extends RecyclerView.Adapter<AdapterOperators.OperatorViewHolder> {
    private Context context;
    private List<Users> usersList;
    private OnClickItemRecycler listener;

    public AdapterOperators(Context context, List<Users> usersList, OnClickItemRecycler listener) {
        this.context = context;
        this.usersList = usersList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OperatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerOperatorsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_operators,parent,false);

        return new OperatorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OperatorViewHolder holder, int position) {
        Users users = usersList.get(position);
        holder.binding.setUserInformation(users);
        holder.relDeleteOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteOperator(users.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class OperatorViewHolder extends RecyclerView.ViewHolder{
        ItemRecyclerOperatorsBinding binding;
        RelativeLayout relDeleteOperator;

        public OperatorViewHolder(@NonNull ItemRecyclerOperatorsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            relDeleteOperator = binding.relItemRecyclerOperatorDeleteOperator;
        }
    }

    public interface OnClickItemRecycler{
        void deleteOperator(String id);
    }
}
