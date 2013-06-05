package mobi.pixi.section.list;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class SectionListAdaptor extends BaseAdapter {
	
	private ArrayList<Section> mSections; 
	private Context mContext; 
	private LayoutInflater mLayoutInflater;
	private OnTitlebarClickedListener mOnTitlebarClickedListener;
	private OnSubSectionItemClickedListener mOnSubSectionItemClickedListener;
	
	/**
     * Interface definition for a callback to be invoked when
     * an titlebar has been clicked.
     */
    public interface OnTitlebarClickedListener {
        public void onClicked(int index);
    }
    
	/**
     * Interface definition for a callback to be invoked when
     * an titlebar has been clicked.
     */
    public interface OnSubSectionItemClickedListener {
        public void onClicked(int index, int subindex);
    }
    
    public static class Subsection {
    	String title;
    	int layoutRes;
    	Drawable drawable;
    	
    	public Subsection(String title, Drawable drawableRes, int layoutRes){
    		this.title = title;
    		this.layoutRes = layoutRes;
    		this.drawable = drawableRes;
    	}
    	
    }
    
    public static class Section extends Subsection {
    	ArrayList<Subsection> subsections;
    	
		public Section(String title, Drawable drawableRes, 
				int layoutRes, ArrayList<Subsection> subsections) {
			super(title, drawableRes, layoutRes);
			this.subsections = subsections;
		}
    	
    }
    
    public SectionListAdaptor(Context context, 
    		OnTitlebarClickedListener onTitlebarClickedListener,
    		OnSubSectionItemClickedListener onSubSectionItemClickedListener) {
		mLayoutInflater = (LayoutInflater) 
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = context;
		mSections = new ArrayList<Section>();
		mOnTitlebarClickedListener = onTitlebarClickedListener;
		mOnSubSectionItemClickedListener = onSubSectionItemClickedListener;
    }
    
    public void setSections(ArrayList<Section> sections) {
		mSections = sections;
        notifyDataSetChanged();
    }
	
	@Override
	public int getCount() {
		return mSections.size();
	}

	@Override
	public Object getItem(int pos) {
		return mSections.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}
	
	@Override
	public View getView(int pos, View view, ViewGroup parent) {
		final int position = pos;
		view = mLayoutInflater.inflate(mSections.get(position).layoutRes, parent, false);
		
		ViewGroup titlebar = (ViewGroup) view.findViewById(R.id.titlebar);
		TextView title = (TextView) titlebar.findViewById(R.id.title);
		ImageView image = (ImageView) titlebar.findViewById(R.id.image);
		
		title.setText(mSections.get(position).title);

		Drawable imageDrable = mSections.get(position).drawable;
		if (image != null)
			image.setImageDrawable(imageDrable);
		
		//ExpandableHeightGridView list = 
		//		(ExpandableHeightGridView) view.findViewById(android.R.id.list);
		//list.setExpanded(true);	
		GridView list = 
				(GridView) view.findViewById(android.R.id.list);
		list.setAdapter(new SublistAdapter(mContext, 0, 
				mSections.get(position).subsections));
		
		list.setOnItemClickListener(new OnItemClickListener () {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long id) {
				mOnSubSectionItemClickedListener.onClicked(position, index);					
			}
			
		});
		
		titlebar.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mOnTitlebarClickedListener.onClicked(position);				
			}
			
		});	
		
		return view;
	}    
		
	private class SublistAdapter extends ArrayAdapter<Subsection> {
		List<Subsection> mSubsections;
		
		public SublistAdapter(Context context, int textViewResourceId,
				List<Subsection> subsections) {
			super(context, textViewResourceId, subsections);
			mSubsections = subsections;
		}	
		
		@Override
		public View getView(int pos, View view, ViewGroup parent) {
			final int position = pos;
			view = mLayoutInflater.inflate(mSubsections.get(position).layoutRes, parent, false);
				
			TextView title = (TextView) view.findViewById(R.id.title);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			
			title.setText(mSubsections.get(position).title);
			image.setImageDrawable(mSubsections.get(position).drawable);
			
			return view;
			
		}
		
	}
    
}
