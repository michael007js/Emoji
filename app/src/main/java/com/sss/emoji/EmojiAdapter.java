package com.sss.emoji;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import share.sss.com.emoji.lib.FaceManager;

/**
 * Created by Administrator on 2019/2/13.
 */

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {

    /**
     * 表情字符集合
     */
    private List<String> data;
    /**
     * 你懂的
     */
    private Context context;

    /**
     * 回调
     */
    private OnEmojiAdapterCallBack onEmojiAdapterCallBack;

    public EmojiAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    /**
     * 清理垃圾
     */
    public void clear() {
        if (data != null) {
            data.clear();
        }
        data = null;
        context = null;
    }

    /**
     * 设置回调
     */
    public void setOnEmojiAdapterCallBack(OnEmojiAdapterCallBack onEmojiAdapterCallBack) {
        this.onEmojiAdapterCallBack = onEmojiAdapterCallBack;
    }

    @NonNull
    @Override
    public EmojiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EmojiAdapter.ViewHolder holder, final int position) {
        holder.faceItemEmoji.setImageResource(FaceManager.getInstance().getQQfaceResource(data.get(position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onEmojiAdapterCallBack != null) {
                    onEmojiAdapterCallBack.onEmojiClick(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView faceItemEmoji;

        public ViewHolder(View itemView) {
            super(itemView);
            faceItemEmoji = itemView.findViewById(R.id.face_item_emoji);

        }
    }


    public interface OnEmojiAdapterCallBack {
        void onEmojiClick(String emojiName);
    }

}
