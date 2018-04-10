package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.view.View;

import com.wang.avi.AVLoadingIndicatorView;

import cn.edu.scau.common_scau.R;

/**
 * Created by QiHuang on 2018/1/19.
 */

public class CommonLoadingView extends CommonAssembleView {
    private AVLoadingIndicatorView mLoadingView;

    public CommonLoadingView(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        mLoadingView = (AVLoadingIndicatorView) findViewById(R.id.common_loading_view);
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();
        mLoadingView.show();
    }

    @Override
    public void onViewDestroy() {
        super.onViewDestroy();
        mLoadingView.hide();
    }

    @Override
    protected int getCommonViewId() {
        return R.layout.common_loading_view;
    }
}
