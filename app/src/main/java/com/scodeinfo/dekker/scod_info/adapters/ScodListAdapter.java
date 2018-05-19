package com.scodeinfo.dekker.scod_info.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.scodeinfo.dekker.scod_info.R;
import com.scodeinfo.dekker.scod_info.helpers.SharedHelper;
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
    private InterstitialAd interstitialAd;

    protected ScodListAdapter(Activity activity, List<ParentObject> parentItemList){
        super(activity,parentItemList);
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        initInterstitial();
    }

    private void initInterstitial(){
        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd.loadAd(getAdRequest());

        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed(){
                super.onAdClosed();
                interstitialAd.loadAd(getAdRequest());
            }
        });
    }

    private AdRequest getAdRequest(){
        return new AdRequest.Builder()
                .addTestDevice("55E27CC73222E3352C7957A2D001E3BF")
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
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
                makeShareCount();
            }
        });

        scodeListParentHolder.li_lay_scode_item.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                makeSCODEtouchCount();
                return false;
            }
        });
    }

    private void makeSCODEtouchCount(){
        int tempSCODEtouchCounter = SharedHelper.getInstance().getSCODEtouchCounter();
        tempSCODEtouchCounter++;
        SharedHelper.getInstance().setSCODEtouchCounter(tempSCODEtouchCounter);
        if((SharedHelper.getInstance().getSCODEtouchCounter()%20)==0){
            showInterstitial();
            SharedHelper.getInstance().setSCODEtouchCounter(0);
        }
    }

    private void makeShareCount(){
        int tempShareCounter = SharedHelper.getInstance().getShareCounter();
        tempShareCounter++;
        SharedHelper.getInstance().setShareCounter(tempShareCounter);
        if((SharedHelper.getInstance().getShareCounter()%10)==0){
            showInterstitial();
            SharedHelper.getInstance().setShareCounter(0);
       }
    }

    private void showInterstitial(){
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            interstitialAd.loadAd(getAdRequest());
        }
    }

    @Override
    public void onBindChildViewHolder(ScodeListChildHolder scodeListChildHolder, int i, Object o) {
        ScodeModelChild title = (ScodeModelChild)o;
        scodeListChildHolder.tv_scode_full_descr.setText(title.getFullDescription());
    }
}
