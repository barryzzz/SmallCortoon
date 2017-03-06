package xi.lsl.code.lib.utils.base;

/**
 * Description:
 * Author   :lishoulin
 * Date     :2017/2/8.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showloading();

    void dissloading();

}
