package com.scodeinfo.dekker.scod_info.fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.scodeinfo.dekker.scod_info.R;
import com.scodeinfo.dekker.scod_info.utils.EmailSendUtil;

/**
 * Created by Sergii on 29.04.2018.
 */

public class AboutDialogFrag extends DialogFragment {

    private TextView tv_email;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_dialog_layout, container, false);
        initUI(view);

        return view;
    }

    private void initUI(View view){
        disableFrameAndTitle();

        tv_email = view.findViewById(R.id.tv_email);
        tv_email.setClickable(true);
        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailSendUtil.sendEmailViaIntent(getActivity());
            }
        });
    }

    private void disableFrameAndTitle(){
        if(getDialog().getWindow()!=null){
            setStyle(DialogFragment.STYLE_NO_FRAME, 0);
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
    }
}
