package com.lujianfei.icecontroller.ui.others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

public class ListAdapter extends BaseAdapter
{
  private OnAccButtonTap accButtonTap;
  private Context context;
  private List<UserAccount> lists;
  private int selectedPosition = -1;

  public ListAdapter(Context paramContext, List<UserAccount> paramList)
  {
    this.context = paramContext;
    this.lists = paramList;
  }

  public int getCount()
  {
    return this.lists.size();
  }

  public Object getItem(int paramInt)
  {
    return this.lists.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getSelectedPosition()
  {
    return this.selectedPosition;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    TextAndButton localTextAndButton = null;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.context).inflate(2130903041, null);
      localTextAndButton = new TextAndButton();
      localTextAndButton.username = ((TextView)paramView.findViewById(2131230723));
      localTextAndButton.btn_acc = ((Button)paramView.findViewById(2131230724));
      localTextAndButton.row = ((RelativeLayout)paramView.findViewById(2131230722));
      paramView.setTag(localTextAndButton);
    }
    while (true)
    {
      UserAccount localUserAccount = (UserAccount)this.lists.get(paramInt);
      localTextAndButton.username.setText(localUserAccount.username);
      localTextAndButton.btn_acc.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ListAdapter.this.accButtonTap != null)
            ListAdapter.this.accButtonTap.onListAccTap(paramInt);
        }
      });
      if (paramInt != this.selectedPosition)
        break;
      localTextAndButton.row.setBackgroundColor(-16777216);
      localTextAndButton.username.setTextColor(-1);
      return paramView;
    }
    localTextAndButton.row.setBackgroundColor(0);
    localTextAndButton.username.setTextColor(-16777216);
    return paramView;
  }

  public void setOnAccButtonTap(OnAccButtonTap paramOnAccButtonTap)
  {
    this.accButtonTap = paramOnAccButtonTap;
  }

  public void setSelectedPosition(int paramInt)
  {
    this.selectedPosition = paramInt;
    notifyDataSetInvalidated();
  }

  public static abstract interface OnAccButtonTap
  {
    public abstract void onListAccTap(int paramInt);
  }

  class TextAndButton
  {
    Button btn_acc;
    RelativeLayout row;
    TextView username;

    TextAndButton()
    {
    }
  }
}

/* Location:           D:\DiskF_bak\lujianfei\J-�?��部\S-苏达武部门经理\D-待破解\Crack\dex2jar-0.0.9.15\classes_dex2jar.jar
 * Qualified Name:     com.fafu.marlight_android.others.ListAdapter
 * JD-Core Version:    0.6.2
 */