package com.example.shiyaqn;
import android.content.Intent;
import android.media.MediaPlayer;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
public class activity extends AppCompatActivity {

    // ImageView 控件
    private ImageView image1, image2, image3, image4;

    // 图片资源 ID 数组
    private final int[] imageResources = {
            R.drawable.image1, // 替换为实际的 drawable 图片文件
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            // 添加更多图片资源 ID
    };

    // 图片名称数组，对应每个图片的名称
    private final String[] imageNames = {
            "橙子", "苹果", "草莓", "西瓜", "芒果", "梨子","猕猴桃","菠萝"
            // 添加更多图片的名称
    };

    // 音频文件名数组
    private final String[] soundFiles = {
            "image1", "image2", "image3", "image4", "image5", "image6","image7","image8"
            // 添加更多音频文件名
    };

    // 标志变量，表示是否在 Activity 启动时播放音频
    private boolean shouldPlaySoundOnCreate = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        // 获取按钮控件
        Button button7 = findViewById(R.id.button7);

        // 设置按钮点击事件
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建跳转到下一页的 Intent
                Intent intent = new Intent(activity.this, NextActivity.class);
                startActivity(intent);  // 启动新的 Activity
            }
        });

        // 初始化 ImageView 控件
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);

        // 按钮初始化
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);

        // 设置随机图片和按钮文本（但是不会在此时播放音频）
        setRandomImage(image1, button1, false); // 传递 false，表示不播放音频
        setRandomImage(image2, button2, false);
        setRandomImage(image3, button3, false);
        setRandomImage(image4, button4, false);

        // 按钮点击事件示例
        button1.setOnClickListener(view -> setRandomImage(image1, button1, true));
        button2.setOnClickListener(view -> setRandomImage(image2, button2, true));
        button3.setOnClickListener(view -> setRandomImage(image3, button3, true));
        button4.setOnClickListener(view -> setRandomImage(image4, button4, true));
    }

    // 随机选择图片并设置到 ImageView，同时播放对应音频，并更新按钮文本
    private void setRandomImage(ImageView imageView, Button button, boolean playSound) {
        Random random = new Random();
        int randomIndex = random.nextInt(imageResources.length);  // 随机选择一个索引

        // 设置随机图片
        imageView.setImageResource(imageResources[randomIndex]);

        // 设置按钮文本为图片对应的名字
        button.setText(imageNames[randomIndex]);

        // 如果需要播放音频，则播放对应的音频
        if (playSound) {
            playSound(soundFiles[randomIndex]);
        }
    }

    // 播放音频
    private void playSound(String soundFile) {
        // 获取 raw 文件夹中的音频资源 ID
        int soundId = getResources().getIdentifier(soundFile, "raw", getPackageName());

        if (soundId != 0) {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, soundId);
            mediaPlayer.start();

            // 音频播放结束后释放资源
            mediaPlayer.setOnCompletionListener(mp -> mp.release());
        } else {
            // 如果没有找到对应的音频文件
            // 这里可以添加错误处理逻辑
        }

    }



}
