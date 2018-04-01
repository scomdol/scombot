package com.scomdol.scombot;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;

public class scombot extends Application {
   public static final String VERSION = "1.0";
   private static Context ctx;
   private static String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();

   public static String getDataFromServer(String param0) {
   }

   public static String readData(InputStream param0) {
   }

   public static String readData(String var0) {
      try {
         String var2 = readFile(sdcard + "/scomdol/scombot/" + var0 + ".txt");
         return var2;
      } catch (Exception var3) {
         toast(var3.toString());
         return "";
      }
   }

   public static String readFile(String param0) {
   }

   public static void removeData(String var0) {
      try {
         File var1 = new File(sdcard + "/scomdol/scombot/" + var0 + ".txt");
         if(var1.exists()) {
            var1.delete();
         }

      } catch (Exception var3) {
         toast(var3.toString());
      }
   }

   public static void saveData(String var0, String var1) {
      try {
         FileOutputStream var2 = new FileOutputStream(new File(sdcard + "/scomdol/scombot/" + var0 + ".txt"));
         var2.write(var1.getBytes());
         var2.close();
      } catch (Exception var4) {
         toast(var4.toString());
      }
   }

   public static void say(TextToSpeech var0, String var1) {
      try {
         toast("[scombot] " + var1);
         var0.speak(var1, 0, (HashMap)null);
      } catch (Exception var3) {
         toast(var3.toString());
      }
   }

   public static void say(TextToSpeech var0, String var1, TextView var2) {
      try {
         var2.setText(var2.getText() + "[scombot] " + var1 + "\n");
         var0.speak(var1, 0, (HashMap)null);
      } catch (Exception var4) {
         toast(var4.toString());
      }
   }

   private static void toast(String var0) {
      Toast.makeText(ctx, var0, 1).show();
   }

   public void onCreate() {
      super.onCreate();
      ctx = this.getApplicationContext();
   }
}
