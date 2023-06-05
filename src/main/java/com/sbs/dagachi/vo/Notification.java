package com.sbs.dagachi.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private int notification_id;
    private String notification_body;
    private Date notification_date;
    private String notification_receiver;
    private String notification_url;
    private String notification_register;
    private String notification_delstatus;
}
