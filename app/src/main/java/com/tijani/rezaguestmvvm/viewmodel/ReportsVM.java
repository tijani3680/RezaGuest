package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tijani.rezaguestmvvm.model.Reports;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

public class ReportsVM extends ViewModel {
    MutableLiveData<Reports> reportsLiveData;

    public MutableLiveData<Reports> getReports(){
            reportsLiveData = new MutableLiveData<>();
            Repository.INSTANCE.callRequest(ApiService.client.getClient().getReports(), SingletonDisposable.getInstance(), new Repository.Unit() {
                @Override
                public void call(Object object) {
                    reportsLiveData.setValue((Reports) object);
                }
            });

        return reportsLiveData;
    }

    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
