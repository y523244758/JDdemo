package com.rxdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.rxdemo.Model.News;
import com.rxdemo.Presenter.ShowPresenterSe;
import com.rxdemo.dao.DownloadUtil;
import com.rxdemo.view.ShowView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity implements ShowView{

    TextView tv;
    private ProgressBar mProgressBar;
    private Button start,go;
    private Button pause;
    private VideoView videoView;
    JCVideoPlayerStandard jcVideoPlayerStandard;
    private TextView total;
    private int max;
    private DownloadUtil mDownloadUtil;
    String urlString = "http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源ID
        videoView=(VideoView) findViewById(R.id.videoview);
        //创建MediaController
        final MediaController controller=new MediaController(this);


        tv= (TextView) findViewById(R.id.tv);
        total= (TextView) findViewById(R.id.textView);
        start= (Button) findViewById(R.id.start);
        pause= (Button) findViewById(R.id.delete);
        go= (Button) findViewById(R.id.kaishi);
        mProgressBar= (ProgressBar) findViewById(R.id.progressBar);

        ShowPresenterSe sp=new ShowPresenterSe(this);
        sp.PresenterData();

        String localPath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/local";
        mDownloadUtil = new DownloadUtil(2, localPath, "adc.mp4", urlString,
                this);
        mDownloadUtil.setOnDownloadListener(new DownloadUtil.OnDownloadListener() {
            @Override
            public void downloadStart(int fileSize) {

                max = fileSize;
                mProgressBar.setMax(fileSize);
            }

            @Override
            public void downloadProgress(int downloadedSize) {
                mProgressBar.setProgress(downloadedSize);
                total.setText((int) downloadedSize * 100 / max + "%");
            }

            @Override
            public void downloadEnd() {
                Toast.makeText(MainActivity.this,"下载完成",Toast.LENGTH_SHORT).show();

                //加载路径
                videoView.setVideoPath("mnt/sdcard/local/adc.mp4");

                //绑定媒体控制器
                videoView.setMediaController(controller);
                //媒体控制器绑定videoView
                controller.setAnchorView(videoView);

                        //开始播放
                        videoView.start();

            }
        });

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                mDownloadUtil.pause();
            }
        });


        jcVideoPlayerStandard= (JCVideoPlayerStandard) findViewById(R.id.jiecao_Player);
        jcVideoPlayerStandard.setUp(urlString,jcVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"播放视频");





    }

    @Override
    public void ShowData(News news) {

        tv.setText(news.getData().get(0).getTITLE());

    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
