package cn.edu.scau.common_scau.assemble;

/**
 * Created by QiHuang on 2018/4/7.
 */

public interface ICommonView {

    void showNoNet();

    void showServerErr();

    void showLoading();

    void dismissAssembleView();

    void reload();
}
