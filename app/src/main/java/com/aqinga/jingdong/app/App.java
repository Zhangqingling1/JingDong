package com.aqinga.jingdong.app;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.BuildConfig;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.utils.Log;

import java.io.File;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/619:53
 */

public class App extends Application {
    public static Context appcontext;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        appcontext = this;
        UMShareAPI.get(this);
        initImageLoader(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "680861abc7", BuildConfig.DEBUG);

    }
    public static Context appcontext(){
        return appcontext;
    }
    public static void initImageLoader(Context context) {
            File cacheDir = StorageUtils.getOwnCacheDirectory(context, "topnews/Cache");//获取到缓存的目录地址
            Log.d("cacheDir", cacheDir.getPath());
            //创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
            ImageLoaderConfiguration config = new ImageLoaderConfiguration
                    .Builder(context)
                    .threadPoolSize(3)//线程池内加载的数量
                    .denyCacheImageMultipleSizesInMemory()//.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation你可以通过自己的内存缓存实现
                    .memoryCacheSize(2 * 1024 * 1024)
                    .diskCacheSize(50 * 1024 * 1024)
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                    .tasksProcessingOrder(QueueProcessingType.LIFO)
                    .diskCache(new UnlimitedDiskCache(cacheDir))
                    //自定义缓存路径//.imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                    .build();
            ImageLoader.getInstance().init(config);//全局初始化此配置
        }

}
