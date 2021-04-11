package com.tijani.rezaguestmvvm.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemRecyclerLastCustomerBinding;
import com.tijani.rezaguestmvvm.model.LastCustomer;

import java.util.ArrayList;
import java.util.List;

public class AdapterLastCustomer extends RecyclerView.Adapter<AdapterLastCustomer.LastCustomerViewHolder> {
    private Context context;
    private List<LastCustomer> lastCustomerList;
    private Click listener;

    public AdapterLastCustomer(Context context, List<LastCustomer> lastCustomerList, Click listener) {
        this.context = context;
        this.lastCustomerList = lastCustomerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LastCustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerLastCustomerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_last_customer, parent, false);
        return new LastCustomerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LastCustomerViewHolder holder, int position) {

        LastCustomer lastCustomer = lastCustomerList.get(position);
        holder.binding.setLastCustomerInformation(lastCustomer);
        String blackList = lastCustomer.getBlackList();
        if (blackList.equals("1")){
            holder.relAddToBlackList.setClickable(false);
            holder.txtAddToBlackList.setTextColor(Color.rgb(217,217,217));
            holder.imgAddToBlackList.setColorFilter(Color.rgb(217,217,217));
        }else {
            holder.txtAddToBlackList.setTextColor(Color.RED);
            holder.imgAddToBlackList.setColorFilter(Color.rgb(255, 0, 0));
            holder.relAddToBlackList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickItem(lastCustomer);
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return lastCustomerList.size();
    }


    public class LastCustomerViewHolder extends RecyclerView.ViewHolder {

        ItemRecyclerLastCustomerBinding binding;

        RelativeLayout relAddToBlackList ;
        ImageView imgAddToBlackList;
        TextView txtAddToBlackList;

        public LastCustomerViewHolder(@NonNull ItemRecyclerLastCustomerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            imgAddToBlackList = binding.imgIconAddToBlakList;
            relAddToBlackList = binding.relItemRecyclerLastCustomerAddBlakList;
            txtAddToBlackList = binding.txtItemRecyclerLastCustomerAddBlackList;
        }
    }

    public void setFilter(ArrayList<LastCustomer> arrayList) {


        lastCustomerList = new ArrayList<>();
        lastCustomerList.addAll(arrayList);
        notifyDataSetChanged();

    }

    public interface Click{
        void onClickItem(LastCustomer lastCustomer);
    }

}
