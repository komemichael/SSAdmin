package com.srsys.ssadmin.logic;

import com.srsys.ssadmin.Application.Services;
import com.srsys.ssadmin.objects.Message;
import com.srsys.ssadmin.persistence.MessagePersistence;

import java.util.List;

public class AccessMessages
{
    private MessagePersistence data;
    private List<Message> messages;

    public AccessMessages()
    {
        data = Services.getMesesagePersistence();
    }

    public AccessMessages(MessagePersistence data)
    {
        this();
        this.data = data;
    }

    public List<Message> getMessages() {
        return data.getMessages();
    }
}
