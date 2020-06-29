package com.example.jpetstore.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jpetstore.domain.Item;
import com.example.jpetstore.service.PetStoreFacade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 * @modified-by Changsup Park
 */
@Controller
public class ViewItemController { 

   private PetStoreFacade petStore;

   @Autowired
   public void setPetStore(PetStoreFacade petStore) {
      this.petStore = petStore;
   }

   
     @RequestMapping("shop/getDeadline.do")
     @ResponseBody public JSONObject getDeadline(
     @RequestParam("itemId") String itemId) throws Exception {
      
  Item item = this.petStore.getItem(itemId); List<Item> items = new
     ArrayList<>(); items.add(item); System.out.println("deadLine?" +
     item.getClosingTime()); 
    
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String closeTime = formatter.format(item.getClosingTime());
     Date endDate = formatter.parse(closeTime);
     Date now = new Date();
     long mill = Math.abs(endDate.getTime() - now.getTime());
   // 시로 변환 ( millisecond -> hour 로 변환 ) 
      long hours = TimeUnit.MILLISECONDS.toHours(mill);
      // 일로 변환 ( hour -> day 로 변환 )
      long days = TimeUnit.HOURS.toDays(hours); 
      long mins =TimeUnit.MILLISECONDS.toMinutes(mill)
              - TimeUnit.HOURS.toMinutes(hours);
      long secs = TimeUnit.MILLISECONDS.toSeconds(mill)
              -  TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mill));
      long  newHour = days*24 - hours;
      String diffDays = String.format("%d days",  days);
      String diff = String.format("%d hour(s) %d min(s) %d secs", newHour
              , TimeUnit.MILLISECONDS.toMinutes(mill)
              - TimeUnit.HOURS.toMinutes(hours),secs);
      
      JSONObject deadLine = new JSONObject();
      deadLine.put("closingTime", diffDays + diff);
      System.out.println(diffDays + diff);
     return deadLine;
     
     }
    
   
     @RequestMapping("/shop/viewItem.do") 
     public String handleRequest(
     @RequestParam("itemId") String itemId, ModelMap model) throws Exception {
     
     Item item = this.petStore.getItem(itemId); //List<Item> items = new
     System.out.println("controller:is Auction?"
     + item); model.put("item", item); model.put("product", item.getProduct());
     return "Item";
     
     }
    

}