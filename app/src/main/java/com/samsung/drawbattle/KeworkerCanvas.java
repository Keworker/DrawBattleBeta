package com.samsung.drawbattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class KeworkerCanvas extends View {
    protected Paint paint, line, eraser;
    protected List<Drawable> lines = new ArrayList<>();
    protected final int MAX_ARGB = 255, MAX_WIDTH = 80;
    protected boolean paintMode = true, lineMode = false, eraserMode = false, stickerAdd = false;
    protected Paint bitmapPaint, bitmapEraser;
    protected Canvas canvas;
    protected Bitmap bitmap;
    protected Context context;
    protected float paintX, paintY;
    protected float eraserX, eraserY;
    private final float TOUCH_TOLERANCE = 4;
    protected Line curLine;
    protected KeworkerPath curPath;

    public KeworkerCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        bitmapEraser = new Paint(Paint.DITHER_FLAG);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        line = new Paint();
        eraser = new Paint();
        eraser.setAntiAlias(true);
        eraser.setDither(true);
        eraser.setStyle(Paint.Style.STROKE);
        eraser.setStrokeJoin(Paint.Join.ROUND);
        eraser.setStrokeCap(Paint.Cap.ROUND);
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
        if (MainGameActivity.getGameStage() % 2 != 0 || DrawTournamentActivity.isTournament()) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (paintMode) {
                        onPaintActionDown(event.getX(), event.getY());
                        curPath = (KeworkerPath) lines.get(lines.size() - 1);
                        invalidate();
                    }
                    else if (lineMode) {
                        lines.add(new Line(event.getX(), event.getY()));
                        curLine = (Line) lines.get(lines.size() - 1);
                        curLine.setColorAndWidth(paint.getColor(), paint.getStrokeWidth());
                    }
                    else if (eraserMode) {
                        onEraserActionDown(event.getX(), event.getY());
                        curPath = (KeworkerPath) lines.get(lines.size() - 1);
                        invalidate();
                    }
                    else if (stickerAdd) {

                    }
                    break;
                }

                case MotionEvent.ACTION_MOVE: {
                    if (paintMode) {
                        onPaintActionMove(event.getX(), event.getY());
                    }
                    else if (lineMode) {
                        curLine.setEndXY(event.getX(), event.getY());
                    }
                    else if (eraserMode) {
                        onEraserActionMove(event.getX(), event.getY());
                    }
                    else if (stickerAdd) {

                    }
                    invalidate();
                    break;
                }

                case MotionEvent.ACTION_UP: {
                    if (paintMode) {
                        onPaintActionUp();
                    }
                    else if (lineMode) {
                        curLine.setEndXY(event.getX(), event.getY());
                    }
                    else if (eraserMode) {
                        onEraserActionUp();
                    }
                    invalidate();
                    break;
                }
            }
        }
        return true;
    }

    private void onPaintActionDown(float x, float y) {
        Path path = new Path();
        path.reset();
        path.moveTo(x, y);
        lines.add(new KeworkerPath(paint, path, bitmap, bitmapPaint));
        paintX = x;
        paintY = y;
    }

    private void onPaintActionMove(float x, float y) {
        float dx = Math.abs(x - paintX);
        float dy = Math.abs(y - paintY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            curPath.path.quadTo(paintX, paintY, (x + paintX)/2, (y + paintY)/2);
            paintX = x;
            paintY = y;
        }
    }

    private void onPaintActionUp() {
        curPath.path.lineTo(paintX, paintY);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
    }

    private void onEraserActionDown(float x, float y) {
        Path path = new Path();
        path.reset();
        path.moveTo(x, y);
        lines.add(new KeworkerPath(eraser, path, bitmap, bitmapEraser));
        eraserX = x;
        eraserY = y;
    }

    private void onEraserActionMove(float x, float y) {
        float dx = Math.abs(x - eraserX);
        float dy = Math.abs(y - eraserY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            curPath.path.quadTo(eraserX, eraserY,
                    (x + eraserX)/2, (y + eraserY)/2);
            eraserX = x;
            eraserY = y;
        }
    }

    private void onEraserActionUp() {
        curPath.path.lineTo(eraserX, eraserY);
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
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

    public void setPaintARGB(int a, int r, int g, int b) throws KeworkerException {
        if (a >= 0 && a <= MAX_ARGB && r >= 0 && r <= MAX_ARGB
                && g >= 0 && g <= MAX_ARGB && b >= 0 && b <= MAX_ARGB) {
            paint.setARGB(a, r, g, b);
            line.setARGB(a, r, g, b);
        }
        else {
            throw new KeworkerException();
        }
    }

    public Paint getEraser() {
        return eraser;
    }

    public void setWidth(float width) throws KeworkerException {
        if (width * MAX_WIDTH / 100 > 0
                && width * MAX_WIDTH / 100 <= MAX_WIDTH) {
            paint.setStrokeWidth(width * MAX_WIDTH / 100);
            line.setStrokeWidth(width * MAX_WIDTH / 100);
            eraser.setStrokeWidth(width * MAX_WIDTH / 100);
        }
        else {
            throw new KeworkerException();
        }
    }

    public void back() {
        if (lines.size() > 0) {
            lines.remove(lines.size() - 1);
            invalidate();
        }
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

        public void setColorAndWidth(int color, float width) {
            paint.setColor(color);
            paint.setStrokeWidth(width);
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

        public void onDraw(Canvas canvas) {
            canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
            canvas.drawPath(this.path, paint);
        }
    }

    public interface Drawable {
        void onDraw(Canvas canvas);
    }

    public class KeworkerException extends Exception {
        protected final String LOG_TAG_EXC = "Exception";
        /*We made our own exception class, because if we use the standard exception class,
                we will not be able to notice an exception that is not related to our methods*/
        public KeworkerException() {
            Log.e(LOG_TAG_EXC, "Exception was called because you make mistake while" +
                    "write code, Keworker!");
        }
    }
}
