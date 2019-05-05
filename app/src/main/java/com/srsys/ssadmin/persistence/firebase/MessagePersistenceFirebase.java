package com.srsys.ssadmin.persistence.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.srsys.ssadmin.objects.Message;
import com.srsys.ssadmin.persistence.MessagePersistence;

import java.util.List;

public class MessagePersistenceFirebase implements MessagePersistence
{
    private List<Message> messages;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public MessagePersistenceFirebase()
    {
        database = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        myRef = database.getReference().child("Messages").child(
                currentUser.getUid());
    }

    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public void sendMessage(String timeStamp, String message_,
                            String to, String messgeId) {

        Message.MessageBuilder builder = new Message.MessageBuilder();
        Message message = builder.buildMessage(message_)
                .buildTimeStamp(timeStamp)
                .buildFrom(currentUser.getUid())
                .buildTo(to).build();

        message.setMessgeId(myRef.push().getKey());
        myRef.child(message.getMessgeId()).setValue(message);
    }

    @Override
    public boolean deleteMessage(Message message)
    {
        myRef.child(message.getMessgeId()).removeValue();
        if (myRef.child(message.getMessgeId()).equals(null))
        {
            return true;
        }
        return false;
    }
}
