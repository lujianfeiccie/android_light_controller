package com.lujianfei.icecontroller.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class PointPageView extends View
{
  private int mDisplayIndex;
  private int mDisplaySize;
  private int mPageIndex;
  private int mPageSize;
  private int mPointSize;
  private int mPointSpan;
  private int mSelectPointSize;
  private int mStep;
  private Paint paint;

  public PointPageView(Context paramContext)
  {
    super(paramContext, null);
    init();
  }

  public PointPageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

	private void drawAllPoint(Canvas canvas) {
		canvas.save();
		final int paddingLeft = mPointSpan;
		final int width = getMeasuredWidth();
		final int height = getMeasuredHeight();
		int radius = mPointSize;
		int contentWidth = (radius + paddingLeft) * mPageSize;
		int beginX = (width - contentWidth) / 2;
		int beginY = height / 2;
		for (int i = 0; i < mDisplaySize; i++) {
			if (i == mDisplayIndex) {
				paint.setAlpha(255);
				radius = mSelectPointSize;
			} else {
				paint.setAlpha(128);
				radius = mPointSize;
			}
			canvas.drawCircle(beginX, beginY, radius, paint);

			beginX = beginX + radius + radius + paddingLeft;
		}
		canvas.restore();
	}


  private void init()
  {
    this.mPointSize = 3;
    this.mSelectPointSize = this.mPointSize;
    this.mPointSpan = (3 * this.mPointSize);
    this.mStep = 1;
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.paint.setColor(-1);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    drawAllPoint(paramCanvas);
  }

  public int getPageIndex()
  {
    return this.mPageIndex;
  }

  public int getPageSize()
  {
    return this.mPageSize;
  }

  public int getStep()
  {
    return this.mStep;
  }

  public void setColor(int paramInt)
  {
    this.paint.setColor(paramInt);
  }

  public void setPageIndex(int paramInt)
  {
    this.mPageIndex = Math.min(Math.max(paramInt, 0), -1 + this.mPageSize);
    this.mDisplayIndex = ((int)Math.floor(this.mPageIndex / this.mStep));
    invalidate();
  }

  public void setPageSize(int paramInt)
  {
    this.mPageSize = Math.max(paramInt, 0);
    this.mDisplaySize = ((int)Math.ceil(this.mPageSize / this.mStep));
    invalidate();
  }

  public void setPointSize(int paramInt)
  {
    this.mPointSize = paramInt;
  }

  public void setPointSpan(int paramInt)
  {
    this.mPointSpan = paramInt;
  }

  public void setSelectPointSize(int paramInt)
  {
    this.mSelectPointSize = paramInt;
  }

  public void setStep(int paramInt)
  {
    this.mStep = paramInt;
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-技术部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.custom.PointPageView
 * JD-Core Version:    0.6.2
 */