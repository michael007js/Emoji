package share.sss.com.emoji.lib;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import share.sss.com.emoji.CenterAlignImageSpan;

/**
 * Created by Administrator on 2019/2/13.
 */

public class EmojiUtils {
    /**
     * 文本中的emoji字符处理为表情图片
     *
     * @param context
     * @param tv
     * @param source
     * @return
     */
    public static SpannableString getEmotionContent(Context context, TextView tv, String source) {
        if (source == null) {
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(source);
        Resources res = context.getResources();

        String regexEmotion = "\\[([\u4e00-\u9fa5\\w])+\\]";
        Pattern patternEmotion = Pattern.compile(regexEmotion);
        Matcher matcherEmotion = patternEmotion.matcher(spannableString);

        while (matcherEmotion.find()) {
            // 获取匹配到的具体字符
            String key = matcherEmotion.group();
            // 匹配字符串的开始位置
            int start = matcherEmotion.start();
            // 利用表情名字获取到对应的图片
            Integer imgRes = FaceManager.getInstance().getQQfaceResource(key);
            if (imgRes != -1) {//-1代表纯文本，没有图片
                // 压缩表情图片
                int size = (int) tv.getTextSize() * 13 / 10;
                Bitmap bitmap = BitmapFactory.decodeResource(res, imgRes);
                Bitmap scaleBitmap = Bitmap.createScaledBitmap(bitmap, size, size, true);

                 CenterAlignImageSpan span = new CenterAlignImageSpan(context, scaleBitmap, ImageSpan.ALIGN_BASELINE);
                spannableString.setSpan(span, start, start + key.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableString;
    }
}
