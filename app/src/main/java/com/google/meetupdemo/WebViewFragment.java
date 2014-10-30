package com.google.meetupdemo;


public class WebViewFragment extends android.webkit.WebViewFragment {

    public WebViewFragment() {}

    private final static String URL = "http://www.android.com/versions/lollipop-5-0/";

    @Override
    public void onResume() {
        super.onResume();
        getWebView().loadUrl(URL);
    }
}
