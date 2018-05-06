package com.scodeinfo.dekker.scod_info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.scodeinfo.dekker.scod_info.adapters.ScodListAdapter;
import com.scodeinfo.dekker.scod_info.fragments.AdMobDialogFrag;
import com.scodeinfo.dekker.scod_info.model.ScodeModel;
import com.scodeinfo.dekker.scod_info.model.ScodeModelChild;
import com.scodeinfo.dekker.scod_info.utils.ContentUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int backPressCounter;
    private ActionBar actionBar;
    private RecyclerView rc_scode_lst;
    private RadioButton rb_cmd_v4;
    private RadioButton rb_azm_ng;
    private EditText ed_scode_filter;
    private ImageView iv_info;
    private ContentUtil contentUtil;
    private List<ScodeModel> scodeParentListCmdV4;
    private List<ParentObject> scodeParentListFilteredCmdV4;
    private List<ScodeModel> scodeParentListAzmNg;
    private List<ParentObject> scodeParentListFilteredAzmNg;
    private AdView mAdview;
    private InterstitialAd interstitialAd;
    private boolean isCmdV4Enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initContent();
        initUI();

        initAdMob();
    }

    private void initContent(){
        contentUtil = new ContentUtil(getApplicationContext());
        initCmdV4Content();
        initAzmNgContent();
    }

    private void initCmdV4Content(){
        scodeParentListCmdV4 = contentUtil.getScodeList("cmd_v4_scode_list.txt");
    }

    private void initAzmNgContent(){
        scodeParentListAzmNg = contentUtil.getScodeList("azm_ng_scode_list.txt");
    }

    private void initUI(){
        initNhideToolbar();

        iv_info = findViewById(R.id.iv_info);
        iv_info.setClickable(true);
        iv_info.setOnClickListener(this);

        rb_cmd_v4 = findViewById(R.id.rb_cmd_v4);
        rb_cmd_v4.setOnClickListener(this);


        rb_azm_ng = findViewById(R.id.rd_azm_ng);
        rb_azm_ng.setOnClickListener(this);

        rc_scode_lst = findViewById(R.id.rc_scode_lst);
        rc_scode_lst.setLayoutManager(new LinearLayoutManager(this));
        rc_scode_lst.setHasFixedSize(true);

        ed_scode_filter = findViewById(R.id.ed_scode_filter);
        ed_scode_filter.addTextChangedListener(edFilter);

        initScodeListRecView(getCmdV4List());
    }

    private void initScodeListRecView(List<ParentObject> scodeList){
         rc_scode_lst.setAdapter(null); // need for removing previous adapter
         rc_scode_lst.setAdapter(new ScodListAdapter(MainActivity.this,scodeList){{
         setParentClickableViewAnimationDefaultDuration();
         setParentAndIconExpandOnClick(true);
        }});
    }

    private List<ParentObject> getCmdV4List(){
        scodeParentListFilteredCmdV4 = new ArrayList<>();
        List<ParentObject> parentObjectCmdV4 = new ArrayList<>();

        for(final ScodeModel scodeModel:scodeParentListCmdV4){
            List<Object> childList = new ArrayList();
            childList.add(new ScodeModelChild(){{
                setFullDescription(scodeModel.getScodeFullDescription());
            }});

            scodeModel.setChildObjectList(childList);
            parentObjectCmdV4.add(scodeModel);
        }
        return parentObjectCmdV4;
    }

    private List<ParentObject> getAzmNgList(){
        scodeParentListFilteredAzmNg = new ArrayList<>();
        List<ParentObject> parentObjectAzmNg = new ArrayList<>();

        for(final ScodeModel scodeModel:scodeParentListAzmNg){
            List<Object> childList = new ArrayList();
            childList.add(new ScodeModelChild(){{
                setFullDescription(scodeModel.getScodeFullDescription());
            }});

            scodeModel.setChildObjectList(childList);
            parentObjectAzmNg.add(scodeModel);
        }
        return parentObjectAzmNg;
    }

    private final TextWatcher edFilter = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            makeFilter();
        }
    };

    private void makeFilter(){
        if(isCmdV4Enabled){
            filterCmdV4List();
        } else {
            filterAzmNgList();
        }
    }

    private void filterCmdV4List(){
        scodeParentListFilteredCmdV4=new ArrayList<>();
        for(ScodeModel scodeModel: scodeParentListCmdV4){
            if(scodeModel.getScodeNo().toLowerCase().startsWith(ed_scode_filter.getText().toString())){
                scodeParentListFilteredCmdV4.add(scodeModel);
            }
        }
        rc_scode_lst.setAdapter(null);
        rc_scode_lst.setAdapter(new ScodListAdapter(MainActivity.this,scodeParentListFilteredCmdV4){{
            setParentClickableViewAnimationDefaultDuration();
            setParentAndIconExpandOnClick(true);
        }});
    }

    private void filterAzmNgList(){
        scodeParentListFilteredAzmNg = new ArrayList<>();
        for(ScodeModel scodeModel: scodeParentListAzmNg){
            if(scodeModel.getScodeNo().toLowerCase().startsWith(ed_scode_filter.getText().toString())){
                scodeParentListFilteredAzmNg.add(scodeModel);
            }
        }
        rc_scode_lst.setAdapter(null);
        rc_scode_lst.setAdapter(new ScodListAdapter(MainActivity.this,scodeParentListFilteredAzmNg){{
            setParentClickableViewAnimationDefaultDuration();
            setParentAndIconExpandOnClick(true);

        }});
    }

    private void initNhideToolbar(){
        actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    private void initAdMob(){
        initBottomBanner();
        initInterstitial();
    }

    private void initBottomBanner(){
        mAdview = findViewById(R.id.adView);
        mAdview.setVisibility(View.GONE);
        mAdview.loadAd(getAdRequest());

        initAdListener();
    }

    private void initInterstitial(){
        interstitialAd = new InterstitialAd(this);
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

    private void initAdListener(){
        mAdview.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mAdview.setVisibility(View.VISIBLE);
            }
        });
    }

    private AdRequest getAdRequest(){
        return new AdRequest.Builder().addTestDevice("55E27CC73222E3352C7957A2D001E3BF").build();
    }

    public void onBackPressed() {
        onTwicePressClose();
    }

    private void onTwicePressClose() {
        backPressCounter++;
        switch (backPressCounter) {
            case 1:
                Toast.makeText(getApplicationContext(),"Press again to exit...",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                closeActivity();
                break;
            default:
                break;
        }
    }

    private void closeActivity(){
        finish();
    }

    private void startAdMobDialogFrag(){
        AdMobDialogFrag adMobDialogFrag1 = new  AdMobDialogFrag();
        adMobDialogFrag1.show(getFragmentManager(),"");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rb_cmd_v4:
                cmdV4RdBtnSelected();
                clearEdScodeFilter();
                break;
            case R.id.rd_azm_ng:
                azmNgRdBtnSelected();
                clearEdScodeFilter();
                break;
            case R.id.iv_info:
                startAdMobDialogFrag();
                break;
            default:
                break;
        }
    }

    private void cmdV4RdBtnSelected(){
        isCmdV4Enabled=true;
        initScodeListRecView(getCmdV4List());
    }

    private void azmNgRdBtnSelected(){
        isCmdV4Enabled=false;
        initScodeListRecView(getAzmNgList());
    }

    private void clearEdScodeFilter(){
        ed_scode_filter.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        ((ScodListAdapter)rc_scode_lst.getAdapter()).onSaveInstanceState(outState);
    }
}
