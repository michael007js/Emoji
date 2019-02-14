package share.sss.com.emoji.lib.base;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * 本层开放了各种功能方法/回调接口
 * Created by Administrator on 2019/2/13.
 */

public abstract class BaseFunctionDialogFragment extends BaseInflaterDialogFragment {
    /**
     * TAG
     */
    private static final String TAG = BaseFunctionDialogFragment.class.getSimpleName();

    /**
     * 默认遮罩透明度
     */
    private static final float DEFAULT_MASK_TRANS = 0.2f;

    /**
     * 重力齐方式
     */
    private int mGravity = Gravity.BOTTOM;

    /**
     * 缩放程度
     */
    private float mScale = 1f;

    /**
     * 回调
     */
    private OnBaseDialogFragmentCallBack onBaseDialogFragmentCallBack;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyleRes());
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = getDimAmount();
        params.width = isWrapWindowWidth() ? WindowManager.LayoutParams.WRAP_CONTENT : getWidth();
        params.height = isWrapWindowHeight() ? WindowManager.LayoutParams.WRAP_CONTENT : getHeight();
        if (isGravity()) params.gravity = getGravity() == 0 ? mGravity : getGravity();
        window.setAttributes(params);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (onBaseDialogFragmentCallBack != null) {
            onBaseDialogFragmentCallBack.onShowComplete();
        }
    }

    /**
     * 获取高度
     *
     * @return
     */
    public int getHeight() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int height = point.y;
        return (int) (mScale * height);
    }

    /**
     * 获取宽度
     *
     * @return
     */
    public int getWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        return (int) (mScale * width);
    }

    /**
     * 设置重力齐方式
     *
     * @param gravity
     */
    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    /**
     * 设置缩放程度
     *
     * @param scale
     */
    public void setWidthScale(float scale) {
        this.mScale = scale;
    }

    /**
     * 获取遮罩透明度
     *
     * @return
     */
    public float getDimAmount() {
        return DEFAULT_MASK_TRANS;
    }

    /**
     * 页面宽度是否取屏幕宽度
     *
     * @return
     */
    public boolean isWrapWindowWidth() {
        return false;
    }

    /**
     * 页面宽度是否取屏幕宽度
     *
     * @return
     */
    public boolean isWrapWindowHeight() {
        return true;
    }

    /**
     * 对齐方式开关
     *
     * @return
     */
    public boolean isGravity() {
        return true;
    }


    /**
     * 获取对齐方式
     *
     * @return
     */
    public int getGravity() {
        return this.mGravity;
    }

    /**
     * 获取tag
     *
     * @return
     */
    public String getFragmentTag() {
        return TAG;
    }

    /**
     * 显示dialog
     *
     * @param fragmentManager
     * @return 显示成功返回true, 失败false
     */
    public boolean show(FragmentManager fragmentManager) {
        if (!isAdded()) {
            show(fragmentManager, TAG);
            return true;
        }
        return false;
    }

    /**
     * 带回调的显示dialog
     *
     * @param fragmentManager
     * @param onBaseDialogFragmentCallBack
     */
    public void show(FragmentManager fragmentManager, OnBaseDialogFragmentCallBack onBaseDialogFragmentCallBack) {
        this.onBaseDialogFragmentCallBack = onBaseDialogFragmentCallBack;
        show(fragmentManager);

    }

    /**
     * 获取主题
     *
     * @return
     */
    public abstract int getStyleRes();

    /**
     * 回调接口
     */
    public interface OnBaseDialogFragmentCallBack {

        /**
         * 窗口显示完毕
         */
        void onShowComplete();
    }
}
