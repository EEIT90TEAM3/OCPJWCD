package com.verygoodbook.entity;

import com.verygoodbook.exception.VGBException;
import com.verygoodbook.utils.VeryGoodBookUitilities;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Customer implements Comparable {
    public static final char MALE = 'M';
    public static final char FEMALE = 'F';
    
    /**
     * 身分證字號, PKey
     */
    private String id;
    
    /**
     * 姓名
     */    
    private String name;
    private String password;
    private String email;
    private Gender gender;

    private Date birthday;
    private String phone;
    private String address;
    //private char gender;//MALE,FEMALE
    private boolean married;

    public Customer() {
    }

    public Customer(String id, String password, String name) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Customer(String id, String password, String name, String email, char gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        setGender(gender);
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name 
                + ", password=" + password + ", email=" + email 
                + ", phone=" + phone + ", address=" + address + ", gender=" + gender + ", married=" + married + ", birthday=" + birthday + '}';
    }

    public boolean checkId(String id) {
        return VeryGoodBookUitilities.checkROCId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws VGBException {
        if(checkId(id)){
            this.id = id;
        }else{
            throw new VGBException("身分證號不正確!");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {        
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//    public Gender getGender() {
//        
//    }
    
    /**     
     * @return     
     */        
    public char getGender() {
        switch (this.gender) {
            case MALE:
                return MALE;
            case FEMALE:
                return FEMALE;
        }
        return MALE;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
    /**
     *  replaced by setGender(Gender.Male) or setGender(Gender.Female)
     * @param gender     
     */    
    public void setGender(char gender) {
        if(gender == FEMALE || gender == MALE){
            this.gender = gender == FEMALE ? Gender.FEMALE : Gender.MALE;
        }else{
            try {
                throw new VGBException("性別資料不正確");
            } catch (VGBException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean checkGender(char gender) {
        switch (gender) {
            case FEMALE:
            case MALE:
                return true;
            default:
                return false;
        }
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) throws VGBException {        
        if(birthday==null || (birthday!=null && birthday.before(new Date()))){
            this.birthday = birthday;
        }else{
            System.out.println(".....");
            throw new VGBException("客戶生日日期必須小於現在時間!");
        }    
    }

    public static final DateFormat webDateFormat; //blank static final attribute
    static {
        String dfPattern = System.getProperty("date-format");
        System.out.println("dfPattern = " + dfPattern);
        if(dfPattern!=null){        
            webDateFormat = new SimpleDateFormat(dfPattern);
        }else{
            webDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }
    }

    public void setBirthday(String bDay) throws VGBException{
        if (bDay != null && bDay.length() > 0) {
            try {
                bDay = bDay.replace('/', '-');
                Date d = webDateFormat.parse(bDay);
                this.birthday = d;
            } catch (ParseException ex) {
                Logger.getLogger(Customer.class.getName()).log(
                        Level.SEVERE,
                        "[server]客戶生日日期格式不正確!", ex);
                throw new VGBException("客戶生日日期格式不正確!");
            }
        } else {
            this.birthday = null;
        }
    }

    public void setBirthday(int year, int month, int day) {
        this.birthday = new GregorianCalendar(
                year, month - 1, day).getTime();
    }

    public int getAge() {
        Date now = new Date();
        if (this.getBirthday() != null) {
            if (now.after(birthday)) {
                long nowMiliSecs = now.getTime();
                long bDayMiliSecs = birthday.getTime();
                long diff = nowMiliSecs - bDayMiliSecs;
                Date age = new Date(diff);
                return age.getYear() + 1900 - 1970;
            }
        }
        return 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Customer)) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
       Customer other = (Customer)o;
       int rtn = this.id.compareTo(other.id);
       return rtn; 
    }

}
