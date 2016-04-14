import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

/**
 *  用户TEST2， 发给TEST3
 * @author kwang
 *
 */
public class Test {

	public static void main(String[] args) {
		try {

			XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword("test2", "test2")
					.setServiceName("127.0.0.1").setHost("127.0.0.1").setPort(5222).setSecurityMode(SecurityMode.disabled).build();

			AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
			// System.out.println(conn2.isAuthenticated());

			conn2.connect();

			System.out.println(conn2.isConnected());
			// conn2.login("test2","test2","127.0.0.1");
			conn2.login();
			// System.out.println("ffff");

			ChatManager chatmanager = ChatManager.getInstanceFor(conn2);

			chatmanager.addChatListener(new ChatManagerListener() {
				@Override
				public void chatCreated(Chat chat, boolean createdLocally) {
					if (!createdLocally) {
						chat.addMessageListener(new ChatMessageListener() {

							@Override
							public void processMessage(Chat chat, Message message) {
								System.out.println("test2 Received message: " + message);
								System.out.println("test2 Received message: " + message.getBody());
								
//								try {
//									chat.sendMessage(message);
//								} catch (NotConnectedException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}

							}
						});
					}
					
				
				}
			});
			Thread.sleep(1000 * 10);
			Chat chat = chatmanager.createChat("test3@127.0.0.1");
			
			 chat.sendMessage("test2 send messeage is ok 333");
			
			 chat.sendMessage("test2 send second message");
			 Thread.sleep(1000 * 30);
			 conn2.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
