package share.sss.com.emoji.lib.base;

import android.view.View;
import android.widget.FrameLayout;

import share.sss.com.emoji.lib.R;
import share.sss.com.emoji.lib.view.SoftInputLayout;

/**
 * 本层解析了必要的View并开放给使用者
 * Created by Administrator on 2019/2/13.
 */

public abstract class BaseCustomInflaterDialogFragment extends BaseFunctionDialogFragment {
    /**
     * 兼容软键盘与表情切换的布局
     */
    private SoftInputLayout softInputLayout;
    /**
     * 遮罩，该布局可以用来点击后关闭弹窗或隐藏键盘等自定义功能
     */
    private View mask;
    /**
     * 文字输入框布局
     */
    private FrameLayout layoutTextInput;
    /**
     * emoji布局
     */
    private FrameLayout layoutEmoji;

    @Override
    public void bindView(View v) {
        softInputLayout = v.findViewById(R.id.softInputLayout);
        mask = v.findViewById(R.id.mask);
        layoutTextInput = v.findViewById(R.id.layout_text_input);
        layoutEmoji = v.findViewById(R.id.layout_emoji);
        layoutTextInput.addView(setTextInputLayout());
        layoutEmoji.addView(setEmojiLayout());
        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMask();
            }
        });
    }

    /**
     * 获取兼容软键盘与表情切换的布局
     *
     * @return
     */
    public SoftInputLayout getSoftInputLayout() {
        return softInputLayout;
    }

    /**
     * 获取遮罩
     *
     * @return
     */
    public View getMask() {
        return mask;
    }

    /**
     * 获取文字输入框布局
     *
     * @return
     */
    public FrameLayout getTextInputLayout() {
        return layoutTextInput;
    }

    /**
     * 获取emoji布局
     *
     * @return
     */
    public FrameLayout getEmojiLayout() {
        return layoutEmoji;
    }

    /**
     * 遮罩点击事件
     */
    public void clickMask() {

    }

    /**
     * 自定义文字输入框布局中的子布局
     *
     * @return
     */
    protected abstract View setTextInputLayout();

    /**
     * 自定义emoji布局中的子布局
     *
     * @return
     */
    protected abstract View setEmojiLayout();
}
