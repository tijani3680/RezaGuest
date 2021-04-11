package com.tijani.rezaguestmvvm.repository;

import android.util.Log;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public final class Repository {

    public static final Repository INSTANCE;

        static {
            INSTANCE = new Repository();
        }


        public interface Unit{
             void call(Object object);
        }

        public final void callRequest (Single api, CompositeDisposable compositeDisposable,final Unit unit){
            compositeDisposable.add((Disposable)
                    api.subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new DisposableSingleObserver() {
                                @Override
                                public void onSuccess(@NonNull Object o) {
                                    unit.call(o);

                                }

                                @Override
                                public void onError(@NonNull Throwable e) {

                                    Log.d("LOG",e.getMessage());

                                }
                            })


            );
        }
}
