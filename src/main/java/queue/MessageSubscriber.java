package queue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageSubscriber implements MessageListener {

	public static List<String> messageList = new ArrayList<String>();
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		messageList.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
	}

}
