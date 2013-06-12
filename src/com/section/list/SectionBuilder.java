package com.section.list;

import java.util.ArrayList;

import com.section.list.SectionListAdaptor.Section;
import com.section.list.SectionListAdaptor.Subsection;

import android.content.Context;
import android.content.res.Resources;

public class SectionBuilder {

	private static final int[][] ALLTIME_SECTION_DATA = {
		{R.drawable.shawshank, R.string.the_shawshank_redemption},
		{R.drawable.godfather, R.string.the_godfather},
		{R.drawable.pulp_fiction, R.string.pulp_fiction},
		{R.drawable.good_bad_ugly, R.string.good_bad_and_ugly},
		{R.drawable.darknight, R.string.the_dark_night},
	};
	
	private static final int[][] ACTION_SECTION_DATA = {
		{R.drawable.fast_and_furious, R.string.fast_and_furious},
		{R.drawable.hansel_and_gretel, R.string.hansel_and_gretel},
		{R.drawable.ironman_three, R.string.ironman},
		{R.drawable.man_of_steal, R.string.man_of_steal},
		{R.drawable.startrek, R.string.star_trek},
	};
	
	private static final int[][] ANIMATION_SECTION_DATA = {
		{R.drawable.epic, R.string.epic},
		{R.drawable.guardians, R.string.rise_of_the_guardians},
		{R.drawable.ralph, R.string.wrech_it_ralph},
		{R.drawable.croods, R.string.the_croods},
		{R.drawable.escape_from_earth, R.string.escape_from_planet_earth},
	};
	
	private static final int[][] COMEDY_SECTION_DATA = {
		{R.drawable.hangover, R.string.hangover},
		{R.drawable.warm_bodies, R.string.warm_bodies},
		{R.drawable.identity_theft, R.string.identity_theft},
		{R.drawable.millers, R.string.were_the_millers},
		{R.drawable.pain_and_gain, R.string.pain_gain},
	};
	
	private static final int[][] DRAMA_SECTION_DATA = {
		{R.drawable.gangster_squad, R.string.gangster_squad},
		{R.drawable.gatsby, R.string.the_great_gatsby},
		{R.drawable.side_effects, R.string.side_effects},
		{R.drawable.silver_linings, R.string.silver_linings},
		{R.drawable.snitch, R.string.snitch},
	};
	
	private static final int[][] HORROR_SECTION_DATA = {
		{R.drawable.dark_skys, R.string.dark_skys},
		{R.drawable.mama, R.string.mama},
		{R.drawable.purge, R.string.the_purge},
		{R.drawable.rather, R.string.would_you_rather},
		{R.drawable.world_war_z, R.string.world_war_z},
	};
	
	private static final int[][] MISTERY_SECTION_DATA = {
		{R.drawable.cabin, R.string.the_cabin_in_the_woods},
		{R.drawable.inception, R.string.inception},
		{R.drawable.prometheus, R.string.prometheus},
		{R.drawable.safe_haven, R.string.safe_haven},
		{R.drawable.seven, R.string.seven},
	};	
	
	Resources mResources;
	
	public SectionBuilder(Context context){
		mResources = context.getResources();
	}
	
	public ArrayList<Section> getSections() {
		ArrayList<Section> sections = new ArrayList<Section>();
		
		int largeTileCnt = mResources.getInteger(R.integer.large_sublist);
		int mediumTileCnt = mResources.getInteger(R.integer.medium_sublist);
					
		Section alltime_section = new Section(
				/* Title */ mResources.getString(R.string.alltime),
				/* Image */ null,
				/* Layout*/ R.layout.section_large_tile, 
				/* Tiles */ getSubs(ALLTIME_SECTION_DATA, R.layout.subitem_large,largeTileCnt));
		
		Section action_section = new Section(
				mResources.getString(R.string.action),
				null,
				R.layout.section_large_tile, 
				getSubs(ACTION_SECTION_DATA, R.layout.subitem_large, largeTileCnt));
		
		Section animation_section = new Section(
				mResources.getString(R.string.animation),
				null,
				R.layout.section_medium_tile, 
				getSubs(ANIMATION_SECTION_DATA, R.layout.subitem_medium, mediumTileCnt));
		
		Section comedy_section = new Section(
				mResources.getString(R.string.comedy),
				null,
				R.layout.section_medium_tile, 
				getSubs(COMEDY_SECTION_DATA, R.layout.subitem_medium, mediumTileCnt));
		
		Section drama_section = new Section(
				mResources.getString(R.string.drama),
				null,
				R.layout.section_medium_tile, 
				getSubs(DRAMA_SECTION_DATA, R.layout.subitem_medium, mediumTileCnt));	
		
		Section horror_section = new Section(
				mResources.getString(R.string.horror),
				null,
				R.layout.section_medium_tile, 
				getSubs(HORROR_SECTION_DATA, R.layout.subitem_medium, mediumTileCnt));	

		Section myster_section = new Section(
				mResources.getString(R.string.mystery),
				null,
				R.layout.section_medium_tile, 
				getSubs(MISTERY_SECTION_DATA, R.layout.subitem_medium, mediumTileCnt));	
		
		Section advert_section = new Section(
				mResources.getString(R.string.advert),
				mResources.getDrawable(R.drawable.icon),
				R.layout.section_small_tile, 
				new ArrayList<Subsection>());//TODO remove	
		
		sections.add(advert_section);	
		sections.add(alltime_section);		
		sections.add(animation_section);
		sections.add(comedy_section);
		sections.add(action_section);
		sections.add(drama_section);
		sections.add(advert_section);	
		sections.add(horror_section);
		sections.add(myster_section);
		sections.add(advert_section);	
		
		return sections;		
	}
	
	private ArrayList<Subsection> getSubs(int[][] ids, int layoutId, int count){		
		ArrayList<Subsection> subs = new ArrayList<Subsection>();
		for (int i = 0; i < count; i++)
			subs.add(new Subsection(
					/* Title */ mResources.getString(ids[i][1]), 
					/* Image */ mResources.getDrawable(ids[i][0]), 
					/* Layout*/ layoutId));
		
		return subs;
	}
	
}
