package com.srsys.ssadmin.objects;


public class Message
{
    String timeStamp;
    String message;
    String from;
    String to;
    String messgeId;


    public Message(MessageBuilder builder)
    {
        this.messgeId = builder.messgeId;
        this.timeStamp = builder.timeStamp;
        this.message = builder.message;
        this.to = builder.to;
        this.from = builder.from;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessgeId() {
        return messgeId;
    }

    public void setMessgeId(String messgeId) {
        this.messgeId = messgeId;
    }


    public static class MessageBuilder
    {
        private String timeStamp;
        private String message;
        private String from;
        private String to;
        private String messgeId;

        public MessageBuilder()
        {
        }

        public MessageBuilder buildTimeStamp(String timeStamp)
        {
            this.timeStamp = timeStamp;
            return this;
        }

        public MessageBuilder buildMessage(String message_)
        {
            this.message = message_;
            return this;
        }

        public MessageBuilder buildFrom(String from)
        {
            this.from = from;
            return this;
        }

        public MessageBuilder buildTo(String to)
        {
            this.to = to;
            return this;
        }

        public Message build()
        {
            return new Message(this);
        }
    }
}
