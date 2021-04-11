package com.tijani.rezaguestmvvm.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemNavigationMenuBinding;
import com.tijani.rezaguestmvvm.model.NavigationMenu;

import java.util.List;

public class NavigationMenuAdapter extends RecyclerView.Adapter<NavigationMenuAdapter.MenuViewHolder> {
    Context context;
    List<NavigationMenu> menuList;
    OnClick onClick;


    public NavigationMenuAdapter(Context context, List<NavigationMenu> menuList, OnClick onClick) {
        this.context = context;
        this.menuList = menuList;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNavigationMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_navigation_menu,parent,false);

        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        NavigationMenu menu = menuList.get(position);
        holder.binding.setNavigationMenuInformation(menu);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.click(position);


            }
        });


    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        ItemNavigationMenuBinding binding;


        public MenuViewHolder(@NonNull ItemNavigationMenuBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }
    }

    public interface OnClick{
        void click(int position);

    }



}
