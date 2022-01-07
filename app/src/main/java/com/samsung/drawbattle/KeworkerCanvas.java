package com.samsung.drawbattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class KeworkerCanvas extends View {
    protected Paint paint, line, eraser;
    protected List<Drawable> lines = new ArrayList<>();
    private List<Path> paths = new ArrayList<>();
    protected List<Short> flags = new ArrayList<>();
    protected final int MAX_ARGB = 255, MAX_WIDTH = 80;
    protected boolean paintMode = true, lineMode = false, eraserMode = false;
    protected float pointX, pointY;
    protected Paint bitmapPaint;
    protected Canvas canvas;
    protected Bitmap bitmap;
    protected Context context;
    protected float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public KeworkerCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        line = new Paint();
        eraser = new Paint();
        eraser.setARGB(MAX_ARGB, MAX_ARGB, MAX_ARGB, MAX_ARGB);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(getResources().getColor(R.color.white));
        for (int i = 0; i < lines.size(); i++) {
            lines.get(i).onDraw(canvas);
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MainGameActivity.getGameStage() % 2 != 0) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (paintMode) {
                        onPaintActionDown(event.getX(), event.getY());
                        invalidate();
                    }
                    else if (lineMode) {
                        lines.add(new Line(event.getX(), event.getY()));
                    }
                    else if (eraserMode) {
                        pointX = event.getX();
                        pointY = event.getY();
                        flags.add((short) lines.size());
                    }
                    break;
                }

                case MotionEvent.ACTION_MOVE: {
                    if (paintMode) {
                        onPaintActionMove(event.getX(), event.getY());
                    }
                    else if (eraserMode) {
                        lines.add(new Line(pointX, pointY, event.getX(), event.getY()));
                        lines.get(lines.size() - 1).setARGBW(eraser.getColor(),
                                eraser.getStrokeWidth());
                        pointX = event.getX();
                        pointY = event.getY();
                    }
                    invalidate();
                    break;
                }

                case MotionEvent.ACTION_UP: {
                    if (paintMode) {
                        onPaintActionUp();
                    }
                    else if (lineMode) {
                        lines.get(lines.size() - 1).setEndXY(event.getX(), event.getY());
                        lines.get(lines.size() - 1).setARGBW(paint.getColor(),
                                paint.getStrokeWidth());
                    }
                    else if (eraserMode) {
                        flags.add((short) lines.size());
                    }
                    invalidate();
                    break;
                }
            }
        }
        return true;
    }

    private void onPaintActionDown(float x, float y) {
        paths.add(new Path());
        paths.get(paths.size() - 1).reset();
        paths.get(paths.size() - 1).moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void onPaintActionMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            paths.get(paths.size() - 1).quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    private void onPaintActionUp() {
        paths.get(paths.size() - 1).lineTo(mX, mY);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
        resetPaint();
    }

    public List<Drawable> getLines() {
        return lines;
    }

    public void setLines(List<Drawable> lines) {
        this.lines.clear();
        this.lines.addAll(lines);
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaintARGB(int a, int r, int g, int b) throws Exception {
        if (a >= 0 && a <= MAX_ARGB && r >= 0 && r <= MAX_ARGB
                && g >= 0 && g <= MAX_ARGB && b >= 0 && b <= MAX_ARGB) {
            paint.setARGB(a, r, g, b);
            line.setARGB(a, r, g, b);
        }
        else {
            throw new Exception();
        }
    }

    public Paint getEraser() {
        return eraser;
    }

    public void setWidth(float width) throws Exception {
        if (width * MAX_WIDTH / 100 > 0
                && width * MAX_WIDTH / 100 <= MAX_WIDTH) {
            paint.setStrokeWidth(width * MAX_WIDTH / 100);
            line.setStrokeWidth(width * MAX_WIDTH / 100);
            eraser.setStrokeWidth(width * MAX_WIDTH / 100);
        }
        else {
            throw new Exception();
        }
    }

    public void back() {
        if (flags.size() > 1 && flags.get(flags.size() - 1) == lines.size()) {
            while (lines.size() > flags.get(flags.size() - 2)) {
                lines.remove(lines.size() - 1);
            }
            flags.remove(flags.size() - 1);
            flags.remove(flags.size() - 1);
        }
        else {
            lines.remove(lines.size() - 1);
        }
        invalidate();
    }

    public void setPaintMode() {
        paintMode = true;
        lineMode = false;
        eraserMode = false;
    }

    public void setLineMode() {
        paintMode = false;
        lineMode = true;
        eraserMode = false;
    }

    public void setEraserMode() {
        paintMode = false;
        lineMode = false;
        eraserMode = true;
    }

    public void resetPaint() {
        lines.add(new KeworkerPath(paint, paths.get(paths.size() - 1), bitmap, bitmapPaint));
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
    }

    public class Line implements Drawable {
        public float x1, y1, x2, y2;
        public Paint paint;

        public Line(float x1, float y1) {
            this.x1 = x1;
            this.y1 = y1;
            paint = new Paint();
        }

        public Line(float x1, float y1, float x2, float y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            paint = new Paint();
        }

        public void setEndXY(float x, float y) {
            x2 = x;
            y2 = y;
        }

        public void setARGBW(int c, float w) {
            paint.setColor(c);
            paint.setStrokeWidth(w);
        }

        public Paint getPaint() {
            return this.paint;
        }

        @Override
        public void onDraw(Canvas canvas) {
            canvas.drawLine(x1, y1, x2, y2, paint);
        }
    }

    public class KeworkerPath implements Drawable, Cloneable {
        Paint bitmapPaint;
        Bitmap bitmap;
        Paint paint;
        Path path;

        public KeworkerPath(Paint paint, Path path, Bitmap bitmap, Paint bitmapPaint) {
            this.paint = new Paint();
            this.paint.setColor(paint.getColor());
            this.paint.setAntiAlias(true);
            this.paint.setDither(true);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeJoin(Paint.Join.ROUND);
            this.paint.setStrokeCap(Paint.Cap.ROUND);
            this.paint.setStrokeWidth(paint.getStrokeWidth());
            this.path = path;
            this.bitmap = bitmap;
            this.bitmapPaint = new Paint();
            this.bitmapPaint.setColor(paint.getColor());
            this.bitmapPaint.setStrokeWidth(paint.getStrokeWidth());
            this.bitmapPaint.setAntiAlias(true);
        }

        @Override
        public void setEndXY(float x, float y) {}

        @Override
        public void setARGBW(int c, float w) {

        }

        public void onDraw(Canvas canvas) {
            canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
            canvas.drawPath(this.path, paint);
        }
    }

    public interface Drawable {
        void setEndXY(float x, float y);

        void setARGBW(int c, float w);

        void onDraw(Canvas canvas);
    }
}
