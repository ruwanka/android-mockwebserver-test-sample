package com.example.ruwa.mockservertest;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import java.io.IOException;

import okhttp3.mockwebserver.MockWebServer;

/**
 * @author Ruwanka
 */
public class MockWebServiceActivityTestRule<A extends Activity> extends ActivityTestRule<A> {

    public interface OnInitListener{
        void onInit(String mockServerUrl);
    }

    MockWebServer mockWebServer;

    String mockWebServerHost;

    public MockWebServiceActivityTestRule(Class<A> activityClass, OnInitListener listener) {
        super(activityClass);
        initMockWebServer(listener);
    }

    public MockWebServiceActivityTestRule(Class<A> activityClass,
                                          boolean initialTouchMode,
                                          OnInitListener listener) {
        super(activityClass, initialTouchMode);
        initMockWebServer(listener);
    }

    public MockWebServiceActivityTestRule(Class<A> activityClass,
                                          boolean initialTouchMode,
                                          boolean launchActivity,
                                          OnInitListener listener) {
        super(activityClass, initialTouchMode, launchActivity);
        initMockWebServer(listener);
    }

    public String getMockWebServerHost() {
        return mockWebServerHost;
    }

    public MockWebServer getMockWebServer() {
        return mockWebServer;
    }

    private void initMockWebServer(OnInitListener listener) {
        mockWebServer = new MockWebServer();
        mockWebServerHost = mockWebServer.url("/").toString();
        //ServiceFactory.API_BASE_URL = mockWebServerHost;
        listener.onInit(mockWebServerHost);
    }

    @Override
    protected Intent getActivityIntent() {
        return super.getActivityIntent();
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        try {
            mockWebServer.shutdown();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
