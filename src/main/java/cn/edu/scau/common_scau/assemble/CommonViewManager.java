package cn.edu.scau.common_scau.assemble;

import android.content.Context;

import java.util.HashMap;

import cn.edu.scau.common_util.Supplier;

/**
 * Created by QiHuang on 2018/1/18.
 */

public class CommonViewManager {
    private static HashMap<String, CommonAssembleView> ASSEMBLE_VIEW_MAP = new HashMap<>();
    private static boolean IS_INIT = false;

    static void init(final Context context) {
        if (IS_INIT) {
            return;
        }
        register(CommonViewConstants.NO_NET, new Supplier<CommonAssembleView>() {
            @Override
            public CommonAssembleView get() {
                return new CommonNoNetView(context);
            }
        });

        register(CommonViewConstants.LOADING, new Supplier<CommonAssembleView>() {
            @Override
            public CommonAssembleView get() {
                return new CommonLoadingView(context);
            }
        });

        register(CommonViewConstants.SEVER_ERR, new Supplier<CommonAssembleView>() {
            @Override
            public CommonAssembleView get() {
                return new CommonServerErrorView(context);
            }
        });
        IS_INIT = true;

    }

    static void register(String tag, Supplier<CommonAssembleView> supplier) {
        if (supplier == null) {
            return;
        }

        CommonAssembleView assembleView = supplier.get();
        if (assembleView == null) {
            return;
        }

        ASSEMBLE_VIEW_MAP.put(tag, assembleView);
    }

    static CommonAssembleView get(String tag) {
        return ASSEMBLE_VIEW_MAP.get(tag);
    }

    static void destroy() {
        ASSEMBLE_VIEW_MAP.clear();
        IS_INIT = false;
    }
}
