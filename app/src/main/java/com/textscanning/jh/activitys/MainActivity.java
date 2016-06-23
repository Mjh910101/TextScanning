package com.textscanning.jh.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.textscanning.jh.R;
import com.textscanning.jh.handlers.PathDataHandler;
import com.textscanning.jh.handlers.TextHandeler;
import com.textscanning.jh.handlers.VersionHandler;
import com.textscanning.jh.tool.Passageway;

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
 * Created by Hua on 16/6/13.
 */
public class MainActivity extends BaseAcivity {

    @ViewInject(R.id.main_messageText)
    private TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);
        initActivity();

    }

    @Override
    public void initActivity() {
        PathDataHandler.initTextData(context);
        messageText.setText(TextHandeler.getText(context, R.string.created_by) + "\n" + TextHandeler.getText(context, R.string.version).replace("?", VersionHandler.getVersionName(context)));
        stateJumpRun();
    }

    private void stateJumpRun() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpImageListActivity();
            }
        }, 3 * 1000);
    }

    private void jumpImageListActivity() {
        Passageway.jumpActivity(context, ImageListActivity.class);
        finish();
    }
}
