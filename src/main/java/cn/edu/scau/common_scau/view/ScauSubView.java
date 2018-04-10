package cn.edu.scau.common_scau.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by QiHuang on 2018/2/6.
 */

public abstract class ScauSubView extends RelativeLayout {
    protected Activity mActivity;
    protected View mContentView;

    public ScauSubView(Context context) {
        super(context);
        init();
    }

    public ScauSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    protected void init() {
        mActivity = (Activity) getContext();
        mContentView = LayoutInflater.from(getContext()).inflate(layoutId(), this, false);
        ViewGroup.LayoutParams lp = mContentView.getLayoutParams();
        lp.width = getContentWidth();
        lp.height = getContentHeight();
        addView(mContentView, lp);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        onViewCreate();
    }

    protected void onViewCreate() {
    }

    protected int getContentWidth() {
        return LayoutParams.MATCH_PARENT;
    }

    protected int getContentHeight() {
        return LayoutParams.WRAP_CONTENT;
    }

    protected abstract int layoutId();
}
