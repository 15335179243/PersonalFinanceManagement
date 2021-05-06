package com.chumu.jianzhimao.ui.activity.eys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getName();

    public static int getYUVByteSize(final int width, final int height) {
        // The luminance plane requires 1 byte per pixel.
        final int ySize = width * height;

        // The UV plane works on 2x2 blocks, so dimensions with odd size must be rounded up.
        // Each 2x2 block takes 2 bytes to encode, one each for U and V.
        final int uvSize = ((width + 1) / 2) * ((height + 1) / 2) * 2;

        return ySize + uvSize;
    }


    public static Bitmap onImageCropping(Bitmap bitmapSrc, List<Float> location) {
        if (bitmapSrc == null) return null;
        if (location == null) return null;
        //获取整张图片
        //需要截取的长和宽
        for (int i = 0; i < location.size(); i++) {
            if (location.get(i) > 1.0f) {
                location.set(i, 1.0f);
            }
        }
        int outWidth = (int) (((location.get(2) * bitmapSrc.getWidth())) - (location.get(0) * bitmapSrc.getWidth()));
        int outHeight = (int) (((location.get(3) * bitmapSrc.getHeight())) - (location.get(1) * bitmapSrc.getHeight()));
        Bitmap handBitMap = null;
        try {
            handBitMap = Bitmap.createBitmap(bitmapSrc, (int) (location.get(0) * bitmapSrc.getWidth()), (int) (location.get(1) * bitmapSrc.getHeight()), outWidth, outHeight);
            return handBitMap;
        } catch (Exception e) {
            bitmapSrc.recycle();
            e.printStackTrace();
        }
        bitmapSrc.recycle();
        return null;

    }

    /**
     * 选择变换
     *
     * @param origin 原图
     * @param alpha  旋转角度，可正可负
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmap(Bitmap origin, float alpha) {
        if (origin == null) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(alpha);
        // 围绕原地进行旋转
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (newBM.equals(origin)) {
            return newBM;
        }
        origin.recycle();
        return newBM;
    }

    /**
     * 根据人脸检测结果截取人脸区域，
     *
     * @param originalImg 原始包含人脸的图片
     * @param left        人脸左上坐标X
     * @param top         人脸左上坐标Y
     * @param right       人脸右下坐标X
     * @param bottom      人脸右下坐标Y
     * @param padScaleW   宽度扩充比例
     * @param padScaleH   高度扩充比例
     * @return 截取的人脸区域 Bitmap
     */
    public static Bitmap enlargeFaceImage(Bitmap originalImg,
                                          int left, int top, int right, int bottom,
                                          double padScaleW, double padScaleH) {
        int width = originalImg.getWidth();
        int height = originalImg.getHeight();

//        float padScaleW = 0.0025f;
//        float padScaleH = 0.0025f;
        int faceWidth = right - left;
        int faceHeight = bottom - top;
        int padWidth = (int) (faceWidth * padScaleW);
        int padHeight = (int) (faceHeight * padScaleH);

        int faceLeft = Math.max(left - padWidth, 0);
        int faceTop = Math.max(top - padHeight, 0);
        int faceRight = Math.min(right + padWidth, width);
        int faceBottom = Math.min(bottom + padHeight, height);

        int faceW = faceRight - faceLeft;
        int faceH = faceBottom - faceTop;
        if (faceH <= 0 || faceW <= 0) {
            return null;
        }
        Bitmap faceImg = Bitmap.createBitmap(originalImg, faceLeft, faceTop, faceW, faceH);

        return faceImg;
    }


    // 首先保存图片
    public static boolean saveImageToGallery(Bitmap bitmap) {
        File appDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/77meeting/");
        if (!appDir.exists()) {
            // 目录不存在 则创建
            appDir.mkdirs();
        }
        String fileName = "hd-" + System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 保存bitmap至本地
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
//            if (type == ScannerType.RECEIVER) {
//                ScannerByReceiver(context, file.getAbsolutePath());
//            } else if (type == ScannerType.MEDIA) {
//                ScannerByMedia(context, file.getAbsolutePath());
//            }
            if (!bitmap.isRecycled()) {
                // bitmap.recycle(); 当存储大图片时，为避免出现OOM ，及时回收Bitmap
                System.gc(); // 通知系统回收
            }
        }
        return true;
    }


    /**
     * 获取 app 保存路径
     *
     * @return
     */
    public static String getFileSaveDir() {
        File appDir = new File(Environment.getExternalStorageDirectory(), "show_root");
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir.getAbsolutePath();
    }

    // 等比缩放图片
    public static Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    public static String bitmapToBase64(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                if (!bitmap.isRecycled()) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
                }

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
        return result;
    }

    /**
     * @param bitmap          图片
     * @param filePathAndName 文件绝对路径
     * @param quality         清晰度
     * @throws IOException
     */
    public static File saveBitMapToFile(Bitmap bitmap, String filePathAndName, int quality) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        File file = new File(filePathAndName);
        FileOutputStream fos = new FileOutputStream(file);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        int x = 0;
        byte[] b = new byte[1024 * 100];
        while ((x = is.read(b)) != -1) {
            fos.write(b, 0, x);
        }
        if (fos != null) {
            fos.close();
        }
        if (is != null) {
            is.close();
        }
        if (baos != null) {
            baos.close();
        }
        return file;
    }

    /**
     * 上传图片
     *
     *
     * @return
     * @throws IOException
     */
    public static void uploadBitMap(String fileName,String token) throws IOException {
//        String filePath = getFileSaveDir() + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(fileName);
        String url = "https://pic.liesio.com/api/upload";
        Log.i(ImageUtils.class.getCanonicalName(), "url:" + url + "\t" + file.getAbsolutePath());

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        // 设置文件以及文件上传类型封装
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        // 文件上传的请求体封装
        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", fileName, requestBody)
                .build();
        Request request = new Request.Builder()
                .url(url)

                .post(multipartBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "upload Photo Failure:" + e.getMessage(), e);


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadFacePhoto onResponse"+response);
                try {
                    String result = response.body().string();
                    if (response.isSuccessful()) {

                    }
                } finally {

                }
            }
        });
    }

    public interface ImageUploadCallBack {
        void onFail(String errorMsg);

        void onSuccess(Map<String, Object> json);
    }

    public static String getImageSquare(String imageUrl, List<List<Integer>> face_loc) {
        String baseUrl = "https://m.360buyimg.com/n1/";
        if (face_loc != null && face_loc.size() == 2) {
            int imgW = face_loc.get(1).get(0) - face_loc.get(0).get(0), imgH = face_loc.get(1).get(1) - face_loc.get(0).get(1);
            if (imgW <= imgH) {
                int startH = (int) (face_loc.get(0).get(1) + (imgH - imgW) / 2);
                return String.format("%s%s!cr_%dx%d_%d_%d", baseUrl, imageUrl, imgW, imgW, face_loc.get(0).get(0), startH);

            } else {
                int startW = (int) (face_loc.get(0).get(0) + (imgW - imgH) / 2);
                return String.format("%s%s!cr_%dx%d_%d_%d", baseUrl, imageUrl, imgH, imgH, startW, face_loc.get(0).get(1));
            }
        } else {
            return baseUrl + imageUrl;
        }
    }

    public static Bitmap ReverseBitmap(Bitmap img) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        img = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        return img;
    }

}
