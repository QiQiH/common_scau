package cn.edu.scau.common_scau;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.edu.scau.common_scau.assemble.CommonAssembleEvent;
import cn.edu.scau.common_scau.assemble.CommonAssembleLayout;
import cn.edu.scau.common_scau.assemble.CommonAssembleView;
import cn.edu.scau.common_scau.assemble.CommonViewConstants;
import cn.edu.scau.common_scau.assemble.ICommonView;

/**
 * Created by QiHuang on 2018/1/20.
 */

public abstract class ScauCommonAssembleActivity extends ScauBaseActivity implements ICommonView {
    protected CommonAssembleLayout mAssembleLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        EventBus.getDefault().register(this);
        int id = initAssembleLayoutId();
        if (id <= 0) {
            throw new IllegalArgumentException("id can no less then 0");
        }
        mAssembleLayout = (CommonAssembleLayout) findViewById(id);
    }

    protected abstract int layoutId();

    protected abstract int initAssembleLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        isDestroy = true;
    }

    @Subscribe
    public void onEvent(CommonAssembleEvent event) {
        if (!isResumed) {
            return;
        }
        String type = event.getType();
        if (CommonViewConstants.NO_NET.equals(type)) {
            showNoNet();
        } else if (CommonViewConstants.SEVER_ERR.equals(type)){
            showServerErr();
        }
    }

    @Override
    public void showLoading() {
        mAssembleLayout.show(CommonViewConstants.LOADING);
    }

    @Override
    public void showNoNet() {
        mAssembleLayout.show(CommonViewConstants.NO_NET)
                .handle(new CommonAssembleView.OnCommonViewClickListener() {
                    @Override
                    public void onCommonViewClick() {
                        reload();
                    }
                });
    }

    @Override
    public void showServerErr() {
        mAssembleLayout.show(CommonViewConstants.SEVER_ERR)
                .handle(new CommonAssembleView.OnCommonViewClickListener() {
                    @Override
                    public void onCommonViewClick() {
                        reload();
                    }
                });
    }

    @Override
    public void dismissAssembleView() {
        mAssembleLayout.dismissView();
    }

    @Override
    public void reload() {

    }

}
