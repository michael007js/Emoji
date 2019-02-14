package share.sss.com.emoji.lib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import share.sss.com.emoji.lib.R;

/**
 * 基类，本层对根布局做了解析
 * Created by Administrator on 2019/2/13.
 */

public abstract class BaseInflaterDialogFragment extends DialogFragment {
    private View baseInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());
        baseInput=LayoutInflater.from(getContext()).inflate(R.layout.base_input,null);
        return baseInput;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
    }

    /**
     *  获取根布局
     * @return
     */
    public View getBaseInput() {
        return baseInput;
    }

    /**
     * 获取点击其他区域是否关闭
     *
     * @return
     */
    public boolean getCancelOutside() {
        return true;
    }


    /**
     * 绑定的view处理
     *
     * @param v
     */
    public abstract void bindView(View v);
}
