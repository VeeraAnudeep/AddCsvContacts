package com.veera.addcontacts.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.veera.addcontacts.marshmallowPermissions.ActivityManagePermission;


public abstract class MVPBaseActivity<P extends MVPBasePresenter> extends ActivityManagePermission implements MVPBaseView<P> {

    boolean isDestroyed;
    private P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getNewPresenter();
        getPresenter().bindView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        getPresenter().unBindView();
    }

    @Override
    public boolean isAlive() {
        return !isFinishing() && !isDestroyed;
    }

    public final P getPresenter() {
        return presenter;
    }
}
