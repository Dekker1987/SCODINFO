package com.scodeinfo.dekker.scod_info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import com.scodeinfo.dekker.scod_info.adapters.ScodListAdapter;
import com.scodeinfo.dekker.scod_info.model.ScodeModel;
import com.scodeinfo.dekker.scod_info.model.ScodeModelChild;
import com.scodeinfo.dekker.scod_info.utils.ContentUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String LOG_TAG = getClass().getName();
    private int backPressCounter;
    private ActionBar actionBar;
    private RecyclerView rc_scode_lst;
    private RadioButton rb_cmd_v4;
    private RadioButton rb_azm_ng;
    private EditText ed_scode_filter;
    private ContentUtil contentUtil;
    private List<ScodeModel> scodeParentList;
    private List<ParentObject> scodeParentListFiltered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initContent();
        initUI();

    }

    private void initContent(){
        contentUtil = new ContentUtil(getApplicationContext());
    }

    private void initUI(){
        initNhideToolbar();
        initScodeListRecView();

        rb_cmd_v4 = findViewById(R.id.rb_cmd_v4);
        rb_azm_ng = findViewById(R.id.rd_azm_ng);
        ed_scode_filter = findViewById(R.id.ed_scode_filter);
        ed_scode_filter.addTextChangedListener(edFilter);
    }

    private void initScodeListRecView(){
        scodeParentList = contentUtil.getScodeList();
        scodeParentListFiltered = new ArrayList<>();

        List<ParentObject> parentObject = new ArrayList<>();

        for(final ScodeModel scodeModel:scodeParentList){
            List<Object> childList = new ArrayList();
            childList.add(new ScodeModelChild(){{
                   setFullDescription(scodeModel.getScodeFullDescription());
             }});

            scodeModel.setChildObjectList(childList);
            parentObject.add(scodeModel);
        }

        rc_scode_lst = findViewById(R.id.rc_scode_lst);
        rc_scode_lst.setAdapter(new ScodListAdapter(MainActivity.this,parentObject){{
                                    setParentClickableViewAnimationDefaultDuration();
                                    setParentAndIconExpandOnClick(true);
        }});
        rc_scode_lst.setLayoutManager(new LinearLayoutManager(this));
        rc_scode_lst.setHasFixedSize(true);
    }

    TextWatcher edFilter = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            scodeParentListFiltered.clear();
            for(ScodeModel scodeModel: scodeParentList){
                if(scodeModel.getScodeNo().toLowerCase().startsWith(ed_scode_filter.getText().toString())){
                    scodeParentListFiltered.add(scodeModel);
                }
            }

            rc_scode_lst.setAdapter(null);
            rc_scode_lst.setAdapter(new ScodListAdapter(MainActivity.this,scodeParentListFiltered){{
                setParentClickableViewAnimationDefaultDuration();
                setParentAndIconExpandOnClick(true);
            }});
            rc_scode_lst.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rc_scode_lst.setHasFixedSize(true);
        }
    };


    private void initNhideToolbar(){
        actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rb_cmd_v4:
                Log.v(LOG_TAG,"cmd_v4");
                break;

            case R.id.rd_azm_ng:
                Log.v(LOG_TAG,"cmd_v4");
                break;
            default:
                Log.v(LOG_TAG,"err...");
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        ((ScodListAdapter)rc_scode_lst.getAdapter()).onSaveInstanceState(outState);
    }
}
