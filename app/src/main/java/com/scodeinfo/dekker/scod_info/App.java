package com.scodeinfo.dekker.scod_info;

import android.app.Application;

import com.scodeinfo.dekker.scod_info.helpers.SharedHelper;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

/**
 * Created by Sergii on 12.03.2018.
 */

@ReportsCrashes(mailTo = "dekker1349@gmail.com", mode = ReportingInteractionMode.TOAST, resToastText = R.string.err_header)

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initACRA();
        initSharedHelper();
    }

    private void initACRA(){
        ACRA.init(this);
    }

    private void initSharedHelper(){
        SharedHelper.getInstance().init(this);
       // SharedHelper.getInstance().resetCounters();
    }
}
