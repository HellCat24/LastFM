package com.fancy.lastfm.arist;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fancy.lastfm.MyViewAction;
import com.fancy.lastfm.R;
import com.fancy.lastfm.artist.list.TopArtistActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Oleg Mazhukin
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TopArtistActivityTest {

  /*  @Rule
    public IntentsTestRule<ArtistDetailActivity> mAddNoteIntentsTestRule = new IntentsTestRule<>(ArtistDetailActivity.class);*/

    @Rule
    public ActivityTestRule<TopArtistActivity> mNoteDetailActivityTestRule = new ActivityTestRule<>(TopArtistActivity.class, true, false);

    @Test
    public void testTitle() throws Exception {
        //assertThat(mNoteDetailActivityTestRule.getActivity().getTitle(), new Ma);
    }

    @Test
    public void clickArtist_opensDetails() throws Exception {

        //onView(withId(R.id.top_album_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new MyViewAction().clickChildViewWithId(R.id.artist_holder)));
        //onView(withId(R.id.add_note_title)).check(matches(isDisplayed()));
    }
}
