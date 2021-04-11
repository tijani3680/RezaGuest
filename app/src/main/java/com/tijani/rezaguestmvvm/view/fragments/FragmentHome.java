package com.tijani.rezaguestmvvm.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;

import com.tijani.rezaguestmvvm.databinding.FragmentHomeBinding;
import com.tijani.rezaguestmvvm.model.ActivateMonths;
import com.tijani.rezaguestmvvm.model.NavigationMenu;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.view.adapters.NavigationMenuAdapter;
import com.tijani.rezaguestmvvm.viewmodel.MonthsVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class FragmentHome extends Fragment implements NavigationMenuAdapter.OnClick {
    /// Views
    private FragmentHomeBinding binding;
    private RecyclerView recyclerViewNavigationMenu;
    private DrawerLayout drawerLayout;
    private ImageView imgHamberMenu, imgListReservation;
    private TextView txtSignIn, txtExit, txtDataHeader;
    private LinearLayout linearSignOutAcount;
    private GridLayout gridLayoutMonth;
    private RelativeLayout relPrg;

    ///Variable
    private List<NavigationMenu> menus;
    public boolean doubleBackToExitPressedOnce = false;
    private String userName;
    private Handler handler = new Handler(Looper.getMainLooper());
    private String farvardin, ordibehesht, khordad, tir, mordad, shahrivar, mehr, aban, azar, dey, bahman, esfand;
    private final String PANEL_PASSWORD = "451371";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        recyclerViewNavigationMenu = binding.rclNavigationLayoutRecyclerMenu;
        drawerLayout = binding.drawerLayout;
        imgHamberMenu = binding.hamberMenue;
        imgListReservation = binding.listReservation;
        txtSignIn = binding.txvSign;
        txtExit = binding.txvExit;
        txtDataHeader = binding.txtHeaderLayoutDate;
        linearSignOutAcount = binding.linearExit;
        gridLayoutMonth = binding.gridLayoutMonth;
        relPrg = binding.relFragmentHomePrg;
        userName = MySharedPreferences.readUserName(getContext());
        checkActivateMonths();
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpNavigationListView();
        showDate();

        if (userName != null) {
            txtSignIn.setText(userName);
        }

        imgHamberMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = MySharedPreferences.readUserName(getContext());

                if (userName == null) {
                    txtSignIn.setText(R.string.t3);
                    linearSignOutAcount.setVisibility(View.GONE);
                    drawerLayout.openDrawer(Gravity.RIGHT);
                } else {
                    if (linearSignOutAcount.getVisibility() == View.VISIBLE) {
                        linearSignOutAcount.setVisibility(View.GONE);
                    }
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }


            }
        });

        imgListReservation.setOnClickListener(v -> {
            String userName = MySharedPreferences.readUserName(getContext());
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                if (userName != null) {
                    Navigation.findNavController(imgListReservation).navigate(R.id.action_fragmentHome_to_fragmentReservation);
                } else {
                    Toast.makeText(getContext(), "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }


        });
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conditionLogin = txtSignIn.getText().toString();
                if (conditionLogin.equals("ورود به حساب کاربری")) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Navigation.findNavController(txtSignIn).navigate(R.id.action_fragmentHome_to_fragmentLoginAcount);

                } else {
                    if (linearSignOutAcount.getVisibility() == View.GONE) {
                        linearSignOutAcount.setVisibility(View.VISIBLE);
                    } else {
                        linearSignOutAcount.setVisibility(View.GONE);
                    }
                }
            }
        });
        txtExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySharedPreferences.removeUserName(getContext());

                txtSignIn.setText("ورود به حساب کاربری");
                linearSignOutAcount.setVisibility(View.GONE);

                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        onClickListenerGridLayout(gridLayoutMonth);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBackPressed();


    }

    @Override
    public void click(int position) {
        String userName = MySharedPreferences.readUserName(getContext());
        switch (position) {
            case 0:
               showDialogGoToPanel();
                break;

            case 1:

                if (userName != null) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentLastCustomers);


                } else {
                    Toast.makeText(getContext(), "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();
                }

                break;


            case 2:
                if (userName != null) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentChangePass);


                } else {
                    Toast.makeText(getContext(), "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (userName != null) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentCustomerBlackList);
                } else {
                    Toast.makeText(getContext(), "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentLockApp);
                break;
            case 5:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentAboutWe);
                break;
            case 6:
                drawerLayout.closeDrawer(Gravity.RIGHT);
                Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentErtebatBaMa);
                break;

        }


    }

    private void setUpNavigationListView() {
        menus = new ArrayList<>();
        menus.add(new NavigationMenu(R.string.t6, R.drawable.c_panel_icon));
        menus.add(new NavigationMenu(R.string.t7, R.drawable.information_contact_icon));
        menus.add(new NavigationMenu(R.string.t8, R.drawable.change_user_pass_icon));
        menus.add(new NavigationMenu(R.string.t9, R.drawable.red_block_person));
        menus.add(new NavigationMenu(R.string.t12, R.drawable.ic_lock_app));
        menus.add(new NavigationMenu(R.string.t10, R.drawable.red_about_me));
        menus.add(new NavigationMenu(R.string.t11, R.drawable.red_contact_use));

        NavigationMenuAdapter adapter = new NavigationMenuAdapter(getContext(), menus, FragmentHome.this);
        recyclerViewNavigationMenu.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerViewNavigationMenu.setHasFixedSize(true);
        recyclerViewNavigationMenu.setAdapter(adapter);

    }

    private void onBackPressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);

                } else {

                    if (doubleBackToExitPressedOnce) {
                        requireActivity().finish();

                        return;
                    }
                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(getContext(), "لطفا کلید بازگشت را مجددا فشار دهید", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);
                }

            }
        });
    }

    private void showDate() {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                changeTime();
            }
        }, 0, 1000);

    }


    private void changeTime() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String date = MyUtils.number2persian(new PersianDateFormat("l j F Y \n H:i:s").format(new PersianDate()));
                txtDataHeader.setText(date);
            }
        });
    }


    private void onClickListenerGridLayout(GridLayout gridLayout) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            CardView cardView = (CardView) gridLayout.getChildAt(i);
            int indexId = i + 1;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userName = MySharedPreferences.readUserName(getContext());


                    if (userName != null) {

                        if (indexId == 1) {
                            if (farvardin.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentFarvardin);

                            } else {
                                showDialog();
                            }
                        }

                        if (indexId == 2) {
                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (ordibehesht.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentOrdibehesht);
                                } else {
                                    showDialog();
                                }

                            }
                        }

                        if (indexId == 3) {
                            if (khordad.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentKhordad);
                            } else {
                                showDialog();
                            }
                        }

                        if (indexId == 4) {

                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (tir.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentTir);
                                } else {
                                    showDialog();
                                }

                            }
                        }

                        if (indexId == 5) {
                            if (mordad.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentMordad);

                            } else {
                                showDialog();
                            }
                        }

                        if (indexId == 6) {

                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (shahrivar.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentShahrivar);

                                } else {
                                    showDialog();
                                }

                            }
                        }

                        if (indexId == 7) {
                            if (mehr.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentMehr);
                            } else {
                                showDialog();
                            }
                        }

                        if (indexId == 8) {

                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (aban.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentAban);

                                } else {
                                    showDialog();
                                }

                            }
                        }

                        if (indexId == 9) {
                            if (azar.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentAzar);

                            } else {
                                showDialog();
                            }

                        }

                        if (indexId == 10) {

                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (dey.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentDey);

                                } else {
                                    showDialog();
                                }

                            }
                        }

                        if (indexId == 11) {
                            if (bahman.equals("1")) {
                                Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentBahman);

                            } else {
                                showDialog();
                            }
                        }

                        if (indexId == 12) {
                            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                            } else {
                                if (esfand.equals("1")) {
                                    Navigation.findNavController(cardView).navigate(R.id.action_fragmentHome_to_fragmentEsfand);

                                } else {
                                    showDialog();
                                }

                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();
                        drawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
            });
        }
    }

    private void checkActivateMonths() {
        relPrg.setVisibility(View.VISIBLE);
        MutableLiveData<ActivateMonths> liveData = new ViewModelProvider(requireActivity()).get(MonthsVM.class).checkActivateMonths();
        liveData.observe(requireActivity(), new Observer<ActivateMonths>() {
            @Override
            public void onChanged(ActivateMonths activateMonths) {
                relPrg.setVisibility(View.GONE);
                farvardin = activateMonths.getFarvardin();
                ordibehesht = activateMonths.getOrdibehesht();
                khordad = activateMonths.getKhordad();
                tir = activateMonths.getTir();
                mordad = activateMonths.getMordad();
                shahrivar = activateMonths.getShahrivar();
                mehr = activateMonths.getMehr();
                aban = activateMonths.getAban();
                azar = activateMonths.getAzar();
                dey = activateMonths.getDey();
                bahman = activateMonths.getBahman();
                esfand = activateMonths.getEsfand();

            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_mesage_inactive_month);
        Dialog dialog = builder.create();
        builder.setCancelable(false);
        dialog.show();
        CardView btnClose = dialog.findViewById(R.id.cardDialog_customLayoutMesageInactiveMonth_btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void showDialogGoToPanel(){
        drawerLayout.closeDrawer(Gravity.RIGHT);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_go_to_manage_panel);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        EditText edtPass = dialog.findViewById(R.id.edt_dialogGoToPanel_edtPass);
        CardView btnCancel = dialog.findViewById(R.id.cardDialog_custom_dialogGoToPanel_btn_cancel);
        CardView btnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialogGoToPanel_btn_confirm);
        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnConfirm.setOnClickListener(v -> {

            String pass = MyUtils.persianToEnglish(edtPass.getText().toString());
            if (pass.isEmpty()) {
                Toast.makeText(getContext(), "رمز ورود را وارد کنید!", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(PANEL_PASSWORD)) {
                    dialog.dismiss();
                    Navigation.findNavController(recyclerViewNavigationMenu).navigate(R.id.action_fragmentHome_to_fragmentManagePanel);
                }else {
                    Toast.makeText(getContext(), "رمز ورود اشتباه است!", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }

}


