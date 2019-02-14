package com.sss.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import share.sss.com.emoji.bean.EmojiFaceResBean;
import share.sss.com.emoji.lib.EmojiUtils;
import share.sss.com.emoji.lib.FaceManager;

public class MainActivity extends AppCompatActivity {
    /**
     * 响应标签
     */
    private TextView tvContent;
    /**
     * 用户自定义的输入模块
     */
    private  InputDialogFragment inputDialogFragment = new InputDialogFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.content);
        //添加表情
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("01", R.drawable.comma_face_01));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("02", R.drawable.comma_face_02));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("03", R.drawable.comma_face_03));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("04", R.drawable.comma_face_04));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("05", R.drawable.comma_face_05));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("06", R.drawable.comma_face_06));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("07", R.drawable.comma_face_07));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("08", R.drawable.comma_face_08));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("09", R.drawable.comma_face_09));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("10", R.drawable.comma_face_10));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("11", R.drawable.comma_face_11));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("12", R.drawable.comma_face_12));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("13", R.drawable.comma_face_13));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("14", R.drawable.comma_face_14));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("15", R.drawable.comma_face_15));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("16", R.drawable.comma_face_16));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("17", R.drawable.comma_face_17));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("18", R.drawable.comma_face_18));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("19", R.drawable.meet));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("20", R.drawable.new_mac_pro));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("21", R.drawable.nike_dunk));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("22", R.drawable.owl));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("23", R.drawable.paint_brush_tool));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("24", R.drawable.pan));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("25", R.drawable.polaroid_socialmatic));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("26", R.drawable.power_plant));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("27", R.drawable.printer_2));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("28", R.drawable.ps4_controller));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("29", R.drawable.radio_4));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("30", R.drawable.rubber_duck));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("31", R.drawable.sharpner));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("32", R.drawable.snowman));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("35", R.drawable.space_rocket));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("36", R.drawable.sunglasses));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("37", R.drawable.support));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("38", R.drawable.traffic_light));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("39", R.drawable.umbrella));
        FaceManager.getInstance().addEmojiFace(new EmojiFaceResBean("40", R.drawable.wind_sock));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (inputDialogFragment!=null) {
            inputDialogFragment.clear();
        }

    }

    /**
     * 唤起表情输入页
     *
     * @param view
     */
    public void goInput(View view) {
        inputDialogFragment.show(getSupportFragmentManager());
        inputDialogFragment.setOnCommentDialogCallBack(new InputDialogFragment.OnCommentDialogCallBack() {
            @Override
            public void onRealTimeChangedContent(String content) {
                tvContent.setText(EmojiUtils.getEmotionContent(MainActivity.this, tvContent, content));
            }

            @Override
            public void onSubmit(String content) {
                Toast.makeText(MainActivity.this,"提交按钮被点击!"+content,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
