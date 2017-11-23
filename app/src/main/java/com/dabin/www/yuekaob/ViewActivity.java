package com.dabin.www.yuekaob;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.pross)
    TextView pross;
    @BindView(R.id.progressbars)
    ProgressBar progressbars;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.end)
    Button end;
    Boolean isDowloding = false;
    private HttpHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);






    }

    @OnClick({R.id.start, R.id.end})
    public void onViewClicked(View view) {
        String fileName = "abc.mp4";
        switch (view.getId()) {
            case R.id.start:
                end.setEnabled(true);
                start.setEnabled(false);
                String path = "http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4" + fileName;
                HttpUtils http = new HttpUtils();
                boolean b = fileIsExists("/storage/emulated/legacy/abc.mp4");
                if(b){
                    Toast.makeText(ViewActivity.this, "文件存在", Toast.LENGTH_SHORT).show();
                    PlayerView playerView = new PlayerView(ViewActivity.this);
                    playerView.setTitle("我")
                            .setScaleType(PlayStateParams.fitparent)
                            .hideMenu(true)
                            .forbidTouch(false)
                            .setPlaySource("http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4")
                            .startPlay();
                }else{
                    handler = http.download(path, Environment.getExternalStorageDirectory() + "/" + fileName, true, true, new RequestCallBack<File>() {
                        @Override
                        public void onSuccess(ResponseInfo<File> responseInfo) {
                            isDowloding = false;
                            //下载成功
                            Toast.makeText(ViewActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                            PlayerView playerView = new PlayerView(ViewActivity.this);
                            playerView.setTitle("我")
                                    .setScaleType(PlayStateParams.fitparent)
                                    .hideMenu(true)
                                    .forbidTouch(false)
                                    .setPlaySource("http://mp4.vjshi.com/2013-05-28/2013052815051372.mp4")
                                    .startPlay();
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            //下载失败
                            Toast.makeText(ViewActivity.this, s, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                            super.onLoading(total, current, isUploading);
                            if (current < total) {
                                isDowloding = true;
                            } else {
                                isDowloding = false;
                            }
                            progressbars.setMax((int) total);
                            progressbars.setProgress((int) current);
                            pross.setText(current * 100 / total + "%");
                        }
                    });
                }



                break;
            case R.id.end:
                end.setEnabled(false);
                start.setEnabled(true);
                if (isDowloding) {
                    if (handler != null) {
                        handler.cancel();
                    }
                }
                break;
        }
    }

    //判断文件是否存在
    public boolean fileIsExists(String strFile)
    {
        try
        {
            File f=new File(strFile);
            if(!f.exists())
            {
                return false;
            }

        }
        catch (Exception e)
        {
            return false;
        }

        return true;
    }
}
