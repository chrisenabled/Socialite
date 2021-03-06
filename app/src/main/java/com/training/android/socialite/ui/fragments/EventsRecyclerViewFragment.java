package com.training.android.socialite.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.adapters.EventsRecyclerViewAdapter;
import com.training.android.socialite.ui.lastFmUtility.LastFmArtistUtility;
import com.training.android.socialite.ui.models.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsRecyclerViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsRecyclerViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsRecyclerViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private static Context mContext;
    private View rootView;
    private RecyclerView mEventsRV;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventsRecyclerViewAdapter mEventsRecyclerViewAdapter;
    private TextView empty;
    private ProgressBar eventsProgressBar;
    static List<Event> mEvents = new ArrayList<>();
    private String mArtistMbid;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment EventsRecyclerViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsRecyclerViewFragment newInstance(Context context) {
        EventsRecyclerViewFragment fragment = new EventsRecyclerViewFragment();
        mContext = context;
        return fragment;
    }

    public EventsRecyclerViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_events_recycler_view, container, false);
        empty = (TextView) rootView.findViewById(R.id.empty);
        eventsProgressBar = (ProgressBar) rootView.findViewById(R.id.eventsProgressBar);
        eventsProgressBar.setVisibility(View.INVISIBLE);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mEventsRV = (RecyclerView) rootView.findViewById(R.id.eventsRV);
        mEventsRV.setLayoutManager(mLayoutManager);
        mEventsRecyclerViewAdapter = new EventsRecyclerViewAdapter(mContext);
        mEventsRV.setAdapter(mEventsRecyclerViewAdapter);
        mArtistMbid = "69989475-2971-49aa-8c53-5d74af88b8be";
        new GetEventsAsync().execute();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    public class GetEventsAsync extends AsyncTask<String, Void, ArrayList<Event>> {


        @Override
        protected ArrayList<Event> doInBackground(String... params) {

            return (ArrayList<Event>) getEventsOnline(mArtistMbid, mContext);

        }

        @Override
        protected void onPostExecute(ArrayList<Event> events) {

            empty.setVisibility(View.INVISIBLE);
            mEvents = new ArrayList<>();
            for(Event event : events){
                mEvents.add(event);
                mEventsRecyclerViewAdapter.add(mEvents.size() - 1, event);
            }

        }
    }

    public List<Event> getEventsOnline(String artistMbid, Context mContext){

        JSONObject artistEvents;
        JSONObject topLevelEvents;
        JSONArray eventsJson;
        List<Event> events = new ArrayList<>();


        try {
            artistEvents = new LastFmArtistUtility(mContext)
                    .getArtistEvents(artistMbid);
            topLevelEvents = artistEvents.getJSONObject("events");
            eventsJson = topLevelEvents.optJSONArray("event");
            for(int i = 0; i < eventsJson.length(); i++){
                Event event;
                JSONObject eventObj = eventsJson.getJSONObject(i);
                event = new Event(eventObj);
                events.add(event);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return events;

    }


}
