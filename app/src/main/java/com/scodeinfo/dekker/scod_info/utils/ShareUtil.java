package com.scodeinfo.dekker.scod_info.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Sergii on 03.03.2018.
 */

public class ShareUtil {

    private ShareUtil(){
        throw new AssertionError();
    }

    public static void shareText(String textToShare, Activity activity){
        if(textToShare!=null){
            if(!textToShare.equals("")){
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                share.putExtra(Intent.EXTRA_TEXT, textToShare);
                activity.startActivity(Intent.createChooser(share, "Share SCODE"));
            }
        }
    }
}
