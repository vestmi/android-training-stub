package com.houston_inc.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.*;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;
import com.houston_inc.android.R;
import com.houston_inc.android.activity.TransactionsActivity;
import com.houston_inc.android.activity.TransactionsActivity_;
import com.houston_inc.android.app.MainApplication;
import com.houston_inc.domain.Account;
import com.houston_inc.service.AccountService;
import dagger.ObjectGraph;

import javax.inject.Inject;
import java.util.List;

@EFragment
public class AccountsListFragment extends ListFragment {

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        constructDependencyGraph();
    }

    /**
     * Constructs dependency injection graph for Dagger.
     */
    private void constructDependencyGraph() {
        MainApplication app = MainApplication.getApplication();
        ObjectGraph graph = app.getObjectGraph();
        graph.inject(this);
    }


}
