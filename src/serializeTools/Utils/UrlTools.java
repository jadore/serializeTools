package serializeTools.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlTools
{
	private final static UrlTools instance = new UrlTools();

	public static UrlTools getInstance()
	{
		return instance;
	}
	
	/**
	*校验URL是否合法
	*return map{"Validate":"true/false"}
	*/
	
	public Map<String, String> checkValidate(String Url)
	{
		final Map<String, String> Result = new HashMap<String, String>();
		if (!Url.startsWith("http"))
		{
			Result.put("Validate", "false");
			return Result;
		}
		if (Url.endsWith("/"))
		{
			Url = Url.replaceAll("/$", "");
		}
		Pattern p = Pattern.compile(
				"^(http|www|ftp|)?(://)?((\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*)((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$",
				2);
		final Matcher m = p.matcher(Url);
		if (m.find())
		{
			Result.put("Validate", "true");
			Result.put("Scheme", m.group(1));
			final String Host = m.group(3);
			String Port = "80";
			p = Pattern.compile(":(\\d+)");
			final Matcher mport = p.matcher(Url);
			if (mport.find())
			{
				Port = mport.group(1);
			}
			Result.put("Host", Host);
			Result.put("Port", Port);
		} else
		{
			Result.put("Validate", "false");
		}
		return Result;
	}
}
