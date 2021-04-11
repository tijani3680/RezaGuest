package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.model.UserPass;
import com.tijani.rezaguestmvvm.model.Users;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

import java.util.List;

public class OperatorVM extends ViewModel {
    private MutableLiveData<List<Users>> userLiveData;
    public MutableLiveData<Status> deleteOperatorLiveData;
    private MutableLiveData<UserPass> getUserPassLiveData;
    private MutableLiveData<Status> changeUserPassLiveData;

    public MutableLiveData<List<Users>>  getOperators(){
        userLiveData =  new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getOperators(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                userLiveData.setValue((List)object);
            }
        });

        return userLiveData;
    }

    public void deleteOperator(String id){
        deleteOperatorLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().deleteOperator(id), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {

                deleteOperatorLiveData.setValue((Status) object);

            }
        }) ;



    }

    public MutableLiveData<UserPass> getUserPass(String userName){
        getUserPassLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getUserPass(userName), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                getUserPassLiveData.setValue((UserPass)object);
            }
        });

        return getUserPassLiveData;
    }

    public MutableLiveData<Status> changeUserPass(String oldUserName,String newUserName,String newPassword){
        changeUserPassLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().changeUserPass(oldUserName, newUserName, newPassword), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                changeUserPassLiveData.setValue((Status)object);
            }
        });

        return changeUserPassLiveData;
    }

    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
