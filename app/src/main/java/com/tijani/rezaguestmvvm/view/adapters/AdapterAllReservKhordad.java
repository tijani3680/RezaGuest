package com.tijani.rezaguestmvvm.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.ItemRecyclerKhordadReservationBinding;
import com.tijani.rezaguestmvvm.model.ReservInformation;

import java.util.List;

public class AdapterAllReservKhordad extends RecyclerView.Adapter<AdapterAllReservKhordad.ReservInformationViewHolder> {
    private Context context;
    private List<ReservInformation> reservInformationList;
    private OnClickListener listener;

    public AdapterAllReservKhordad(Context context, List<ReservInformation> reservInformationList, OnClickListener listener) {
        this.context = context;
        this.reservInformationList = reservInformationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReservInformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecyclerKhordadReservationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_recycler_khordad_reservation, parent, false);

        return new ReservInformationViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservInformationViewHolder holder, int position) {
        ReservInformation reservInformation = reservInformationList.get(position);
        holder.binding.setReservInformation(reservInformation);
        holder.imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.arrowItemOnClick(reservInformation);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reservInformationList.size();
    }

    public class ReservInformationViewHolder extends RecyclerView.ViewHolder {
        ItemRecyclerKhordadReservationBinding binding;
        ImageView imgArrow;


        public ReservInformationViewHolder(@NonNull ItemRecyclerKhordadReservationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            imgArrow =binding.imgItemRecyclerReservationGoToDescription;
        }
    }

    public interface OnClickListener{
        void arrowItemOnClick(ReservInformation reservInformation);
    }
}
