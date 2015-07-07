package com.training.android.socialite.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.training.android.socialite.R;
import com.training.android.socialite.ui.models.NavigationDrawerMenu;

import java.util.List;

/**
 * Created by chrisenabled on 7/7/15.
 */
public class NavigationDrawerListViewAdapter extends ArrayAdapter<NavigationDrawerMenu> {

    private final Context mContext;
    private final List<NavigationDrawerMenu> mNavigationDrawerMenuList;

    public NavigationDrawerListViewAdapter(Context context, List<NavigationDrawerMenu> navigationDrawerMenus) {
        super(context, -1, navigationDrawerMenus);
        mContext = context;
        mNavigationDrawerMenuList = navigationDrawerMenus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.navigation_drawer_list, parent, false);

        final LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.drawerListLinearLayout);

        TextView textView = (TextView) rowView.findViewById(R.id.drawerListViewText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.drawerListViewImage);

        textView.setText(mNavigationDrawerMenuList.get(position).mText);
        imageView.setImageResource(mNavigationDrawerMenuList.get(position).mIcon);

        return rowView;
    }
}
