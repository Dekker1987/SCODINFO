package com.scodeinfo.dekker.scod_info;

import android.app.Application;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by Sergii on 12.03.2018.
 */

@ReportsCrashes(mailTo = "reports@yourdomain.com",
                mode = ReportingInteractionMode.TOAST,
                resToastText = R.string.err_header)
public class App extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            ACRA.init(this);
      }
}
