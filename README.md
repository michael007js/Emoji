 ** **Emoji** ** 

No picture u say a j8!

少啰嗦，先看效果

![闭嘴看图](https://github.com/michael007js/Emoji/blob/master/app/src/preview.gif "闭嘴看图")


## 项目介绍
这是一款目前在项目中使用的输入方案，采用DialogFragment方式以方便随用随调以及callback的设置，高度自定义的emoji表情实现方案,仅仅需要用户导入emoji表情以及决定输入页的UI样式即可,什么表情兼容、键盘冲突啥啥啥之类的全部交给我就行

 ## 使用说明
1.copy你的表情到drawable文件夹


2.使用表情管理器FaceManager导入表情：

  FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("01", R.drawable.comma_face_01));


3.继承输入页模块UI构造器BaseCustomInflaterDialogFragment并实现你的输入页逻辑，demo中已实现一个[InputDialogFragment](https://github.com/michael007js/Emoji/blob/master/app/src/main/java/com/sss/emoji/InputDialogFragment.java)类以供参考


 ## Tip
1.库中每个类每个方法每个变量都有详细的注释，说明它是干什么的，这里就不详细介绍了，大家可以宕下来强势围观，很容易秒懂

2.表情兼容：textview.setText(EmojiUtils.getEmotionContent(context, textview, "要设置到textview中的文本内容"));

3.键盘处理：[SoftInputLayout](https://github.com/michael007js/Emoji/blob/master/lib/src/main/java/share/sss/com/emoji/lib/view/SoftInputLayout.java)


混淆：

-keep class share.sss.com.emoji.bean.**{*;}

 over

 By SSS




