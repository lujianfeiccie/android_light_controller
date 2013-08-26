package com.lujianfei.icecontroller.ui.others;

import android.os.Environment;

import com.lujianfei.icecontroller.ui.Activity1;
import com.lujianfei.icecontroller.ui.Activity2;
import com.lujianfei.icecontroller.ui.Activity3;

import java.io.File;
import java.util.List;

public class GlobalData
{
  public static Activity1 activity1;
  public static Activity2 activity2;
  public static Activity3 activity3;
  public static List<UserAccount> userList;

  public static String getSDPath()
  {
    boolean bool = Environment.getExternalStorageState().equals("mounted");
    File localFile = null;
    if (bool)
      localFile = Environment.getExternalStorageDirectory();
    return localFile.toString();
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-�?��部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.others.GlobalData
 * JD-Core Version:    0.6.2
 */