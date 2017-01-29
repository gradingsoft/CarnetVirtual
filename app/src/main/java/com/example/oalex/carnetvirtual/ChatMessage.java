package com.example.oalex.carnetvirtual;

import java.util.Date;

/**
 * Created by oalex on 2017-01-29.
 */

public class ChatMessage
{
    public Date date;
    public Date expirationDate;
    public String mesaj;
    public String autor;
    public int type;

    public ChatMessage(Date date, Date expirationDate, String mesaj, String autor, int type)
    {
        this.date = date;
        this.expirationDate = expirationDate;
        this.mesaj = mesaj;
        this.autor = autor;
        this.type = type;

        Student.student.chatMessages.add(this);
    }
}
