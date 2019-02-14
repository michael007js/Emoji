package share.sss.com.emoji.lib;

import android.graphics.drawable.Drawable;

import java.util.LinkedHashMap;

import share.sss.com.emoji.bean.EmojiFaceResBean;
import share.sss.com.emoji.dao.IEmojiFaceManager;

/**
 * 表情管理类
 * Created by Administrator on 2019/2/13.
 */

public class FaceManager implements IEmojiFaceManager {
    /**
     * 表情容器
     */
    private static final LinkedHashMap<String, Integer> mFaceMap = new LinkedHashMap<>();

    /**
     * 实例
     */
    private static FaceManager faceManager = new FaceManager();

    /**
     * 获取实例
     * @return
     */
    public static FaceManager getInstance() {
        return faceManager;
    }

    /**
     * 添加表情
     * @param beans
     */
    public void addEmojiFace(EmojiFaceResBean... beans) {
        for (int i = 0; i < beans.length; i++) {
            mFaceMap.put("[" + beans[i].name + "]", beans[i].res);
        }
    }

    /**
     * 判断是否是SoftBank编码表情
     *
     * @param c
     * @return
     */
    @Override
    public boolean maybeSoftBankEmoji(char c) {
        return false;
    }

    /**
     * 获取SoftBank编码表情
     *
     * @param c
     * @return
     */
    @Override
    public int getSoftbankEmojiResource(char c) {
        return 0;
    }

    /**
     * 判断Unicode编码字符是否是表情，用于缩小解析范围
     *
     * @param codePoint
     * @return
     */
    @Override
    public boolean maybeEmoji(int codePoint) {
        return false;
    }

    /**
     * 获取Unicode编码表情，如果没有则返回0
     *
     * @param codePoint
     * @return
     */
    @Override
    public int getEmojiResource(int codePoint) {
        return 0;
    }

    /**
     * 获取双字符编码表情， 如果没有则返回0
     *
     * @param currentCodePoint
     * @param nextCodePoint
     * @return
     */
    @Override
    public int getDoubleUnicodeEmoji(int currentCodePoint, int nextCodePoint) {
        return 0;
    }

    /**
     * 将字符串解析例为表情，如果没有则返回0
     *
     * @param text
     * @return
     */
    @Override
    public int getQQfaceResource(CharSequence text) {
        Integer integer = mFaceMap.get(text.toString());
        if (integer == null) {
            return 0;
        }
        return integer;
    }

    /**
     * 寻找特殊bounds的Drawable, 字符串请以[开头和以]结尾
     *
     * @param text
     * @return
     */
    @Override
    public Drawable getSpecialBoundsDrawable(CharSequence text) {
        return null;
    }

    /**
     * 获取特殊bounds的Drawable的最高height, 这将决定行高
     *
     * @return
     */
    @Override
    public int getSpecialDrawableMaxHeight() {
        return 0;
    }

    public LinkedHashMap<String, Integer> getmFaceMap() {
        return mFaceMap;
    }
}
