package com.houston_inc.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.googlecode.androidannotations.annotations.*;
import com.houston_inc.android.R;
import com.houston_inc.android.fragment.LoadingDialogFragment;
import com.houston_inc.domain.SecurityKey;
import com.houston_inc.exception.InvalidCredentialsException;
import com.houston_inc.exception.InvalidSecurityKeyException;
import com.houston_inc.service.LoginProvider;

import javax.inject.Inject;

@EActivity(R.layout.login)
public class LoginActivity extends BaseActivity {


}
