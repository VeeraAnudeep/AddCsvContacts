package com.veera.addcontacts.mvp;

public interface MVPBaseView<P> {

    /**
     * returns whether view is accepting requests to update ui
     *
     * @return
     */
    boolean isAlive();

    P getNewPresenter();

}