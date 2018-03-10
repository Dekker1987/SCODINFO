package com.scodeinfo.dekker.scod_info.viewholders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.scodeinfo.dekker.scod_info.R;

/**
 * Created by Sergii on 28.02.2018.
 */

public class ScodeListChildHolder extends ChildViewHolder {

    public TextView tv_scode_full_descr;

    public ScodeListChildHolder(View itemView) {
        super(itemView);
        tv_scode_full_descr = itemView.findViewById(R.id.tv_scode_full_descr);
    }
}
