package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by QiHuang on 2018/1/18.
 */

public class CommonAssembleLayout extends RelativeLayout {
    private Context mContext;
    protected CommonAssembleView mAssembleView;
    private boolean isShow;

    public CommonAssembleLayout(Context context) {
        super(context);
        init(context);
    }

    public CommonAssembleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    public CommonAssembleView show(String viewType) {
        if (TextUtils.isEmpty(viewType)) {
            throw new IllegalArgumentException("view type can no be null");
        }

        CommonViewManager.init(getContext());

        if (mAssembleView != null) {
            removeView(mAssembleView);
            mAssembleView = null;
        }
        mAssembleView = CommonViewFactory.get(viewType, mContext);
        if (mAssembleView.getParent() != null) {
            ViewGroup parent = (ViewGroup) mAssembleView.getParent();
            parent.removeView(mAssembleView);
        }
        mAssembleView.setVisibility(VISIBLE);

        setContentViewVisibility(true);

        mAssembleView.setLayoutParams(new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mAssembleView);
        mAssembleView.onViewCreated();

        isShow = true;

        return mAssembleView;
    }

    public void dismissView() {
        if (mAssembleView != null && mAssembleView.getVisibility() == VISIBLE) {
            mAssembleView.onViewDestroy();
            removeView(mAssembleView);
            mAssembleView = null;
            isShow = false;
        }
        setContentViewVisibility(false);
    }

    private void setContentViewVisibility(boolean hide) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.setVisibility(hide? GONE : VISIBLE);
        }
    }


    public boolean isShow() {
        return isShow;
    }
}
