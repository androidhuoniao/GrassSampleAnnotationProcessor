package com.grass;

import com.grass.bean.ActivityDataStore;

public final class ActivitysStore {
  static {
    ActivityDataStore.getInstance().add("honey","des","com.baidu.com.Activity");
  }
}
