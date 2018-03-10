package com.scodeinfo.dekker.scod_info.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.scodeinfo.dekker.scod_info.R;
import com.scodeinfo.dekker.scod_info.model.ScodeModel;
import com.scodeinfo.dekker.scod_info.model.ScodeModelChild;
import com.scodeinfo.dekker.scod_info.utils.ShareUtil;
import com.scodeinfo.dekker.scod_info.viewholders.ScodeListChildHolder;
import com.scodeinfo.dekker.scod_info.viewholders.ScodeListParentHolder;

import java.util.List;

/**
 * Created by Sergii on 24.02.2018.
 */

public class ScodListAdapter extends ExpandableRecyclerAdapter<ScodeListParentHolder,ScodeListChildHolder> {

    private LayoutInflater inflater;
    private Activity activity;

    protected ScodListAdapter(Activity activity, List<ParentObject> parentItemList){
        super(activity,parentItemList);
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }

    @Override
    public ScodeListParentHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.scod_item_parent_short_descr,viewGroup,false);
        return new ScodeListParentHolder(view);
    }

    @Override
    public ScodeListChildHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.scod_item_child_full_descr,viewGroup,false);
        return new ScodeListChildHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ScodeListParentHolder scodeListParentHolder, int i, Object o) {
        final ScodeModel scodeModel = (ScodeModel)o;
        scodeListParentHolder.tv_scode_num.setText(scodeModel.getScodeNo());
        scodeListParentHolder.tv_scode_short_descr.setText(scodeModel.getScodeShortDescription());
        scodeListParentHolder.iv_share_scode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 ShareUtil.shareText(scodeModel.toString(),activity);
            }
        });
    }

    @Override
    public void onBindChildViewHolder(ScodeListChildHolder scodeListChildHolder, int i, Object o) {
        ScodeModelChild title = (ScodeModelChild)o;
        scodeListChildHolder.tv_scode_full_descr.setText(title.getFullDescription());
    }
}
