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

/**
 * Created by Sergii on 29.04.2018.
 */

public class AdMobDialogFrag extends DialogFragment {

    private TextView tv_show_interstial;
    private TextView tv_show_rew_video;
    private TextView tv_about;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ad_mob_dialog_layout, container, false);
        initUI();

        return view;
    }

    private void initUI(){
        disableFrameAndTitle();

        tv_show_interstial = getActivity().findViewById(R.id.tv_show_interstial);
        tv_show_rew_video = getActivity().findViewById(R.id.tv_show_rew_video);
        tv_about = getActivity().findViewById(R.id.tv_about);
    }

    private void disableFrameAndTitle(){
        setStyle(DialogFragment.STYLE_NO_FRAME, 0);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    }
}
