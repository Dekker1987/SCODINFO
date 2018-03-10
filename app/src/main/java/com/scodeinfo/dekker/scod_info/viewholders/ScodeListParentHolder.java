package com.scodeinfo.dekker.scod_info.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.scodeinfo.dekker.scod_info.R;

/**
 * Created by Sergii on 28.02.2018.
 */

public class ScodeListParentHolder extends ParentViewHolder{

    public ImageView iv_expand;
    public ImageView iv_share_scode;
    public TextView tv_scode_num;
    public TextView tv_scode_short_descr;

    public ScodeListParentHolder(View itemView) {
        super(itemView);
        tv_scode_num = itemView.findViewById(R.id.tv_scode_num);
        tv_scode_short_descr = itemView.findViewById(R.id.tv_scode_full_descr);

        initShareIco();
        initExpandIco();

    }

    private void initShareIco(){
        iv_share_scode = itemView.findViewById(R.id.iv_share_scode);
        iv_share_scode.setImageResource(R.drawable.ic_share_24dp);
    }

    private void initExpandIco(){
        iv_expand = itemView.findViewById(R.id.iv_expand);
        iv_expand.setImageResource(R.drawable.ic_expand_more_black_24dp);
    }
}
