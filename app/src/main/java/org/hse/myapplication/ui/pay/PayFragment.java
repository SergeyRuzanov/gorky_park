package org.hse.myapplication.ui.pay;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.hse.myapplication.databinding.FragmentPayBinding;


public class PayFragment extends Fragment {

    private PayViewModel payViewModel;
    private FragmentPayBinding binding;
    private WebView webView;
    private ProgressBar progress;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        payViewModel =
                new ViewModelProvider(this).get(PayViewModel.class);

        binding = FragmentPayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        payViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        progress = binding.progress;
        webView = binding.webViewPay;
        webView.getSettings().setJavaScriptEnabled(true);
        progress.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                return false;
            }
            @Override
            public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
                progress.setVisibility(View.GONE);
                super.onPageStarted(view, url, favicon);
            }
            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
            }
        });


        webView.loadUrl("https://payframe.ckassa.ru/?service=111-16503-1");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}