package com.lujianfei.icecontroller.ui.others;

public class UserAccount
{
  public String IpAddress;
  public int id;
  public int port;
  public String username;

  public UserAccount()
  {
    this.id = -1;
    this.username = "Username";
    this.IpAddress = "192.168.1.100";
    this.port = 50000;
  }

  public UserAccount(UserAccount paramUserAccount)
  {
    this.id = paramUserAccount.id;
    this.username = paramUserAccount.username;
    this.IpAddress = paramUserAccount.IpAddress;
    this.port = paramUserAccount.port;
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-�?��部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.others.UserAccount
 * JD-Core Version:    0.6.2
 */