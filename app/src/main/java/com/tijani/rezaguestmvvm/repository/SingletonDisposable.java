package com.tijani.rezaguestmvvm.repository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public final class SingletonDisposable {
    public static CompositeDisposable instance;

    public static final CompositeDisposable getInstance() {
        if (instance == null) {
            instance = new CompositeDisposable();
        }

        return instance;
    }

}
