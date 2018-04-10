package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.view.View;

import cn.edu.scau.common_scau.R;

/**
 * Created by QiHuang on 2018/1/18.
 */

public class CommonNoNetView extends CommonAssembleView {
    public CommonNoNetView(Context context) {
        super(context);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        findViewById(R.id.btn_no_net).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCommonViewClick();
                }
            }
        });
    }

    @Override
    protected int getCommonViewId() {
        return R.layout.common_no_net_view;
    }
}
