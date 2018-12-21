package com.example.laitianbing.badgeview_master;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 *
 * 创建人：赖天兵
 * 时间： 2018/12/21
 * 简书：https://www.jianshu.com/u/2229fd214880
 * 掘金：https://juejin.im/user/58647e21128fe1006d0f3f3e
 * github：https://github.com/93Laer
 *
 * 描述：徽章控件，显示效果：气泡提示消息数量等.
 */
public class BadgeView extends RelativeLayout {

    private static final int ANCHOR_LEFT_TOP = 0, ANCHOR_LEFT_BOTTOM = 1, ANCHOR_RIGHT_TOP = 2, ANCHOR_RIGHT_BOTTOM = 3;
    public static final int DEF_TEXT_SIZE = 20;
    public static final int DEF_HORIZON_PADDING = 10;
    public static final int DEF_VERTICAL_PADDING = 7;

    private Paint mBadgeTextPaint, mBadgeBgPaint, mBadgeBorderPaint;
    private int mBadgeBgcolor, mBadgeBordercolor, mBadgeTextcolor;
    private int mBadgeTextSize;
    private String mBadgeText;
    private int mBorderWidth;

    private int mBadgeAnchorPosition;
    private int mMarginHorizon, mMarginVertical;
    private float mPaddingH;
    private float mPaddingV;


    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BadgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BadgeView);
        mBadgeText = array.getString(R.styleable.BadgeView_badgeText);
        mBadgeBgcolor = array.getColor(R.styleable.BadgeView_badgeBgColor, 0);
        mBadgeBordercolor = array.getColor(R.styleable.BadgeView_badgeBorderColor, 0);
        mBadgeTextcolor = array.getColor(R.styleable.BadgeView_badgeTextColor, 0);
        mBorderWidth = (int) array.getDimension(R.styleable.BadgeView_badgeBorderWidth, 0);
        mBadgeTextSize = (int) array.getDimension(R.styleable.BadgeView_badgeTextSize, DEF_TEXT_SIZE);

        mBadgeAnchorPosition = array.getInt(R.styleable.BadgeView_badgeAnchorPosition, 2);
        mMarginHorizon = (int) array.getDimension(R.styleable.BadgeView_badgeMarginHorizon, 0);
        mMarginVertical = (int) array.getDimension(R.styleable.BadgeView_badgeMarginVertical, 0);

        mPaddingH = array.getDimension(R.styleable.BadgeView_badgePaddingHorizon, DEF_HORIZON_PADDING) * 2 + mBorderWidth;
        mPaddingV = array.getDimension(R.styleable.BadgeView_badgePaddingVertical, DEF_VERTICAL_PADDING) * 2 + mBorderWidth;

        array.recycle();
        mBadgeTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBadgeTextPaint.setTextSize(mBadgeTextSize);
        mBadgeTextPaint.setColor(mBadgeTextcolor);

        mBadgeBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBadgeBgPaint.setColor(mBadgeBgcolor);
        mBadgeBgPaint.setStyle(Paint.Style.FILL);

        mBadgeBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBadgeBorderPaint.setStyle(Paint.Style.STROKE);
        mBadgeBorderPaint.setColor(mBadgeBordercolor);
        mBadgeBorderPaint.setStrokeWidth(mBorderWidth);

    }

    public void setBadgeText(String text) {
        mBadgeText = text;
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawBadge(canvas);
    }


    private void drawBadge(Canvas canvas) {
        if (null == mBadgeText) {
            return;
        }

        Paint.FontMetrics metrics = mBadgeTextPaint.getFontMetrics();
        float textWidth = mBadgeTextPaint.measureText(mBadgeText);

        RectF rectF = getBadgeRectF(textWidth);
        if (rectF.width() > 0) {
            //绘制背景
            canvas.drawRoundRect(rectF, rectF.height() / 2, rectF.height() / 2, mBadgeBgPaint);
            //绘制徽章边框
            if (mBorderWidth > 0) {
                canvas.drawRoundRect(rectF, rectF.height() / 2, rectF.height() / 2, mBadgeBorderPaint);
            }

            canvas.drawText(mBadgeText, rectF.centerX() - textWidth / 2,
                    rectF.centerY() - (metrics.top + metrics.bottom) / 2, mBadgeTextPaint);
        }
    }

    private RectF getBadgeRectF(float textWidth) {
        float left = 0, right = 0, top = 0, bottom = 0, width = 0, height = 0;
        int offset = mBorderWidth / 2;


        switch (mBadgeAnchorPosition) {
            case ANCHOR_LEFT_TOP:
                left = mMarginHorizon + offset;
                right = mMarginHorizon + textWidth + mPaddingH + offset;
                top = mMarginVertical + offset;
                bottom = mMarginVertical + mBadgeTextSize + mPaddingV + offset;
                width = right - left;
                height = bottom - top;
                if (width < height) {
                    right = right + height - width;
                }

                break;
            case ANCHOR_LEFT_BOTTOM:
                left = mMarginHorizon + offset;
                right = mMarginHorizon + textWidth + mPaddingH + offset;
                top = getHeight() - mMarginVertical - mBadgeTextSize - mPaddingV - offset;
                bottom = top + mBadgeTextSize + mPaddingV - offset;
                width = right - left;
                height = bottom - top;
                if (width < height) {
                    right = right + height - width;
                }

                break;
            case ANCHOR_RIGHT_TOP:
                left = getWidth() - textWidth - mMarginHorizon - mPaddingH - offset;
                right = left + textWidth + mPaddingH - offset;
                top = mMarginVertical + offset;
                bottom = mMarginVertical + mBadgeTextSize + mPaddingV + offset;
                width = right - left;
                height = bottom - top;
                if (width < height) {
                    left = left - (height - width);
                }
                break;
            case ANCHOR_RIGHT_BOTTOM:
                left = getWidth() - textWidth - mMarginHorizon - mPaddingH - offset;
                right = left + textWidth + mPaddingH - offset;
                top = getHeight() - mBadgeTextSize - mMarginVertical - mPaddingV - offset;
                bottom = top + mBadgeTextSize + mPaddingV - offset;
                width = right - left;
                height = bottom - top;
                if (width < height) {
                    left = left - (height - width);
                }
                break;
        }

        return new RectF(left, top, right, bottom);
    }
}
