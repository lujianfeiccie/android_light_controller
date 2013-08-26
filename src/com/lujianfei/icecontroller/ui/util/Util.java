package com.lujianfei.icecontroller.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public class Util
{
  public static final String HOST = "host";
  private static String NAME = "ptcapp";
  public static final String PORT = "port";

  public static int DipToPixels(Context paramContext, int paramInt)
  {
    return (int)(0.5F + paramContext.getResources().getDisplayMetrics().density * paramInt);
  }

  public static int convertPxOrDip(float paramFloat, int paramInt)
  {
    float f = paramInt / paramFloat;
    if (paramInt >= 0);
    for (int i = 1; ; i = -1)
      return (int)(f + 0.5F * i);
  }

  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1);
    for (Bitmap.Config localConfig = Bitmap.Config.ARGB_8888; ; localConfig = Bitmap.Config.RGB_565)
    {
      Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
  }

  public static Bitmap getDisableBitmap(Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    int i = 0;
    if (i >= paramBitmap.getHeight())
      return localBitmap;
    for (int j = 0; ; j++)
    {
      if (j >= paramBitmap.getWidth())
      {
        i++;
        break;
      }
      localBitmap.setPixel(j, i, getDisaledColor(paramBitmap.getPixel(j, i)));
    }
    return localBitmap;
  }

  public static Drawable getDisableDrawable(Drawable paramDrawable)
  {
    return new BitmapDrawable(getDisableBitmap(((BitmapDrawable)paramDrawable).getBitmap()));
  }

  public static int getDisaledColor(int paramInt)
  {
    int i = Color.red(paramInt);
    int j = Color.blue(paramInt);
    int k = Color.green(paramInt);
    int m = Color.alpha(paramInt);
    int n = (int)(0.5F * i);
    int i1 = (int)(0.5F * j);
    return Color.argb(m, n, (int)(0.5F * k), i1);
  }

  public static Object getValue(Context paramContext, String paramString)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences(NAME, 32768);
    if (paramString.equals("host"))
      return localSharedPreferences.getString(paramString, "192.168.1.100");
    if (paramString.equals("port"))
      return Integer.valueOf(localSharedPreferences.getInt(paramString, 50000));
    return null;
  }

  public static void saveValue(Context paramContext, String paramString, Object paramObject)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(NAME, 32768).edit();
    if (paramString.equals("host"))
      localEditor.putString(paramString, (String)paramObject);
    while (true)
    {
      localEditor.commit();
      return;
    }
  }

  public Bitmap drawableToBitmap(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    if (paramDrawable.getOpacity() != -1);
    for (Bitmap.Config localConfig = Bitmap.Config.ARGB_8888; ; localConfig = Bitmap.Config.RGB_565)
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, paramInt1, paramInt2);
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
  }
}
