package com.xxs.order.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileImgUtil {


    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static Future<Void> saveBitmapAsync(final Bitmap bitmap,final String path){
        return executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                saveImageBitmapToFileImg(bitmap,path);
                return null;
            }
        });
    }

    public static void saveImageBitmapToFileImg(Bitmap bitmap, String path) {

        File file = new File(path);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void saveImageBitmapToFileImg(Uri uri, Context context,String path) {

        CustomTarget<Bitmap> target = new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                File file = new File(path);

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    resource.compress(Bitmap.CompressFormat.PNG,100,fos);
                    fos.flush();
                    fos.close();
                }catch (IOException e){

                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        };

        Glide.with(context)
                .asBitmap()
                .load(uri)
                .into(target);
    }

    public static String getImageName(){
        String pigName = "/"+UUID.randomUUID().toString().replaceAll("-", "")+".png";

        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }
}
