package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by QiHuang on 2018/1/18.
 */

public abstract class CommonAssembleView extends RelativeLayout {
    protected OnCommonViewClickListener mListener;

    public CommonAssembleView(Context context) {
        super(context);
        init(context);
    }

    public CommonAssembleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        View contentView = LayoutInflater.from(context).inflate(getCommonViewId(), this, false);
        contentView.setLayoutParams(new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(contentView);
    }

    public void onViewCreated() {
    }

    public void onViewDestroy() {

    }


    protected abstract int getCommonViewId();

    public void handle(OnCommonViewClickListener l) {
        mListener = l;
    }

    public interface OnCommonViewClickListener {
        void onCommonViewClick();
    }
}
