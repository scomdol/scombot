package com.scomdol.scombot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.Toast;
import com.darktornado.nusty.NustyService;

public class scombotReceiver extends BroadcastReceiver {
   String[] basicData = null;
   Context ctx;
   String[] customData = null;
   String[] runData = null;
   String[] runData2 = null;
   String[] searchData = null;

   private void loadLocalDatabase() {
      try {
         this.basicData = this.loadDatabaseFromAsset("basicData.txt").split("\n");
         this.searchData = this.loadDatabaseFromAsset("searchData.txt").split("\n");
         this.runData = this.loadDatabaseFromAsset("runData.txt").split("\n");
         this.toast("[scombot] ");
      } catch (Exception var2) {
         this.toast("[scombot] " + var2.toString());
      }

      this.startNustyService();
   }

   public int dip2px(Context var1, int var2) {
      return (int)Math.ceil((double)((float)var2 * var1.getResources().getDisplayMetrics().density));
   }

   public String getAllContact(Context var1, String var2) {
      try {
         Cursor var4 = var1.getContentResolver().query(Phone.CONTENT_URI, (String[])null, (String)null, (String[])null, (String)null);
         var4.moveToFirst();
         if(var4.getString(var4.getColumnIndex("data1")).equals(var2)) {
            return var4.getString(var4.getColumnIndex("display_name"));
         } else {
            do {
               if(!var4.moveToNext()) {
                  return null;
               }
            } while(!var4.getString(var4.getColumnIndex("data1")).equals(var2));

            String var6 = var4.getString(var4.getColumnIndex("display_name"));
            return var6;
         }
      } catch (Exception var7) {
         this.toast("getAllContact\n" + var7.toString());
         return null;
      }
   }

   public String loadDatabaseFromAsset(String param1) {
      // $FF: Couldn't be decompiled
   }

   public void onReceive(Context param1, Intent param2) {
      // $FF: Couldn't be decompiled
   }

   public void startscombotService() {
      try {
         Intent var1 = new Intent(this.ctx, NustyService.class);
         var1.putExtra("BasicData", this.basicData);
         var1.putExtra("CustomData", this.customData);
         var1.putExtra("SearchData", this.searchData);
         var1.putExtra("RunData", this.runData);
         var1.putExtra("RunData2", this.runData2);
         this.ctx.startService(var1);
         this.toast("[scombot] ");
      } catch (Exception var3) {
         this.toast("[scombot] " + var3);
      }
   }

   public void toast(Context var1, String var2) {
      Toast var3 = Toast.makeText(var1, var2, 0);
      var3.getView().setBackgroundColor(Color.argb(150, 0, 0, 0));
      int var4 = this.dip2px(var1, 5);
      var3.getView().setPadding(var4, var4, var4, var4);
      var3.show();
   }

   public void toast(String var1) {
      Toast var2 = Toast.makeText(this.ctx, var1, 0);
      var2.getView().setBackgroundColor(Color.argb(150, 0, 0, 0));
      int var3 = this.dip2px(this.ctx, 5);
      var2.getView().setPadding(var3, var3, var3, var3);
      var2.show();
   }
}
