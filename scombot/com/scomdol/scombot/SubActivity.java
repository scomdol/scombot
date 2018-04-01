package com.scomdol.scombot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import com.scomdol.scombot.Scombot;

public class SubActivity extends AppCompatActivity {
   WebView web;

   public void onBackPressed() {
      if(this.web.canGoBack()) {
         this.web.goBack();
      } else {
         this.finish();
      }
   }

   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      Intent var2 = this.getIntent();
      String var3 = var2.getStringExtra("data");
      int var4 = var2.getIntExtra("type", 0);
      LinearLayout var5 = new LinearLayout(this);
      var5.setOrientation(1);
      this.web = new WebView(this);
      WebSettings var6 = this.web.getSettings();
      var6.setJavaScriptEnabled(scombot.readData("useJs").equals("true"));
      var6.setBuiltInZoomControls(scombot.readData("zoomIn").equals("true"));
 X      this.web.setWebViewClient(new WebViewClient());
      if(var4 == 1) {
         this.web.loadUrl("http://search.daum.net/search?nil_profile=simpleurl&w=tot&q=" + var3);
      } else if(var4 == 2) {
         this.web.loadUrl("https://www.google.co.kr/#q=" + var3);
      } else {
         this.web.loadUrl("https://m.search.naver.com/search.naver?query=" + var3);
      }

      var5.addView(this.web);
      this.setContentView(var5);
   }
}
