package com.tijani.rezaguestmvvm.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemRecyclerSuitePriceBinding;
import com.tijani.rezaguestmvvm.model.PriceSuites;
import com.tijani.rezaguestmvvm.util.MyUtils;

import java.util.List;

public class AdapterSuitesPrice extends RecyclerView.Adapter<AdapterSuitesPrice.PriceViewHolder> {
    private Context context;
    private List<PriceSuites> priceSuitesList;
    private OnItemClick listener;

    public AdapterSuitesPrice(Context context, List<PriceSuites> priceSuitesList, OnItemClick listener) {
        this.context = context;
        this.priceSuitesList = priceSuitesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerSuitePriceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_suite_price, parent, false);
        return new PriceViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PriceViewHolder holder, int position) {
        PriceSuites priceSuites = priceSuitesList.get(position);
        holder.txtSuiteName.setText(priceSuites.getSuiteName());
        holder.txtSpacialPrice.setText(MyUtils.splitPrice(Integer.parseInt(priceSuites.getSpecialPrice()))+"  هزار تومان  ");
        holder.txtExtraSpacialPrice.setText(MyUtils.splitPrice(Integer.parseInt(priceSuites.getExtraSpecialPrice()))+"  هزار تومان  ");
        holder.txtPrice.setText(MyUtils.splitPrice(Integer.parseInt(priceSuites.getPrice()))+"  هزار تومان  ");
        holder.txtExtraPrice.setText(MyUtils.splitPrice(Integer.parseInt(priceSuites.getExtraPrice()))+"  هزار تومان  ");
        holder.linearEditprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickItemRecycler(priceSuites.getSuiteId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return priceSuitesList.size();
    }


    public class PriceViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerSuitePriceBinding binding;
        TextView txtSpacialPrice, txtPrice, txtExtraSpacialPrice, txtExtraPrice,txtSuiteName;
        LinearLayout linearEditprice;


        public PriceViewHolder(@NonNull ItemRecyclerSuitePriceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            txtSuiteName = binding.txtItemRecyclerSuitePriceSuiteName;
            txtSpacialPrice = binding.txtItemRecyclerSuitepriceSpacialCustomerPrice;
            txtExtraSpacialPrice = binding.txtItemRecyclerSuitepriceSpacialMazadCustomerPrice;
            txtPrice = binding.txtItemRecyclerSuitepriceCustomerPrice;
            txtExtraPrice = binding.txtItemRecyclerSuitepriceMazadCustomerPrice;
            linearEditprice = binding.linearEditPrice;



        }
    }

    public interface OnItemClick{
        void onClickItemRecycler(String id);
    }
}
