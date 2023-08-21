package ch12;

import java.util.Map;
import java.util.HashMap;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestConfig extends Application {
	public Map<String, Object> getProperties(){
		
		Map<String,Object> properties=new HashMap<String, Object>();
		properties.put("jersy.config.server.provider.packages", "ch12");
		
		return properties;
	}
}
