package com.scodeinfo.dekker.scod_info.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Sergii on 24.02.2018.
 */

public class ScodeModel implements ParentObject {

    private String scodeNo;
    private String scodeShortDescription;
    private String scodeFullDescription;
    private List<Object> mChildrenList;

    public String getScodeNo() {
        return scodeNo;
    }

    protected void setScodeNo(String scodeNo) {
        this.scodeNo = scodeNo;
    }

    public String getScodeShortDescription() {
        return scodeShortDescription;
    }

    protected void setScodeShortDescription(String scodeShortDescription) {
        this.scodeShortDescription = scodeShortDescription;
    }

    public String getScodeFullDescription() {
        return scodeFullDescription;
    }

    protected void setScodeFullDescription(String scodeFullDescription) {
        this.scodeFullDescription = scodeFullDescription;
    }

    public String toString(){
        return "No: " + scodeNo + "\n"
                + "Short Description: " + scodeShortDescription + "\n"
                + "Full Description: " + scodeFullDescription;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        this.mChildrenList=list;
    }
}
