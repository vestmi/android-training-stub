package com.houston_inc.service.impl;

import com.houston_inc.domain.SecurityKey;
import com.houston_inc.exception.InvalidCredentialsException;
import com.houston_inc.exception.InvalidSecurityKeyException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DummyLoginProviderTest {

    private DummyLoginProvider loginProvider;

    @Before
    public void setUp() throws Exception {
        loginProvider = new DummyLoginProvider();
        loginProvider.doNotSleep();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        loginProvider.login("test", "test", SecurityKey.valueOf("123456"));
        assertEquals("test", loginProvider.getLoggedInUser().getUsername());
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testLoginFailsWrongUsername() throws Exception {
        loginProvider.login("fail", "test", SecurityKey.valueOf("123456"));
    }

    @Test(expected = InvalidCredentialsException.class)
    public void testLoginFailsWrongPassword() throws Exception {
        loginProvider.login("test", "fail", SecurityKey.valueOf("123456"));
    }

    @Test(expected = InvalidSecurityKeyException.class)
    public void testLoginFailsWrongSecurityKey() throws Exception {
        loginProvider.login("test", "test", SecurityKey.valueOf("000000"));
    }

}
