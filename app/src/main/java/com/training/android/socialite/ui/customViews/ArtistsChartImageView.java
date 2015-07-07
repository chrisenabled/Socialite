package com.training.android.socialite.ui.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.training.android.socialite.R;

/**
 * Created by chrisenabled on 6/30/15.
 */
public class ArtistsChartImageView extends View {

    //circle and text colors
    private int circleCol, circleImage;
    //label text
    //paint for drawing custom view
    private Paint circlePaint;
    private Paint semiCircleBottomLeft;
    private Paint semiCircleBottomRight;
    private Bitmap image;
    private Drawable imageResource;
    private int mWidth;
    private int mHeight;
    private float mAngle;
    public Target loadtarget;


    public ArtistsChartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //paint object for drawing in onDraw
        circlePaint = new Paint();
        semiCircleBottomRight = new Paint();
        semiCircleBottomLeft = new Paint();

        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ArtistsChartImageView, 0, 0);

        try {
            //get the text and colors specified using the names in attrs.xml
            circleCol = a.getInteger(R.styleable.ArtistsChartImageView_circleColor, 0);//0 is default
           imageResource = a.getDrawable(R.styleable.ArtistsChartImageView_circleImage);
        } finally {
            a.recycle();
        }

        image = ((BitmapDrawable)imageResource).getBitmap();

    }

    public void setImage(Bitmap image) {
        this.image = image;
        invalidate();
        requestLayout();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;

        float radius = 0;
        float smallRadius = 0;
        if (viewWidthHalf > viewHeightHalf)
            radius=viewHeightHalf * 0.9f;
        else
            radius=viewWidthHalf * 0.9f;

        smallRadius = radius/3;

        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);

        semiCircleBottomRight.setStyle(Style.FILL);
        semiCircleBottomRight.setAntiAlias(true);

        semiCircleBottomLeft.setStyle(Style.FILL);
        semiCircleBottomLeft.setAntiAlias(true);

        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);
        semiCircleBottomLeft.setColor(circleCol);
        semiCircleBottomRight.setColor(circleCol);

        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, circlePaint);
        canvas.drawCircle(viewWidthHalf - radius + smallRadius, viewHeightHalf + (radius * 0.7f), smallRadius, semiCircleBottomLeft);
        canvas.drawCircle(viewWidthHalf + radius - smallRadius, viewHeightHalf + (radius * 0.7f), smallRadius, semiCircleBottomRight);

        float roundedRadFloat = radius * 1.8f;
        int roundedRad = (int) roundedRadFloat;
        Bitmap b = getRoundedCroppedBitmap(image, roundedRad);
        int cx = (mWidth - b.getWidth()) >> 1;
        int cy = (mHeight - b.getHeight()) >> 1;

        if (mAngle > 0) {
            canvas.rotate(mAngle, mWidth >> 1, mHeight >> 1);
        }

        canvas.drawBitmap(b,cx,cy, circlePaint);

    }


    //Method to turn a rectangle bitmap into circular bitmap
    public static Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap;
        if (bitmap.getWidth() != radius || bitmap.getHeight() != radius)
            finalBitmap = Bitmap.createScaledBitmap(bitmap, radius, radius,
                    false);
        else
            finalBitmap = bitmap;
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(),
                finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, finalBitmap.getWidth(),
                finalBitmap.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(finalBitmap.getWidth() / 2 + 0.7f,
                finalBitmap.getHeight() / 2 + 0.7f,
                finalBitmap.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, paint);

        return output;
    }

    public void loadBitmap(String url, Context context) {

        if (loadtarget == null) loadtarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // do something with the Bitmap
                setImage(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }


        };

        Picasso.with(context).load(url).into(loadtarget);
    }


}
