package serializeTools.Utils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;


public class HttpClient {

	private HttpProxy proxy;


	public void setProxy(HttpProxy proxy) {
		this.proxy = proxy;
	}

	public void enableSSL(boolean enabled) {
		HttpClientWrapper.enabledSSL(enabled);
	}

	public ResponseStatus get(String url) throws HttpException, IOException {
		HttpClientWrapper hw = new HttpClientWrapper(proxy);
		return hw.sendRequest(url);
	}

	public ResponseStatus get(String url, String urlEncoding) {
		HttpClientWrapper hw = new HttpClientWrapper(proxy);
		ResponseStatus response = null;
		try {
			response = hw.sendRequest(url, urlEncoding);
		} catch (Exception e) {
			response.setStatusCode(505);
			return response;
		}
		return response;
	}

	public ResponseStatus post(String url) {
		HttpClientWrapper hw = new HttpClientWrapper(proxy);
		ResponseStatus ret = null;
		try {
			setParams(url, hw);
			ret = hw.postNV(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private void setParams(String url, HttpClientWrapper hw) {
		String[] paramStr = url.split("[?]", 2);
		if (paramStr == null || paramStr.length != 2) {
			return;
		}
		String[] paramArray = paramStr[1].split("[&]");
		if (paramArray == null) {
			return;
		}
		for (String param : paramArray) {
			if (param == null || "".equals(param.trim())) {
				continue;
			}
			String[] keyValue = param.split("[=]", 2);
			if (keyValue == null || keyValue.length != 2) {
				continue;
			}
			hw.addNV(keyValue[0], keyValue[1]);
		}
	}

	public ResponseStatus post(String url, Map<String, Object> paramsMap) {
		HttpClientWrapper hw = new HttpClientWrapper(proxy);
		ResponseStatus ret = null;
		try {
			setParams(url, hw);
			Iterator<String> iterator = paramsMap.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = paramsMap.get(key);
				if (value instanceof File) {
					FileBody fileBody = new FileBody((File) value);
					hw.getContentBodies().add(fileBody);
				} else if (value instanceof byte[]) {
					byte[] byteVlue = (byte[]) value;
					ByteArrayBody byteArrayBody = new ByteArrayBody(byteVlue, key);
					hw.getContentBodies().add(byteArrayBody);
				} else {
					if (value != null && !"".equals(value)) {
						hw.addNV(key, String.valueOf(value));
					} else {
						hw.addNV(key, "");
					}
				}
			}
			ret = hw.postEntity(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public ResponseStatus post(String url, String jsonBody) {
		return post(url, jsonBody, "application/json");
	}

	public ResponseStatus postXml(String url, String xmlBody) {
		return post(url, xmlBody, "application/xml");
	}

	private ResponseStatus post(String url, String body, String contentType) {
		HttpClientWrapper hw = new HttpClientWrapper(proxy);
		ResponseStatus ret = null;
		try {
			hw.addNV("body", body);
			ret = hw.postNV(url, contentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}