package com.veera.addcontacts.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

public abstract class MVPBaseFragment<P extends MVPBasePresenter> extends Fragment implements MVPBaseView<P> {

    private P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getNewPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().bindView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().unBindView();
    }

    @Override
    public boolean isAlive() {
        return getActivity() != null
                && isAdded();
    }

    public final P getPresenter() {
        return presenter;
    }

}
