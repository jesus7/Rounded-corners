package com.example.jesus7_w.myapplication;

import android.content.Context;
import android.graphics.AvoidXfermode;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jesus7_w on 15/6/25.
 */
public class DemoView extends View {
    public DemoView(Context c){super(c);}
    public DemoView(Context c,AttributeSet attributeSet) {super(c, attributeSet);}
    public DemoView(Context c,AttributeSet attributeSet, int defstyle) {super(c, attributeSet, defstyle);}


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        BitmapDrawable bitmapDrawable =(BitmapDrawable) getResources().getDrawable(R.mipmap.test);
        Bitmap bitmap =  drawBitmap(bitmapDrawable.getBitmap());
        canvas.drawBitmap(bitmap, 0, 0, null);
    }

    public static Bitmap drawBitmap( Bitmap bitmap) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int corner = 50;
            Canvas canvas = new Canvas(output);

            float[] outerR = new float[] { 0, 0, corner, corner, 0, 0, corner, corner };
            RoundRectShape rr = new RoundRectShape(outerR, null, null);
            rr.resize(bitmap.getWidth(), bitmap.getHeight());
            ShapeDrawable drawable = new ShapeDrawable(rr);
            final Paint paint = drawable.getPaint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final Rect src = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawARGB(0, 0, 0, 0);
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            drawable.getPaint().setColor(Color.WHITE);
            drawable.getPaint().setStyle(Paint.Style.FILL);
            drawable.draw(canvas);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, src, rect, paint);
            return  output;
      } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
