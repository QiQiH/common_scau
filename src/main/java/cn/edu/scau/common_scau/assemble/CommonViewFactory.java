package cn.edu.scau.common_scau.assemble;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import cn.edu.scau.common_util.Supplier;

/**
 * Created by QiHuang on 2018/3/22.
 */

public class CommonViewFactory {
    private static HashMap<String, Class> ASSEMBLE_VIEW_MAP = new HashMap<>();


    static {
        register(CommonViewConstants.LOADING, new Supplier<Class<? extends CommonAssembleView>>() {
            @Override
            public Class get() {
                return CommonLoadingView.class;
            }
        });

        register(CommonViewConstants.NO_NET, new Supplier<Class<? extends CommonAssembleView>>() {
            @Override
            public Class get() {
                return CommonNoNetView.class;
            }
        });

        register(CommonViewConstants.SEVER_ERR, new Supplier<Class<? extends CommonAssembleView>>() {
            @Override
            public Class get() {
                return CommonServerErrorView.class;
            }
        });
    }

    public static void register(String tag, Supplier<Class<? extends CommonAssembleView>> supplier) {
        if (supplier == null) {
            return;
        }

        Class assembleView = supplier.get();
        if (assembleView == null) {
            return;
        }

        ASSEMBLE_VIEW_MAP.put(tag, assembleView);
    }


    public static CommonAssembleView get(String type, Context context) {
        if (TextUtils.isEmpty(type)) {
            return null;
        }

        Class<? extends CommonAssembleView> cls = ASSEMBLE_VIEW_MAP.get(type);
        if (cls == null) {
            return null;
        }

        try {
            Constructor c = cls.getDeclaredConstructor(Context.class);
            if (c == null) {
                return null;
            }
            return (CommonAssembleView) c.newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
