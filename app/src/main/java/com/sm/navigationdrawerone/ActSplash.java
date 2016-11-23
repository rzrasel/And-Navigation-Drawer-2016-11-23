package com.sm.navigationdrawerone;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ActSplash extends AppCompatActivity {
    //|------------------------------------------------------------|
    private Activity activity;
    private Context context;
    //|------------------------------------------------------------|
    //|------------------------------------------------------------|
    private Toolbar sysToolBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout sysDrawerLayout;
    private RelativeLayout sysIdDrawerContainer;
    private ListView sysDrawerList;
    //|------------------------------------------------------------|
    private AdapterDrawerListView adapterLstDrawer;
    private ArrayList<ModelDrawerData> modelDrawerItems = new ArrayList<ModelDrawerData>();
    //|------------------------------------------------------------|
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //|------------------------------------------------------------|
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
        //|------------------------------------------------------------|
        activity = this;
        context = this;
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
        sysToolBar = (Toolbar) findViewById(R.id.sysToolBar);
        //sysToolBar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(sysToolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        //sysIdDrawerContainer = (RelativeLayout) findViewById(R.id.sysIdDrawerContainer);
        sysDrawerLayout = (DrawerLayout) findViewById(R.id.sysDrawerLayout);
        sysDrawerList = (ListView) findViewById(R.id.sysDrawerList);
        //|------------------------------------------------------------|
        modelDrawerItems.add(new ModelDrawerData(1, "Home"));
        modelDrawerItems.add(new ModelDrawerData(1, "Connect"));
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        adapterLstDrawer = new AdapterDrawerListView(this, 0, modelDrawerItems);
        sysDrawerList.setAdapter(adapterLstDrawer);
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, sysDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /* Called when drawer is closed */
            public void onDrawerClosed(View view) {
                //Put your code here
                invalidateOptionsMenu();
            }

            /* Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                //Put your code here
                invalidateOptionsMenu();
            }
        };
        //|------------------------------------------------------------|
        actionBarDrawerToggle.syncState();
        sysDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        //|------------------------------------------------------------|
        //|------------------------------------------------------------|
        sysDrawerList.setOnItemClickListener(new MenuListClickListener());
        //|------------------------------------------------------------|
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            DisplayView(0);
        }
        adapterLstDrawer.setSelectedPosition(0, true, 1);
        adapterLstDrawer.notifyDataSetChanged();
        //|------------------------------------------------------------|
    }
    //|------------------------------------------------------------|
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    //|------------------------------------------------------------|
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        //Toast.makeText(context, "Pressed", Toast.LENGTH_LONG).show();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    //|------------------------------------------------------------|
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    //|------------------------------------------------------------|
    private class MenuListClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            //view.setSelected(true);
            /*for (int i = 0; i < sysDrawerList.getAdapter().getCount(); i++) {
                sysDrawerList.getChildAt(i).setBackgroundColor(Color.BLUE);
            }*/
            /*parent.getChildAt(position).setBackgroundColor(Color.YELLOW);
            LinearLayout idLineLayListView = (LinearLayout) view.findViewById(R.id.idLineLayListView);
            idLineLayListView.setBackgroundColor(Color.YELLOW);
            TextView sysTextViewTitle = (TextView) view.findViewById(R.id.sysTextViewTitle);
            TextView sysTextViewTitleTemp = (TextView) parent.findViewById(R.id.sysTextViewTitle);
            sysTextViewTitle.setText("Checked");
            sysTextViewTitleTemp.setText("CheckedT");
            Toast.makeText(context, "Hi: " + sysTextViewTitleTemp.getText(), Toast.LENGTH_LONG).show();*/
            //view.setBackgroundColor(Color.YELLOW);
            DisplayView(position);
            adapterLstDrawer.setSelectedPosition(position - 0, true, 1);
            adapterLstDrawer.notifyDataSetChanged();
        }
    }

    //|------------------------------------------------------------|
    private void DisplayView(int position) {
        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentEventListener;
        ModelDrawerData drawerItem = this.modelDrawerItems.get(position);
        String toolbarTitle = "CMDSS";
        Fragment fragment = null;
        Bundle bundle = null;
        switch (position) {
            case 0:
                fragment = new Home();
                bundle = new Bundle();
                String myMessage = "Stackoverflow is cool!";
                bundle.putString("message", myMessage);
                break;
            case 1:
                fragment = new Connect();
                break;
        }
        if (fragment != null) {
            //FragmentManager fragmentManager = getFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.frame_container, Fragment.instantiate(ActSplash.this, fragment));
            if (bundle == null) {
                bundle = new Bundle();
            }
            //bundle.putSerializable(APPConstants.SESSION.KEY, userSession);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.sysFrameContainer, fragment);
            fragmentTransaction.commit();
        }
        /*
        //LogWriter.Log("CHECK:- " + drawerItem.getTypeOfMenu().toString() + " - " + position + " - " + drawerItem.getTypeOfMenu().getMenuType());
        if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.DASHBOARD) {
            fragment = new FragHomeAgent();
            *//*bundle = new Bundle();
            String myMessage = "Stackoverflow is cool!";
            bundle.putString("message", myMessage);*//*
            if (userSession.getUserType() == SysUserType.TYPE.AGENT)
                fragment = new FragHomeAgent();
            else if (userSession.getUserType() == SysUserType.TYPE.DEALER)
                fragment = new FragHomeDealer();
            else if (userSession.getUserType() == SysUserType.TYPE.DISTRIBUTOR)
                fragment = new FragHomeDistributor();
            else if (userSession.getUserType() == SysUserType.TYPE.BANK)
                fragment = new FragHomeBank();
            else
                fragment = new FragTest();
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.GOOGLE_MAP) {
            fragment = new FragGoogleMap();
            toolbarTitle = "Map";
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.VIDEO_TUTORIAL) {
            fragment = new FragYoutubeTutorial();
            //toolbarTitle = "Tutorial";
            toolbarTitle = SetUserTheme.GetString(context, R.string.str_tutorial);
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.REPORT) {
            fragment = new FragReport();
            //toolbarTitle = "Report";
            toolbarTitle = SetUserTheme.GetString(context, R.string.str_report);
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.MESSAGE_SEND) {
            fragment = new FragMessage();
            //toolbarTitle = "Message";
            toolbarTitle = SetUserTheme.GetString(context, R.string.str_message);
        } *//*else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.MESSAGE_READ) {
            fragment = new FragReceiveMessage();
        }*//* else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.SEND_PICTURE) {
            fragment = new FragSendPicture();
            //toolbarTitle = "Message";
            toolbarTitle = SetUserTheme.GetString(context, R.string.str_send_picture);
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.SETTINGS) {
            fragment = new FragSettings();
            //toolbarTitle = "Message";
            toolbarTitle = SetUserTheme.GetString(context, R.string.str_settings);
        } else if (drawerItem.getTypeOfMenu() == SysTypesOfMenu.MENU.LOGOUT) {
            //sharePrefHandler.clearAll();
            sharePrefHandler.addData(APPConstants.SHARED_PREF_KEY.USER_IS_LOGIN, "0");
            Intent intent = new Intent(getApplicationContext(), ActLogin.class);
            startActivity(intent);
            finish();
            return;
        }
        if (fragment != null) {
            //FragmentManager fragmentManager = getFragmentManager();
            //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.replace(R.id.frame_container, Fragment.instantiate(ActSplash.this, fragment));
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSerializable(APPConstants.SESSION.KEY, userSession);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.sysFrameContainer, fragment);
            fragmentTransaction.commit();

            // update selected item and title, then close the drawer
            sysDrawerList.setItemChecked(position, true);
            sysDrawerList.setSelection(position);
            setTitle(toolbarTitle);
        } else {
            // error in creating fragment
            Log.e("Dashboard", "Error in creating fragment");
        }*/
        sysDrawerList.setItemChecked(position, true);
        sysDrawerLayout.closeDrawer(sysDrawerList);
    }

    //|------------------------------------------------------------|
}