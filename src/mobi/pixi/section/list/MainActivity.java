package mobi.pixi.section.list;

import java.util.ArrayList;

import mobi.pixi.section.list.SectionListAdaptor.OnSubSectionItemClickedListener;
import mobi.pixi.section.list.SectionListAdaptor.OnTitlebarClickedListener;
import mobi.pixi.section.list.SectionListAdaptor.Section;
import mobi.pixi.section.list.SectionListAdaptor.Subsection;

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
		new LoadTask().execute(0);
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

	private class LoadTask extends AsyncTask<Integer, Void, String> {
		@Override
		protected String doInBackground(Integer... flags) {
			mSections = new ArrayList<Section>();
			
			ArrayList<Subsection> subs = new ArrayList<Subsection>();
			subs.add(new Subsection("Title Test","SubTitle Test", 
					R.layout.subitem));
			subs.add(new Subsection("Title Test","SubTitle Test", 
					R.layout.subitem));
			subs.add(new Subsection("Title Test","SubTitle Test", 
					R.layout.subitem));
			subs.add(new Subsection("Title Test","SubTitle Test", 
					R.layout.subitem));
			subs.add(new Subsection("Title Test","SubTitle Test", 
					R.layout.subitem));
			
			ArrayList<Subsection> subMedsections = new ArrayList<Subsection>();
			ArrayList<Subsection> subLarsections = new ArrayList<Subsection>();
			
			int lar = mContext.getResources().getInteger(R.integer.large_sublist);
			int med = mContext.getResources().getInteger(R.integer.medium_sublist);
			
			
			for (int i = 0; i < lar; i++ )
				subLarsections.add(subs.get(i));
			
			for (int i = 0; i < med; i++ )
				subMedsections.add(subs.get(i));			
			
			Section section1 = new Section("Title Test","SubTitle Test", 
					R.layout.section_medium_tile, subMedsections);
			
			Section section2 = new Section("Title Test","SubTitle Test", 
					R.layout.section_large_tile, subLarsections);
			
			Section section3 = new Section("Title Test","SubTitle Test", 
					R.layout.section_small_tile, new ArrayList<Subsection>());
			
			mSections.add(section3);
			mSections.add(section1);
			mSections.add(section2);
			mSections.add(section3);
			mSections.add(section3);
			mSections.add(section1);
			mSections.add(section2);
			mSections.add(section3);
			mSections.add(section1);
			mSections.add(section1);
			mSections.add(section2);
			mSections.add(section1);
			
			
			return null;
		}
		
		@Override
	    protected void onPostExecute(String result) {			
			mSectionListAdaptor.setSections(mSections);
		}
	}

}
