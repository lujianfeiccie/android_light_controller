package com.lujianfei.icecontroller.ui;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lujianfei.icecontroller.R;
import com.lujianfei.icecontroller.ui.custom.ContainerView;
import com.lujianfei.icecontroller.ui.custom.PointPageView;
import com.lujianfei.icecontroller.ui.util.Util;

public class ContainerGroup extends ActivityGroup {
	 private ContainerView container;
	  private PointPageView pageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initSubActivity();
    }
    
    private void findView()
    {
      this.container = ((ContainerView)findViewById(R.id.container));
      this.container.setOnPageChangeListener(new ContainerView.OnPageChangeListener()
      {
        public void onPageChane(int paramAnonymousInt)
        {
          ContainerGroup.this.pageView.setPageIndex(paramAnonymousInt);
        }
      });
      this.pageView = ((PointPageView)findViewById(R.id.pointPage));
      this.pageView.setPageSize(3);
      this.pageView.setPointSize(Util.DipToPixels(this, 3));
    }
    
    private void initSubActivity()
    {
      View localView1 = getLocalActivityManager().startActivity("Activity1", new Intent(this, Activity1.class)).getDecorView();
      this.container.addView(localView1);
      View localView2 = getLocalActivityManager().startActivity("Activity2", new Intent(this, Activity2.class)).getDecorView();
      this.container.addView(localView2);
      View localView3 = getLocalActivityManager().startActivity("Activity3", new Intent(this, Activity3.class)).getDecorView();
      this.container.addView(localView3);
    }
}
