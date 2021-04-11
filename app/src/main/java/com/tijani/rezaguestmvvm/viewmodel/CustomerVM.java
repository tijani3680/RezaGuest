package com.tijani.rezaguestmvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tijani.rezaguestmvvm.model.BlockCustomer;
import com.tijani.rezaguestmvvm.model.CheckLogin;
import com.tijani.rezaguestmvvm.model.LastCustomer;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.repository.ApiService;
import com.tijani.rezaguestmvvm.repository.Repository;
import com.tijani.rezaguestmvvm.repository.SingletonDisposable;

import java.util.List;

public class CustomerVM extends ViewModel {

    public MutableLiveData<Status> addToBlackListLiveData;

    private MutableLiveData<List<LastCustomer>> lastCustomerLiveData;

    public MutableLiveData<Status> deleteFromBlackListLiveData;

    private MutableLiveData<List<BlockCustomer>> blockCustomerLiveData;

    private MutableLiveData<Status> customerConditionLiveData;

    public MutableLiveData<CheckLogin> checkLoginLiveData;
    public MutableLiveData<Status> createOperatorLiveData;


    public MutableLiveData<List<LastCustomer>> getLastCustomer() {
        lastCustomerLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getLastCustomers(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                lastCustomerLiveData.setValue((List) object);
            }
        });


        return lastCustomerLiveData;
    }


    public void addToBlackList(String customerId, String description) {

        addToBlackListLiveData = new MutableLiveData<>();

        Repository.INSTANCE.callRequest(ApiService.client.getClient().addToBlackList(customerId, description), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {

                addToBlackListLiveData.setValue((Status) object);
            }
        });

    }

    public void deleteFromBlackList(String id) {
        deleteFromBlackListLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().deleteCustomerFromBlackList(id), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {

                deleteFromBlackListLiveData.setValue((Status) object);

            }
        });
    }


    public MutableLiveData<List<BlockCustomer>> getBlockCustomer() {
        blockCustomerLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().getBlockCustomer(), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                blockCustomerLiveData.setValue((List) object);
            }
        });


        return blockCustomerLiveData;
    }


    public MutableLiveData<Status> checkConditionCustomer(String shenaseh) {
        customerConditionLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.client.getClient().checkConditionCustomer(shenaseh), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                customerConditionLiveData.setValue((Status) object);

            }
        });
        return customerConditionLiveData;
    }


    public void checkLogin(String userName, String password) {
        checkLoginLiveData = new MutableLiveData<>();

        Repository.INSTANCE.callRequest(ApiService.client.getClient().loginAcount(userName, password), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                CheckLogin checkLogin = (CheckLogin) object;
                checkLoginLiveData.setValue(checkLogin);

            }
        });


    }

    public void createOperator(String fullName,String code,String userName,String password){
        createOperatorLiveData = new MutableLiveData<>();
        Repository.INSTANCE.callRequest(ApiService.ApiClient.CLIENT.getClient().addOperator(fullName, code, userName, password), SingletonDisposable.getInstance(), new Repository.Unit() {
            @Override
            public void call(Object object) {
                createOperatorLiveData.setValue((Status)object);

            }
        });
    }


    @Override
    protected void onCleared() {
        SingletonDisposable.getInstance().clear();
        super.onCleared();
    }
}
