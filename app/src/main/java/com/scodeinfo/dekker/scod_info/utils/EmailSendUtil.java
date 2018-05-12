package com.scodeinfo.dekker.scod_info.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Sergii on 12.05.2018.
 */

public class EmailSendUtil {
    private EmailSendUtil(){
        throw new AssertionError();
    }

    public static void sendEmailViaIntent(Activity activity){
        if(activity!=null){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                    Uri.fromParts("mailto","dekker1349@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SCOD_REMARKS");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
            activity.startActivity(Intent.createChooser(emailIntent, "Send email..."));
        }
    }
}
