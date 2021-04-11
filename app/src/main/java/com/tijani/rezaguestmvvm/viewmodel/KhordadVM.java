package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tijani.rezaguestmvvm.model.DetailsReserv;
import com.tijani.rezaguestmvvm.model.ReservInformation;
import com.tijani.rezaguestmvvm.model.ReservSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

import java.util.List;

public class KhordadVM  extends ViewModel {

    private MutableLiveData<List<ReservSuites>> reservSuiteLiveData;

    public MutableLiveData<Status> saveReservLiveData;
    private MutableLiveData<List<ReservInformation>> allReservLiveData;

    public MutableLiveData<DetailsReserv> detailsReservLiveData;
    public MutableLiveData<Status> updateCoastReceivedLiveData;
    public MutableLiveData<Status> deleteReservCustomerLiveData;


    public MutableLiveData<List<ReservSuites>> getReservSuiteLiveData(){
        reservSuiteLiveData = new MutableLiveData<>();

        Repository.INSTANCE.callRequest(ApiService.client.getClient().getReservKhordad(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                reservSuiteLiveData.setValue((List)object);
            }
        });

        return reservSuiteLiveData;
    }


    public void saveReserv(String fullName, String phone, String customerId, int nafaratAvaliyeh, int nafaratMazad, int finalCoast, int coastReceived,
                           String numberFish, String reservistPerson, String resSuite1, String resSuite2, String resSuite3, String resSuite4, String resSuite5,
                           String resSuite6, String resSuite7, String resSuite8, String resSuite9, String resSuite10, String resSuite11, String resSuite12) {

        saveReservLiveData = new MutableLiveData<>();

        Repository.INSTANCE.callRequest(ApiService.client.getClient().sendReservInformationKhordad(fullName,phone, customerId,nafaratAvaliyeh,nafaratMazad,finalCoast,coastReceived,numberFish,reservistPerson, resSuite1, resSuite2, resSuite3,
                resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                saveReservLiveData.setValue((Status) object);
            }
        });

    }

    public MutableLiveData<List<ReservInformation>> getAllReserv(){
        allReservLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getAllReservKhordad(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                allReservLiveData.setValue((List)object);
            }
        });

        return allReservLiveData;
    }



    public void getDetailsReserv(String id){
        detailsReservLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getDetailsReservKhordad(id), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                detailsReservLiveData.setValue((DetailsReserv)object);
            }
        });
    }

    public void updateCoastReceived(String id,String money){
        updateCoastReceivedLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().updateCoastReceivedKhordad(id, money), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {

                updateCoastReceivedLiveData.setValue((Status)object);

            }
        });
    }

    public void deleteReservCustomer(String id){
        deleteReservCustomerLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().deleteReservCustomerKhordad(id), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                deleteReservCustomerLiveData.setValue((Status)object);
            }
        });
    }




    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
