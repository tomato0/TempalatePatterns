package design.wang.com.designpatterns.picture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 知其然，而后知其所以然
 * 倔强小指，成名在望
 * 作者： Tomato
 * on 2018/4/21 0021.
 * design.wang.com.designpatterns.picture
 * 功能、作用：
 * compress 压缩
 */

public class PictureCompress {
    /*
    * 质量压缩
    * 质量压缩会降低图片的质量，但是不会减少图片的像素。它是在保证图片像素的情况下，通过改变图片的位深、透明度等来
    * 达到压缩的目的。经过该方法压缩的图片，图片的文件大小会改变，但是导入Bitmap后占用的内存是不变的。不适用于想通
    * 过压缩来减少图片的内存占用，适用于想保证图片质量的同时减少文件的存储空间占用的大小。
    * 质量压缩通过Bitmap的options来实现
    *
    */
    //quality(质量)
    public Bitmap qualityCompress(Bitmap bitmap, int quality) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        /*
        * format:文件的格式， quality压缩额质量比， outputStream压缩后的文件输出流
        */
        boolean compressSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return BitmapFactory.decodeStream(inputStream, null, null);//生成压缩后的Bitmap
    }

    /*
    * 采样率压缩 通过降低图片像素减小图片的大小
    * 先通过读取图片的边，然后根据设定，读取图片的像素，在读取像素的时候根据采样率有选择的读取，所以这种方式减少了
    * 图片的像素的个数，同时也就减少了图片在内存中的占用。此压缩方式会造成图片的失真
    */
    //pixel(像素)
    public Bitmap pixelCompress(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//设置此属性后，读取图片只能读取到图片的边
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);//由于上面的属性设置，此时返回bitmap=null
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        //可根据宽高来设置要缩放的比率
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2; //采样率缩放的比率
        bitmap = BitmapFactory.decodeFile(filePath, options);
        return qualityCompress(bitmap, 100);
    }

    /*
    * 尺寸压缩：通过降低图片像素减小图片的大小
    */

    public void sizeCompress(Bitmap bitmap, File file) {
        int compressMultiple = 4; //multiple(倍数)
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth() / compressMultiple,
                bitmap.getHeight() / compressMultiple, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, bitmap.getWidth() / compressMultiple, bitmap.getHeight() / compressMultiple);
        canvas.drawBitmap(bitmap, null, rect, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean compress = result.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(outputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    * 图片压缩：哈夫曼编码
    */
}




