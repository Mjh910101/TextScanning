package com.textscanning.jh.activitys;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.task.TaskHandler;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.textscanning.jh.R;
import com.textscanning.jh.dialogs.ListDialog;
import com.textscanning.jh.handlers.MessageHandler;
import com.textscanning.jh.handlers.TextHandeler;
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
 * Created by Hua on 16/6/14.
 */
public class ImageListActivity extends BaseAcivity {

    private final static long EXITTIME = 2000;
    private long EXIT = 0;

    @ViewInject(R.id.title_titleName)
    private TextView titleName;

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
                MessageHandler.showToast(context, TextHandeler.getText(context, R.string.again_click_quit));
            }
            EXIT = System.currentTimeMillis();
        }
        return false;
    }

    @Override
    public void initActivity() {
        titleName.setText(TextHandeler.getText(context, R.string.my_file));
    }

    @OnClick({R.id.imageList_addButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageList_addButton:
                showAddImageDialog();
                break;
        }

    }

    private void showAddImageDialog() {
        final ListDialog dialog = new ListDialog(context);
        dialog.setList(new String[]{TextHandeler.getText(context, R.string.taking_photo), TextHandeler.getText(context, R.string.photo_album)});
        dialog.setItemListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                switch (position) {
                    case 0:
                        takingPhoto();
                        break;
                    case 1:
                        photoAlbum();
                        break;
                }
            }
        });
    }

    private void photoAlbum() {
        Passageway.selectImage(context);
    }

    private void takingPhoto() {
        Passageway.takePhoto(context);
    }
}
