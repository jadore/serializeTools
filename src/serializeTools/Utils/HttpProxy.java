package serializeTools.Utils;

public class HttpProxy {

	private String host;
	private int port;
	private String user;
	private String password;

	public HttpProxy(String ipAndPort) {
		this.host = ipAndPort.split(":")[0];
		this.port = Integer.parseInt(ipAndPort.split(":")[1]);
	}

	public HttpProxy(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	public HttpProxy(String host, int port, String user, String password) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
