package com.section.list;

import java.util.ArrayList;

import com.section.list.SectionListAdaptor.OnSubSectionItemClickedListener;
import com.section.list.SectionListAdaptor.OnTitlebarClickedListener;
import com.section.list.SectionListAdaptor.Section;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class SectionListActivity extends ListActivity implements 
		OnTitlebarClickedListener, OnSubSectionItemClickedListener {
	
	private ArrayList<Section> mSections; 
	private SectionListAdaptor mSectionListAdaptor;
	private Context mContext;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		mContext = this;
		mSectionListAdaptor = new SectionListAdaptor(this,this,this);
		getListView().setAdapter(mSectionListAdaptor);
		
		mSections = new SectionBuilder(mContext).getSections();
		mSectionListAdaptor.setSections(mSections);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onClicked(int index, int subindex) {
		Toast.makeText(this, "Index = " + index + " SubIndex = " + subindex, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClicked(int index) {
		Toast.makeText(this, "Title = " + index, Toast.LENGTH_SHORT).show();
		
	}

}
