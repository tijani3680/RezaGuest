package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tijani.rezaguestmvvm.model.ActivateMonths;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

public class MonthsVM extends ViewModel {
    private MutableLiveData<ActivateMonths> checkActivateMonthsLiveData;
    private MutableLiveData<Status> activeMonthLiveData;
    private MutableLiveData<Status> inActiveMonthLiveData;
    private MutableLiveData<Status> resetMonthLiveData;




    public MutableLiveData<ActivateMonths> checkActivateMonths(){
            checkActivateMonthsLiveData = new MutableLiveData<>();
            Repository.INSTANCE.callRequest(ApiService.client.getClient().checkActivateMonths(), SingletonDisposable.getInstance(), new Repository.Unit() {
                @Override
                public void call(Object object) {
                    checkActivateMonthsLiveData.setValue((ActivateMonths)object);
                }
            });

        return checkActivateMonthsLiveData;


    }

    public MutableLiveData<Status> activateMonth(String month){
        activeMonthLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().activateMonth(month), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                activeMonthLiveData.setValue((Status) object);
            }
        });

        return activeMonthLiveData;
    }

    public MutableLiveData<Status> inActiveMonth(String month){
        inActiveMonthLiveData= new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().inActiveMonth(month), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                inActiveMonthLiveData.setValue((Status) object);
            }
        });

        return inActiveMonthLiveData;
    }

    public MutableLiveData<Status> reset(String month){
        resetMonthLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().reset(month), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                resetMonthLiveData.setValue((Status) object);
            }
        });

        return resetMonthLiveData;
    }




    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
