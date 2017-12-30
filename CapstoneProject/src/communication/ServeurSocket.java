package communication;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONArray;
import org.json.JSONObject;

import application.InitialisationSysteme;

public class ServeurSocket extends WebSocketServer{

	
    private static int TCP_PORT = 8003;
    private List<WebSocket> socketsIHM;
    
    private List<String> messages;
    

	public ServeurSocket() throws IOException
	{
        super(new InetSocketAddress(TCP_PORT));
        socketsIHM = new ArrayList<WebSocket>();
        messages = new ArrayList<String>();
	}
	
	@Deprecated
	public WebSocket getSocketIHM()
	{
		if(socketsIHM.isEmpty())
			return null;
		else	
			return socketsIHM.get(0);
	}
	
	public void send(String data) {
		this.send(data, 0);
	}
	
	public void send(String data, int socketIndex) {
		this.socketsIHM.get(0).send(data);
	}
	
	
	@Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        socketsIHM.add(conn);
        System.out.println("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        socketsIHM.remove(conn);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Message from client: " + message);
        int index = this.socketsIHM.indexOf(conn);
        this.messages.add(index, message);
        
        if(message.startsWith("init")) {
        	String subMessage = message.substring(6, message.length()-1);
        	System.out.println(subMessage);
        	//InitialisationSysteme.initialiserSysteme("{     \"ressources\": [         {             \"id\": 1,             \"nb\": 1,             \"name\": \"m1\",             \"services\": [                 {                     \"id\": 1,                     \"duration\": 10                 },                 {                     \"id\": 2,                     \"duration\": 10                 }             ]         },         {             \"id\": 2,             \"nb\": 1,             \"name\": \"m2\",             \"services\": [                 {                     \"id\": 3,                     \"duration\": 20                 },                 {                     \"id\": 4,                     \"duration\": 20                 },                 {                     \"id\": 1,                     \"duration\": 20                 }             ]         },         {             \"id\": 3,             \"nb\": 1,             \"name\": \"m3\",             \"services\": [                 {                     \"id\": 3,                     \"duration\": 20                 },                 {                     \"id\": 4,                     \"duration\": 20                 },                 {                     \"id\": 2,                     \"duration\": 20                 }             ]         },         {             \"id\": 4,             \"nb\": 1,             \"name\": \"m4\",             \"services\": [                 {                     \"id\": 4,                     \"duration\": 20                 }             ]         },         {             \"id\": 5,             \"nb\": 1,             \"name\": \"m5\",             \"services\": [                 {                     \"id\": 1,                     \"duration\": 5                 }             ]         },         {             \"id\": 6,             \"nb\": 1,             \"name\": \"m6\",             \"services\": [                 {                     \"id\": 2,                     \"duration\": 60                 }             ]         },         {             \"id\": 7,             \"nb\": 1,             \"name\": \"m7\",             \"services\": []         }     ],     \"services\": [         {             \"id\": 1,             \"name\":\"deplacement\"         },         {             \"id\": 2,             \"name\":\"peindre\"         },         {             \"id\": 3,             \"name\":\"souder\"         },         {             \"id\": 4,             \"name\":\"peindre\"         }     ],     \"perturbations\": [         {             \"trigger\": \"init:ok\",             \"action\": \"stop:all\"         }     ],     \"orders\": [         {             \"id\": 1,             \"products\": [                 {                     \"id\": 1,                     \"nb\": 1                 }             ]         }     ],     \"products\": [         {             \"id\":1 ,             \"description\": \"faire un joli produit necessitant un deplacement\",             \"services\":[                 [4],                 [2]             ]         }     ],         \"layoutSpec\": {         \"nodes\": [             {                 \"id\": 1,                 \"name\": \"n1\"             },             {                 \"id\": 2,                 \"name\": \"m7\",                 \"ressource\": 7             },             {                 \"id\": 3,                 \"name\": \"n2\"             },             {                 \"id\": 4,                 \"name\": \"n3\"             },             {                 \"id\": 5,                 \"name\": \"n4\"             },             {                 \"id\": 6,                 \"name\": \"n5\"             },             {                 \"id\": 7,                 \"name\": \"m1\",                 \"ressource\": 1             },             {                 \"id\": 8,                 \"name\": \"n6\"             },             {                 \"id\": 9,                 \"name\": \"n7\"             },             {                 \"id\": 10,                 \"name\": \"n8\"             },             {                 \"id\": 11,                 \"name\": \"n9\"             },             {                 \"id\": 12,                 \"name\": \"m2\",                 \"ressource\": 2             },             {                 \"id\": 13,                 \"name\": \"n10\"             },             {                 \"id\": 14,                 \"name\": \"n11\"             },             {                 \"id\": 15,                 \"name\": \"m3\",                 \"ressource\": 3             },             {                 \"id\": 16,                 \"name\": \"n12\"             },             {                 \"id\": 17,                 \"name\": \"n13\"             },             {                 \"id\": 18,                 \"name\": \"m4\",                 \"ressource\": 4             },             {                 \"id\": 19,                 \"name\": \"n14\"             },             {                 \"id\": 20,                 \"name\": \"n15\"             },             {                 \"id\": 21,                 \"name\": \"n16\"             },             {                 \"id\": 22,                 \"name\": \"n17\"             },             {                 \"id\": 23,                 \"name\": \"m5\",                 \"ressource\": 5             },             {                 \"id\": 24,                 \"name\": \"n18\"             },             {                 \"id\": 25,                 \"name\": \"n19\"             },             {                 \"id\": 26,                 \"name\": \"n20\"             },             {                 \"id\": 27,                 \"name\": \"n21\"             },             {                 \"id\": 28,                 \"name\": \"m6\",                 \"ressource\": 6             },             {                 \"id\": 29,                 \"name\": \"n22\"             }         ],         \"arcs\": [             {                 \"id\": \"1_1\",                 \"from\": 1,                 \"to\": 2,                 \"size\": 1             },             {                 \"id\": \"1_2\",                 \"from\": 2,                 \"to\": 3,                 \"size\": 1             },             {                 \"id\": \"2\",                 \"from\": 1,                 \"to\": 3,                 \"size\": 1             },             {                 \"id\": \"3\",                 \"from\": 3,                 \"to\": 4,                 \"size\": 1             },             {                 \"id\": \"4\",                 \"from\": 4,                 \"to\": 5,                 \"size\": 1             },             {                 \"id\": \"5\",                 \"from\": 5,                 \"to\": 6,                 \"size\": 1             },             {                 \"id\": \"6_1\",                 \"from\": 6,                 \"to\": 7,                 \"size\": 1             },             {                 \"id\": \"6_2\",                 \"from\": 7,                 \"to\": 8,                 \"size\": 1             },             {                 \"id\": \"7\",                 \"from\": 6,                 \"to\": 8,                 \"size\": 1             },             {                 \"id\": \"8\",                 \"from\": 8,                 \"to\": 9,                 \"size\": 1             },             {                 \"id\": \"9\",                 \"from\": 9,                 \"to\": 10,                 \"size\": 1             },             {                 \"id\": \"10\",                 \"from\": 10,                 \"to\": 11,                 \"size\": 1             },             {                 \"id\": \"11_1\",                 \"from\": 11,                 \"to\": 12,                 \"size\": 1             },             {                 \"id\": \"11_2\",                 \"from\": 12,                 \"to\": 13,                 \"size\": 1             },             {                 \"id\": \"12\",                 \"from\": 11,                 \"to\": 13,                 \"size\": 1             },             {                 \"id\": \"13\",                 \"from\": 13,                 \"to\": 14,                 \"size\": 1             },             {                 \"id\": \"14_1\",                 \"from\": 14,                 \"to\": 15,                 \"size\": 1             },             {                 \"id\": \"14_2\",                 \"from\": 15,                 \"to\": 16,                 \"size\": 1             },             {                 \"id\": \"15\",                 \"from\": 14,                 \"to\": 16,                 \"size\": 1             },             {                 \"id\": \"16\",                 \"from\": 16,                 \"to\": 17,                 \"size\": 1             },             {                 \"id\": \"17_1\",                 \"from\": 17,                 \"to\": 18,                 \"size\": 1             },             {                 \"id\": \"17_2\",                 \"from\": 18,                 \"to\": 19,                 \"size\": 1             },             {                 \"id\": \"18\",                 \"from\": 17,                 \"to\": 19,                 \"size\": 1             },             {                 \"id\": \"19\",                 \"from\": 19,                 \"to\": 20,                 \"size\": 1             },             {                 \"id\": \"20\",                 \"from\": 20,                 \"to\": 21,                 \"size\": 1             },             {                 \"id\": \"21\",                 \"from\": 21,                 \"to\": 22,                 \"size\": 1             },             {                 \"id\": \"22_1\",                 \"from\": 22,                 \"to\": 23,                 \"size\": 1             },             {                 \"id\": \"22_2\",                 \"from\": 23,                 \"to\": 24,                 \"size\": 1             },             {                 \"id\": \"23\",                 \"from\": 22,                 \"to\": 24,                 \"size\": 1             },             {                 \"id\": \"24\",                 \"from\": 24,                 \"to\": 25,                 \"size\": 1             },             {                 \"id\": \"25\",                 \"from\": 25,                 \"to\": 26,                 \"size\": 1             },             {                 \"id\": \"26\",                 \"from\": 26,                 \"to\": 27,                 \"size\": 1             },             {                 \"id\": \"27_1\",                 \"from\": 27,                 \"to\": 28,                 \"size\": 1             },             {                 \"id\": \"27_2\",                 \"from\": 28,                 \"to\": 29,                 \"size\": 1             },             {                 \"id\": \"28\",                 \"from\": 27,                 \"to\": 29,                 \"size\": 1             },             {                 \"id\": \"29\",                 \"from\": 29,                 \"to\": 1,                 \"size\": 1             },             {                 \"id\": \"30\",                 \"from\": 4,                 \"to\": 26,                 \"size\": 1             },             {                 \"id\": \"31\",                 \"from\": 25,                 \"to\": 5,                 \"size\": 1             },             {                 \"id\": \"32\",                 \"from\": 9,                 \"to\": 21,                 \"size\": 1             },             {                 \"id\": \"33\",                 \"from\": 20,                 \"to\": 10,                 \"size\": 1             }         ]     } }"
//);
        	InitialisationSysteme.initialiserSysteme(subMessage);
        }
        
//		try {
//			File log = new File("logsIHM.txt");
//			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			
//			out.write(message + '\n');
			
//			out.close();
			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
        	socketsIHM.remove(conn);
            // do some thing if required
        }
        System.out.println("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

}
