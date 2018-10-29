package com.gank.chen.mvp.presenter;

import android.content.Context;

import com.gank.chen.base.BasePrestener;
import com.gank.chen.http.ApiRetrofit;
import com.gank.chen.http.subscriber.ApiSubscriberFlowable;
import com.gank.chen.mvp.model.MeiZi;
import com.gank.chen.mvp.view.OnLoadSuccessViewImp;
import com.gank.chen.widget.StateView;

import java.util.List;

/**
 * Created by chen on 2017/12/17
 * @author chenbo
 */

public class MinePresenter extends BasePrestener<OnLoadSuccessViewImp<List<MeiZi>>> {

    public void getGankData(Context context, StateView stateView, int page) {

        ApiRetrofit.setFlowableSubscribe(apiUtil.getGankData(page), new ApiSubscriberFlowable<List<MeiZi>>(context, stateView) {
            @Override
            public void onSuccess(List<MeiZi> meiZiList) {
                if (meiZiList != null && getView() != null) {
                    getView().onLoadSucess(meiZiList);
                }
            }
        });
    }

}
