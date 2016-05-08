package com.example.ruwa.mockservertest;

import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import com.example.ruwa.mockservertest.service.ServiceFactory;
import com.example.ruwa.mockservertest.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MainActivityTest {

    MockWebServer mockWebServer;

    @Rule public MockWebServiceActivityTestRule<MainActivity> mainActivityActivityTestRule = new
            MockWebServiceActivityTestRule<MainActivity>(MainActivity.class, new MockWebServiceActivityTestRule.OnInitListener() {
        @Override
        public void onInit(String mockServerUrl) {
            ServiceFactory.API_BASE_URL = mockServerUrl;
        }
    });

    @Before public void setup() throws IOException {
        mockWebServer = mainActivityActivityTestRule.getMockWebServer();
    }

    @Test public void get200Shows200() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"response\": \"success\", \"status\": \"200\"}"));
        onView(withId(R.id.btnGet)).perform(click());
        onView(withId(R.id.txtResponse)).check(matches(withText("success")));
    }

    @Test public void get201Shows201() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(201)
                .setBody("{\"response\": \"moved_perm\", \"status\": \"201\"}"));
        onView(withId(R.id.btnGet)).perform(click());
        onView(withId(R.id.txtResponse)).check(matches(withText("moved_perm")));
    }

    @Test public void get200Shows2002() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"response\": \"success\", \"status\": \"200\"}"));
        onView(withId(R.id.btnGet)).perform(click());
        onView(withId(R.id.txtResponse)).check(matches(withText("success")));
    }

    @Test public void get302Shows302() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(302)
                .setBody("{\"response\": \"moved_perm\", \"status\": \"302\"}"));
        onView(withId(R.id.btnGet)).perform(click());
        onView(withId(R.id.txtResponse)).check(matches(withText("moved_perm")));
    }


}
