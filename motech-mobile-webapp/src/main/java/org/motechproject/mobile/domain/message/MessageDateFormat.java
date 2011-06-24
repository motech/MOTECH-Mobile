package org.motechproject.mobile.domain.message;

import java.text.SimpleDateFormat;

public class MessageDateFormat extends SimpleDateFormat{

    private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public MessageDateFormat(){
        super(DATE_FORMAT);
    }
}
