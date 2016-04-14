
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class Test2 {

	public static void main(String[] args) {
		try {

			XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword("test3", "test3")
					.setServiceName("127.0.0.1").setHost("127.0.0.1").setPort(5222).setSecurityMode(SecurityMode.disabled).build();

			AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
			// System.out.println(conn2.isAuthenticated());

			conn2.connect();

			System.out.println(conn2.isConnected());
			// conn2.login("test2","test2","127.0.0.1");
			conn2.login();
			// System.out.println("ffff");

//			Chat chat = ChatManager.getInstanceFor(conn2).createChat("test1@127.0.0.1", new ChatMessageListener() {
//
//				public void processMessage(Chat chat, Message message) {
//					try {
//						chat.sendMessage(message.getBody());
//					} catch (NotConnectedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					System.out.println("Received message: " + message);
//				}
//			});
			Chat chat = ChatManager.getInstanceFor(conn2).createChat("test2@127.0.0.1");
			Thread.sleep(20 * 1000);
			chat.sendMessage("test3   send messeage");
			conn2.addPacketListener(new PacketListener() {

				public void processPacket(Stanza packet) throws NotConnectedException {
			
				Message message = (Message)packet; 
				System.out.println(message.getFrom() + ":"+ message.getBody()); 
				} 
				}, new PacketTypeFilter(Message.class)); 
			
//			chat.addMessageListener(new ChatMessageListener() {
//				
//				@Override
//				public void processMessage(Chat chat, Message message) {
//					System.out.println("test3  Received message: " + message);
//					
//				}
//			});
			chat.sendMessage("test3  send second message");
//			Thread.sleep(1000 * 100000);
			while(true);
//			conn2.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
