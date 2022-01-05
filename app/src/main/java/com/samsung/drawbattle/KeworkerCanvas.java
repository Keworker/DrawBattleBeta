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
    protected List<Line> lines = new ArrayList<>();
    protected final int MAX_ARGB = 255, MAX_WIDTH = 80;
    //If paintMode mode = paint, else if lineMode mode = line, else mode = eraser
    protected boolean paintMode = true, lineMode = false;
    protected float pointX, pointY;
    protected Path path, circlePath;
    protected Paint bitmapPaint, circlePaint;
    protected int width, height;
    protected Canvas canvas;
    protected Bitmap bitmap;
    protected Context context;
    protected float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    public KeworkerCanvas(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
        path = new Path();
        circlePath = new Path();
        bitmapPaint = new Paint(Paint.DITHER_FLAG);
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setARGB(255, 255, 0, 0);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f);
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
            canvas.drawLine(lines.get(i).x1, lines.get(i).y1,
                    lines.get(i).x2, lines.get(i).y2, lines.get(i).getPaint());
        }
        canvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        canvas.drawPath(path,  paint);
        canvas.drawPath( circlePath,  circlePaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //This is equals, but faster then (MainGameActivity.getGameStage() % 2 != 0)
        if ((MainGameActivity.getGameStage() & 2) == 0) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (paintMode) { //Paint mode
                        onPaintActionDown(event.getX(), event.getY());
                        invalidate();
                    }
                    else if (lineMode) { //Line mode
                        lines.add(new Line(event.getX(), event.getY()));
                    }
                    else { //Eraser mode
                        pointX = event.getX();
                        pointY = event.getY();
                    }
                    break;
                }

                case MotionEvent.ACTION_MOVE: {
                    if (paintMode) {
                        onPaintActionMove(event.getX(), event.getY());
                        invalidate();
                    } else if (!lineMode) {
                        lines.add(new Line(pointX, pointY, event.getX(), event.getY()));
                        lines.get(lines.size() - 1).paint.setColor(eraser.getColor());
                        lines.get(lines.size() - 1).paint.setStrokeWidth(eraser.getStrokeWidth());
                        pointX = event.getX();
                        pointY = event.getY();
                        invalidate();
                    }
                    break;
                }

                case MotionEvent.ACTION_UP: {
                    if (paintMode) {
                        onPaintActionUp();
                        invalidate();
                    }
                    else if (lineMode) {
                        lines.get(lines.size() - 1).setEndXY(event.getX(), event.getY());
                        lines.get(lines.size() - 1).paint.setColor(paint.getColor());
                        lines.get(lines.size() - 1).paint.setStrokeWidth(paint.getStrokeWidth());
                        invalidate();
                    }
                    break;
                }
            }
        }
        return true;
    }

    private void onPaintActionDown(float x, float y) {
        //showDialog();
        path.reset();
        path.moveTo(x, y);
        mX = x;
        mY = y;

    }

    private void onPaintActionMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            path.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }

    private void onPaintActionUp() {
        path.lineTo(mX, mY);
        canvas.drawPath(path, paint);
        path.reset();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
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
        if (width > 0 && width <= MAX_WIDTH) {
            paint.setStrokeWidth(width * MAX_WIDTH / 100);
            line.setStrokeWidth(width * MAX_WIDTH / 100);
            eraser.setStrokeWidth(width * MAX_WIDTH / 100);
        }
        else {
            throw new Exception();
        }
    }

    public void setEraserMode() {
        paintMode = false;
        lineMode = false;
    }

    public void setPaintMode() {
        paintMode = true;
        lineMode = false;
    }

    public void setLineMode() {
        paintMode = false;
        lineMode = true;
    }

    public class Line {
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

        public Paint getPaint() {
            return this.paint;
        }
    }
}
