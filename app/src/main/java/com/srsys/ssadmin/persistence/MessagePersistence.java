package com.srsys.ssadmin.persistence;

import com.srsys.ssadmin.objects.Message;

import java.util.List;

public interface MessagePersistence
{
    List<Message> getMessages();

    void sendMessage(String timeStamp, String message, String from, String to);

    boolean deleteMessage(Message message);
}
