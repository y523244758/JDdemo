package com.rxdemo;

import android.os.Environment;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.rxdemo.Model.News;
import com.rxdemo.Presenter.ShowPresenterSe;
import com.rxdemo.dao.DownloadUtil;
import com.rxdemo.view.ShowView;

import java.util.ArrayList;
import java.util.List;


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

    ViewPager vp;
    RadioGroup rg;
    List<String> ilist=new ArrayList<>();
    int i;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp.setCurrentItem(msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        //获取资源ID
        videoView=(VideoView) findViewById(R.id.videoview);
        //创建MediaController
        final MediaController controller=new MediaController(this);

        //轮播
        vp= (ViewPager) findViewById(R.id.vp);
        rg= (RadioGroup) findViewById(R.id.rg);

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

        //轮播
        ilist.add("http://img01.vgtime.com/game/cover/2017/11/23/171123101558445.jpg");
        ilist.add("http://img01.vgtime.com/game/cover/2017/11/23/171123093907243.jpg");
        ilist.add("http://img01.vgtime.com/web/topic/2017/11/22/171122182851825.jpg");

        Myviewpage mp=new Myviewpage(MainActivity.this,ilist);

        vp.setAdapter(mp);

        vp.setCurrentItem(ilist.size()*10000);

        i=vp.getCurrentItem();

        getpage();

        new Thread(){
            @Override
            public void run() {
                super.run();

                while (true){
                    i++;
                    try {
                        sleep(5000);
                        handler.sendEmptyMessage(i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

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

    public void getpage() {

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position%ilist.size()){
                    case 0:
                        rg.check(R.id.b1);
                        break;
                    case 1:
                        rg.check(R.id.b2);
                        break;
                    case 2:
                        rg.check(R.id.b3);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId%ilist.size()){
                    case R.id.b1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.b2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.b3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });


    }
}
