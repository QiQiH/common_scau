package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.edu.scau.common_scau.R;

/**
 * Created by QiHuang on 2018/2/8.
 */

public class CommonServerErrorView extends CommonAssembleView {
    public CommonServerErrorView(Context context) {
        super(context);
    }

    public CommonServerErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        findViewById(R.id.btn_server_err).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onCommonViewClick();
                }
            }
        });
    }

    @Override
    protected int getCommonViewId() {
        return R.layout.server_error_layout;
    }
}
