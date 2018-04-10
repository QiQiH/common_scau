package cn.edu.scau.common_scau.view;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.scau.common_scau.R;
import cn.edu.scau.common_util.DensityUtil;

/**
 * Created by QiHuang on 2018/1/19.
 */

public class ScauTitleBar extends RelativeLayout {
    private ViewGroup mTitleBarView;

    private ImageView mBackIv;
    private TextView mTitleTv;
    private ImageView mMoreIv;
    private RelativeLayout mCenterView;

    private View mBottomLine;

    private int mTitleColor;
    private String mTitleText;
    private int mBgColor;
    private boolean showBack;
    private float mTitleBarAlpha;
    private boolean showMore;
    private boolean mShowBottomLine;

    private View line;

    private int mBackArrowRes;

    private Context mContext;

    private OnBackListener mBackListener;

    private boolean isHide;


    public ScauTitleBar(Context context) {
        super(context);
        init(context);
    }

    public ScauTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScauTitleBar);

        mTitleText = a.getString(R.styleable.ScauTitleBar_titleText);
        mTitleColor = a.getColor(R.styleable.ScauTitleBar_titleTextColor, 0xff000000);
        mBgColor = a.getColor(R.styleable.ScauTitleBar_backgroundColor, 0xffffffff);
        showBack = a.getBoolean(R.styleable.ScauTitleBar_showBackItem, true);
        showMore = a.getBoolean(R.styleable.ScauTitleBar_showMoreItem, false);
        mBackArrowRes = a.getResourceId(R.styleable.ScauTitleBar_back_arrow_img, 0);
        mTitleBarAlpha = a.getFloat(R.styleable.ScauTitleBar_bgAlpha, 1);
        mShowBottomLine = a.getBoolean(R.styleable.ScauTitleBar_showBottomLine, true);

        a.recycle();
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initViews();
    }

    public void setBackListener(OnBackListener backListener) {
        mBackListener = backListener;
    }

    private void initViews() {

        mTitleBarView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.title_bar_view, this, false);
        mBackIv = (ImageView) mTitleBarView.findViewById(R.id.title_bar_back_iv);
        line = mTitleBarView.findViewById(R.id.line);

        mTitleTv = (TextView) mTitleBarView.findViewById(R.id.title_bar_title);
        mMoreIv = (ImageView) mTitleBarView.findViewById(R.id.title_bar_more_iv);
        mMoreIv.setVisibility(GONE);
        mCenterView = (RelativeLayout) mTitleBarView.findViewById(R.id.title_center_custom_layout);

        mBottomLine = mTitleBarView.findViewById(R.id.title_bar_bottom_line);
        mBottomLine.setVisibility(mShowBottomLine? VISIBLE : GONE);
        setBackgroundColor(mBgColor);
        if (mBackArrowRes != 0) {
            mBackIv.setImageResource(mBackArrowRes);
        } else {
            mBackIv.setImageResource(R.drawable.back);
        }
        mBackIv.setBackgroundColor(mBgColor);

        mTitleTv.setText(mTitleText);
        mTitleTv.setTextColor(mTitleColor);
        mBackIv.setVisibility(showBack? VISIBLE : GONE);
        mMoreIv.setVisibility(showMore? VISIBLE : GONE);
        line.setVisibility(showBack? VISIBLE : GONE);

        mBackIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBackListener != null) {
                    mBackListener.onBackClick();
                    return;
                }
                ((Activity)mContext).finish();
            }
        });

        addView(mTitleBarView, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(48)));
    }

    public void showBackItem(boolean show) {
        mBackIv.setVisibility(show? VISIBLE : GONE);
    }

    public void setTitle(String text) {
        if (!TextUtils.isEmpty(text) && mTitleTv.getVisibility() == VISIBLE) {
            mTitleTv.setText(text);
        }
    }


    public void setCenterView(View contentView) {
        mCenterView.addView(contentView, new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (mTitleTv.getVisibility() == VISIBLE) {
            mTitleTv.setVisibility(GONE);
        }
    }

    public void setTitleColor(int color) {
        mTitleTv.setTextColor(color);
    }


    public void hide() {
        if (isHide) {
            return;
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY", 0, -getHeight());
        animator.setDuration(200).start();
        animator.addListener(new AnimatorListenerAdapter() {
        });
        isHide = true;
        setVisibility(GONE);
    }

    public void show() {
        if (!isHide) {
            return;
        }

        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY", -getHeight(), 0);
        animator.setDuration(200).start();
        isHide = false;
        setVisibility(VISIBLE);
    }



    public void setTitleBarAlpha(float alpha) {
        mTitleBarView.setAlpha(alpha);
    }

    public interface OnBackListener {
        void onBackClick();
    }
}
