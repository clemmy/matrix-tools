package com.math.tools.matrixtools;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {

	private DrawerLayout drawerLayout;
	private ListView drawerList;
	private ActionBarDrawerToggle drawerToggle;

	private CharSequence opTitle;

	private String[] navMenuTitles;
	private ListView opsDrawerListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 * creating references to XML objects
		 */
		opTitle = getTitle();
		//gets list items from strings.xml
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		//gets ListView defined in activity_main.xml
		opsDrawerListView = (ListView) findViewById(R.id.ops_drawer);

		drawerList.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				displayView(position);
			}
		});

		//sets the nav drawer list adapter
		ArrayAdapter<String> operationsAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, navMenuTitles);
		opsDrawerListView.setAdapter(operationsAdapter);

		//enables action bar app icon and sets it to behave as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, //nav menu toggle icon
				R.string.app_name, //nav drawer open - description for accessibility
				R.string.app_name //nav drawer close - description for accessibility
				) {
			
			@Override
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(opTitle);
				//calls onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}
			
			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(opTitle);
				//calls onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);

		//displays home view on first run
		if (savedInstanceState == null) {
			displayView(0);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//toggles nav drawer on selecting action bar app icon/title
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		//handles action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true; //TODO: Implement some settings
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * NOTE: This method is called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		//TODO: Figure out why the above code works when drawerLayout.isDrawerOpen(drawerList) returns false
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Displays a fragment view for selected matrix operation
	 **/
	private void displayView(int position) {

		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new AdditionFragment1();
			break;
		case 2:
			fragment = new SubtractionFragment1();
			break;
		case 3:
			fragment = new MMultiplicationFragment1();
			break;
		case 4:
			fragment = new SMultiplicationFragment1();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
			.replace(R.id.content_frame, fragment).commit();

			//updates the selected item and title, then close the drawer
			drawerList.setItemChecked(position, true);
			drawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			drawerLayout.closeDrawer(drawerList);
		} else {
			//error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}
	
	@Override
	public void setTitle(CharSequence title) {
		opTitle = title;
		getActionBar().setTitle(opTitle);
	}

	/**
	 * Use ActionBarDrawerToggle in the following methods only:
	 * onPostCreate(Bundle)
	 * onCongigurationChanged(Congiguration)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		//syncs the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//passes any configuration change to the drawer toggle
		drawerToggle.onConfigurationChanged(newConfig);
	}


}
