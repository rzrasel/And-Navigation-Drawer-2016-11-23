package com.sm.navigationdrawerone;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RIFAN on 23-Nov-16.
 */

public class AdapterDrawerListView extends ArrayAdapter<ModelDrawerData> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<ModelDrawerData> drawerItems = new ArrayList<ModelDrawerData>();

    private boolean isClicked = false;
    private int selectedPosition;
    private int checkedColor;

    public AdapterDrawerListView(Context mContext, int layoutResourceId, ArrayList<ModelDrawerData> argItemsTeam) {

        super(mContext, layoutResourceId, argItemsTeam);
        this.layoutResourceId = layoutResourceId;
        this.context = mContext;
        this.drawerItems = argItemsTeam;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelDrawerData drawerItem = this.drawerItems.get(position);
        View rowView = convertView;
        RowViewHolderNormal rowViewHolderNormal;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.layout_drawer_item_row, parent, false);
            rowViewHolderNormal = new RowViewHolderNormal(rowView);
            rowView.setTag(rowViewHolderNormal);
        } else {
            rowViewHolderNormal = (RowViewHolderNormal) rowView.getTag();
        }
        rowViewHolderNormal.sysTextViewTitle.setText(drawerItem.getTitle());
        if (isClicked) {
            if (position == selectedPosition) {
                //layout.setBackgroundColor(mContext.getResources().getColor(R.color.app_bg));
                rowViewHolderNormal.idLineLayDrawer.setBackgroundColor(Color.parseColor("#ff0000"));
            } else {
                //layout.setBackgroundColor(mContext.getResources().getColor(R.color.normal_bg));
                rowViewHolderNormal.idLineLayDrawer.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        } else {
            if (position == 0) {
                //layout.setBackgroundColor(checkedColor);
            }
        }
        return rowView;
    }

    @Override
    public int getCount() {
        return this.drawerItems.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class RowViewHolderNormal {
        //ProgressBar sysProgBar;
        //ImageView sysImgProfThumb;
        RelativeLayout idLineLayDrawer;
        ImageView sysTextViewIcon;
        TextView sysTextViewTitle;
        public RowViewHolderNormal(View argView) {
            idLineLayDrawer = (RelativeLayout) argView.findViewById(R.id.idLineLayDrawer);
            sysTextViewIcon = (ImageView) argView.findViewById(R.id.sysTextViewIcon);
            sysTextViewTitle = (TextView) argView.findViewById(R.id.sysTextViewTitle);
        }
    }

    public void setSelectedPosition(int argPosition, boolean argIsClicked, int argCheckedColor) {
        this.selectedPosition = argPosition;
        this.isClicked = argIsClicked;
        this.checkedColor = argCheckedColor;
    }
}