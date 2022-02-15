/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author hamza
 */
public class notification {
    String id_notification;
    int num_notificatione;
    String type_notification;
    String email_notification;

    public String getId_notification() {
        return id_notification;
    }

    public int getNum_notificatione() {
        return num_notificatione;
    }

    public void setNum_notificatione(int num_notificatione) {
        this.num_notificatione = num_notificatione;
    }

    public String getType_notification() {
        return type_notification;
    }

    public void setType_notification(String type_notification) {
        this.type_notification = type_notification;
    }

    public String getEmail_notification() {
        return email_notification;
    }

    public void setEmail_notification(String email_notification) {
        this.email_notification = email_notification;
    }

    public notification(int num_notificatione, String type_notification, String email_notification) {
        this.num_notificatione = num_notificatione;
        this.type_notification = type_notification;
        this.email_notification = email_notification;
    }
    
    
    
    
}
