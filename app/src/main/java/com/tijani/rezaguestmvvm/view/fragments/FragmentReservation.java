package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentReservationBinding;
import com.tijani.rezaguestmvvm.view.adapters.ViewPagerAdapter;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer2;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer;
import com.wajahatkarim3.easyflipviewpager.CardFlipPageTransformer2;

public class FragmentReservation extends Fragment {
    FragmentReservationBinding binding;
    private SmartTabLayout tabLayout;
    private ViewPager viewPager;
    private CardFlipPageTransformer cardFlipPageTransformer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reservation, container, false);
        tabLayout = binding.tabLayout;
        viewPager = binding.viewpager;
        cardFlipPageTransformer = new CardFlipPageTransformer();
        cardFlipPageTransformer.setScalable(false);
        cardFlipPageTransformer.setFlipOrientation(CardFlipPageTransformer.VERTICAL);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), 12);

        adapter.addTab(new FragmentEsfandReservation(), "اسفند");
        adapter.addTab(new FragmentBahmanReservation(), "بهمن");
        adapter.addTab(new FragmentDeyReservation(), "دی");
        adapter.addTab(new FragmentAzarReservation(), "آذر");
        adapter.addTab(new FragmentAbanReservation(), "آبان");
        adapter.addTab(new FragmentMehrReservation(), "مهر");
        adapter.addTab(new FragmentShahrivarReservation(), "شهریور");
        adapter.addTab(new FragmentMordadReservation(), "مرداد");
        adapter.addTab(new FragmentTirReservation(), "تیر");
        adapter.addTab(new FragmentKhordadReservation(), "خرداد");
        adapter.addTab(new FragmentOrdibeheshtReservation(), "اردیبهشت");
        adapter.addTab(new FragmentFarvardinReservation(), "فروردین");

        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        viewPager.setCurrentItem(11);
        viewPager.setPageTransformer(true, cardFlipPageTransformer);
    }
}
