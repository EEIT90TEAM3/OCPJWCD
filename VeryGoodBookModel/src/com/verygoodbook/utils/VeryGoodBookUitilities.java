/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.verygoodbook.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Administrator
 */
public class VeryGoodBookUitilities {

    private static final String NUMBERS = "0123456789";
    private static final String FIRSTCHAR_NUMBERS = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
    private static final String WEIGHTS = "19876543211";    
    
    public static final DateFormat webDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    public static final DateFormat webDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat webTimeFormat = new SimpleDateFormat("hh:mm");
    public static final NumberFormat webPriceFormat = new DecimalFormat("#####.##");
    

    public static boolean checkROCId(String id){
        //身分證字號檢核
        //https://zh.wikipedia.org/wiki/%E4%B8%AD%E8%8F%AF%E6%B0%91%E5%9C%8B%E5%9C%8B%E6%B0%91%E8%BA%AB%E5%88%86%E8%AD%89
        //格式檢查:長度，字首，其餘是數字
        if (!id.matches("[A-Z][12][0-9]{8}")) { //regular expression
//            System.out.println("System Log:長度為10，格式....");            
            return false;
        }
        id = id.toUpperCase();
        char firstChar = id.charAt(0);
        int firstCharInt = (int) firstChar;
        if (firstCharInt < 65 || firstCharInt > 90) {
//            System.out.println("System Log:字首要是英文字");
            return false;
//            throw new VGBException("System Log:字首要是英文字");
        }
        
//        for (int i = 1; i < id.length(); i++) {
//            char c = id.charAt(i);
//            if (NUMBERS.indexOf(c) == -1) {
//                System.out.println("System Log:格式....");
//                return false;
//                throw new VGBException("System Log:數字格式....");
//            }
//        }

        int firstCharNumber = FIRSTCHAR_NUMBERS.indexOf(firstChar) + 10;
        String checkIdStr = firstCharNumber + id.substring(1);
        int totalNumber = 0;
        for (int i = 0; i < checkIdStr.length(); i++) {
            int charNum = Integer.parseInt(checkIdStr.charAt(i) + "");
            int weight = Integer.parseInt(WEIGHTS.charAt(i) + "");
            totalNumber += charNum * weight;
        }
        if (totalNumber % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
