package com.houston_inc.android.activity;

import android.content.Intent;
import android.os.Bundle;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.FragmentById;
import com.houston_inc.android.R;
import com.houston_inc.android.fragment.AccountDetailsFragment;
import com.houston_inc.android.fragment.AccountDetailsFragment_;

@EActivity(R.layout.transactions)
public class TransactionsActivity extends BaseActivity {

    public static String INTENT_EXTRA_ACCOUNT_INDEX = "accountIndex";


}
