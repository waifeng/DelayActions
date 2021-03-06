package com.tubb.delayactions.test;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.tubb.delayactions.PremiseAction;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by tubingbing on 2017/12/18.
 */

public class LoginPremiseAction implements PremiseAction {
    private Activity mContext;
    public LoginPremiseAction(Activity context) {
        mContext = context;
    }

    @Override
    public boolean isPremiseCheckAsync() {
        return true;
    }

    @NonNull
    @Override
    public Observable<Boolean> finished() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                boolean isLogin = UserInfoCache.isLogin(mContext);
                e.onNext(isLogin);
            }
        });
    }

    @Override
    public void execute() {
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
    }
}
