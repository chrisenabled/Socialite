package com.training.android.socialite.ui.customLayouts;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.training.android.socialite.R;

/**
 * Created by chrisenabled on 7/8/15.
 */
public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private CheckedTextView _checkbox;

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // find checked text view
        int childCount = getChildCount();
        for (int i = 0; i < childCount; ++i) {
            View v = getChildAt(i);
            if (v instanceof CheckedTextView) {
                _checkbox = (CheckedTextView)v;
            }
        }
    }

    @Override
    public boolean isChecked() {
        return _checkbox != null ? _checkbox.isChecked() : false;
    }

    @Override
    public void setChecked(boolean checked) {
        if (checked) {
            this.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDarkTransparent));
        }
    }

    @Override
    public void toggle() {
        if (_checkbox != null) {
            _checkbox.toggle();
        }
    }
}

