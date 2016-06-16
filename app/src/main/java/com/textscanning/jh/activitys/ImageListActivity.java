package com.textscanning.jh.activitys;

import android.os.Bundle;
import android.view.KeyEvent;

import com.lidroid.xutils.ViewUtils;
import com.textscanning.jh.R;
import com.textscanning.jh.handlers.MessageHandler;

/**
 * *
 * * ┏┓      ┏┓
 * *┏┛┻━━━━━━┛┻┓
 * *┃          ┃
 * *┃          ┃
 * *┃ ┳┛   ┗┳  ┃
 * *┃          ┃
 * *┃    ┻     ┃
 * *┃          ┃
 * *┗━┓      ┏━┛
 * *  ┃      ┃
 * *  ┃      ┃
 * *  ┃      ┗━━━┓
 * *  ┃          ┣┓
 * *  ┃         ┏┛
 * *  ┗┓┓┏━━━┳┓┏┛
 * *   ┃┫┫   ┃┫┫
 * *   ┗┻┛   ┗┻┛
 * Created by Hua on 16/6/14.
 */
public class ImageListActivity extends BaseAcivity {

    private final static long EXITTIME = 2000;
    private long EXIT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        ViewUtils.inject(this);
        initActivity();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (System.currentTimeMillis() - EXIT < EXITTIME) {
                finish();
            } else {
                MessageHandler.showToast(context, "再次点击退出");
            }
            EXIT = System.currentTimeMillis();
        }
        return false;
    }

    @Override
    public void initActivity() {

    }
}
