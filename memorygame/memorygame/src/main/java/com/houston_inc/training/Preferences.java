package com.houston_inc.training;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultInt;
import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultString;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface Preferences {

    @DefaultInt(0)
    int highScore();

    long lastUpdated();

}
