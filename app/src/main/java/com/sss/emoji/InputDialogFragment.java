package com.sss.emoji;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import share.sss.com.emoji.lib.EmojiUtils;
import share.sss.com.emoji.lib.FaceManager;
import share.sss.com.emoji.lib.base.BaseCustomInflaterDialogFragment;

/**
 * 该类为用户自定义的业务逻辑类
 * Created by Administrator on 2019/2/13.
 */

public class InputDialogFragment extends BaseCustomInflaterDialogFragment {
    /**
     * 表情/文字切换按钮
     */
    private ImageView clickChange;
    /**
     * 输入框
     */
    private EditText input;
    /**
     * 提交按钮
     */
    private Button clickSubmit;

    /**
     * emoji列表
     */
    private RecyclerView emojiList;

    /**
     * 表情适配器
     */
    private EmojiAdapter emojiAdapter;

    /**
     * 输入回调
     */
    private OnCommentDialogCallBack onCommentDialogCallBack;

    /**
     * 编辑框内容监听
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (onCommentDialogCallBack != null) {
                onCommentDialogCallBack.onRealTimeChangedContent(input.getText() == null ? "" : input.getText().toString());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * 设置输入回调
     */
    public void setOnCommentDialogCallBack(OnCommentDialogCallBack onCommentDialogCallBack) {
        this.onCommentDialogCallBack = onCommentDialogCallBack;
    }

    /**
     * 清理引用
     */
    public void clear() {
        if (input != null) {
            input.removeTextChangedListener(textWatcher);
        }
        if (emojiAdapter != null) {
            emojiAdapter.clear();
        }
    }

    /**
     * 设置遮罩透明度
     */
    @Override
    public float getDimAmount() {
        return 0f;
    }

    /**
     * 遮罩点击事件
     */
    @Override
    public void clickMask() {
        super.clickMask();
        getDialog().dismiss();
    }

    /**
     * 自定义文字输入框布局
     */
    @Override
    protected View setTextInputLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_input, null);
        clickChange = view.findViewById(R.id.click_change);
        input = view.findViewById(R.id.input);
        clickSubmit = view.findViewById(R.id.click_submit);
        disposeInputLogic();
        return view;
    }

    /**
     * 自定义emoji布局
     */
    @Override
    protected View setEmojiLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_emoji, null);
        emojiList = view.findViewById(R.id.emoji_list);
        disposeEmojiLogic();
        return view;
    }

    /**
     * 自定义主题
     */
    @Override
    public int getStyleRes() {
        return R.style.InputDialogFragment;
    }

    /**
     * 处理输入
     */
    private void disposeInputLogic() {
        //添加输入监听
        input.addTextChangedListener(textWatcher);
        //提交按钮点击事件
        clickSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCommentDialogCallBack != null) {
                    onCommentDialogCallBack.onSubmit(input.getText() == null ? "" : input.getText().toString());
                }
                getDialog().dismiss();
            }
        });
        //切换按钮点击事件
        clickChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSoftInputLayout().isEmojiShow()) {
                    getSoftInputLayout().showSoftInput(input);
                } else {
                    getSoftInputLayout().showEmojiLayout();
                }
            }
        });
    }


    /**
     * 处理表情
     */
    private void disposeEmojiLogic() {
        //所有的表情符号，从表情管理类中读取
        List<String> allEmojiList = new ArrayList<>();
        for (String face : FaceManager.getInstance().getmFaceMap().keySet()) {
            allEmojiList.add(face);
        }
        //处理表情适配器
        emojiList.setLayoutManager(new GridLayoutManager(getContext(), 6));
        emojiAdapter = new EmojiAdapter(allEmojiList, getContext());
        emojiList.setAdapter(emojiAdapter);
        emojiAdapter.setOnEmojiAdapterCallBack(new EmojiAdapter.OnEmojiAdapterCallBack() {
            @Override
            public void onEmojiClick(String emojiName) {
                // 获取当前光标位置,在指定位置上添加表情图片文本
                int curPosition = input.getSelectionStart();
                StringBuilder sb = new StringBuilder(input.getText().toString());
                sb.insert(curPosition, emojiName);
                input.setText(EmojiUtils.getEmotionContent(getContext(), input, sb.toString()));
                // 将光标设置到新增完表情的右侧
                input.setSelection(curPosition + emojiName.length());
            }
        });
    }


    public interface OnCommentDialogCallBack {
        /**
         * 实时内容
         *
         * @param content 输入框中的实时内容
         */
        void onRealTimeChangedContent(String content);

        /**
         * 提交按钮被点击
         * @param content 输入框中的最终内容
         */
        void onSubmit(String content);

    }

}
