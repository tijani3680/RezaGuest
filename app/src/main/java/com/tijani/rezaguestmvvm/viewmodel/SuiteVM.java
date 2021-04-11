package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tijani.rezaguestmvvm.model.ActivateSuites;
import com.tijani.rezaguestmvvm.model.PriceSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

import java.util.List;

public class SuiteVM extends ViewModel {
    private MutableLiveData<ActivateSuites> checkActivateSuiteLiveData;
    private MutableLiveData<Status> activateSuiteLiveData;
    private MutableLiveData<Status> inActiveSuiteLiveData;
    private MutableLiveData<List<PriceSuites>> suitePriceLiveData;
    private MutableLiveData<Status> updatePriceLiveData;




    public MutableLiveData<ActivateSuites> checkActivateSuite(){
            checkActivateSuiteLiveData = new MutableLiveData<>();
            Repository.INSTANCE.callRequest(ApiService.client.getClient().checkActivateSuites(), SingletonDisposable.getInstance(), new Repository.Unit() {
                @Override
                public void call(Object object) {
                    checkActivateSuiteLiveData.setValue((ActivateSuites)object);
                }
            });
        return checkActivateSuiteLiveData;

    }

    public MutableLiveData<Status> activateSuite(String suite){
        activateSuiteLiveData = new MutableLiveData<>();
        Repository.INSTANCE .callRequest(ApiService.client.getClient().activateSuite(suite), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                activateSuiteLiveData.setValue((Status) object);
            }
        });

        return activateSuiteLiveData;
    }
    public MutableLiveData<Status> inActiveSuite(String suite){
        inActiveSuiteLiveData = new MutableLiveData<>();
        Repository.INSTANCE .callRequest(ApiService.client.getClient().inActiveSuite(suite), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                inActiveSuiteLiveData.setValue((Status) object);
            }
        });

        return inActiveSuiteLiveData;
    }

    public MutableLiveData<List<PriceSuites>> getSuitePrice(){
        suitePriceLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getPriceSuites(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                suitePriceLiveData.setValue((List) object);
            }
        });
        return suitePriceLiveData;
    }

    public MutableLiveData<Status> updatePrice(String id,String spacialPrice,String spacialMazadPrice,String price,String  mazadPrice){
        updatePriceLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().updaptePrice(id, spacialPrice, spacialMazadPrice, price, mazadPrice), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                updatePriceLiveData.setValue((Status) object);
            }
        });

        return updatePriceLiveData;
    }

    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
