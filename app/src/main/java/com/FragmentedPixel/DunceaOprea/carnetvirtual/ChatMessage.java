package com.FragmentedPixel.DunceaOprea.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-29.
 */

class ChatMessage
{
    public Date date;
    public Date expirationDate;
    String message;
    String autor;
    int type;

    public ChatMessage(Date date, Date expirationDate, String message, String autor, int type)
    {
        this.date = date;
        this.expirationDate = expirationDate;
        this.message = message;
        this.autor = autor;
        this.type = type;

        Student.student.chatMessages.add(this);
    }
}
