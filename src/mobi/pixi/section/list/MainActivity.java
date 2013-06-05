package mobi.pixi.section.list;

import java.util.ArrayList;

import mobi.pixi.section.list.SectionListAdaptor.OnSubSectionItemClickedListener;
import mobi.pixi.section.list.SectionListAdaptor.OnTitlebarClickedListener;
import mobi.pixi.section.list.SectionListAdaptor.Section;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ListActivity implements 
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
		new LoadTask().execute();
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

	private class LoadTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... flags) {
			mSections = new SectionBuilder(mContext).getSections();
			return null;
		}
		
		@Override
	    protected void onPostExecute(Void nothing) {			
			mSectionListAdaptor.setSections(mSections);
		}
	}	

}
