package esb.chapter4.component.spring;

import java.util.Map;

public class ResponseOptions implements ResponseOptionsIF {
	
	private Map<String, String> options;

	public String getResponseOption(String word) {
		if(options.containsKey(word)) {
			return options.get(word);
		} else {
			return options.get("other");
		}
	}
	
	public Map<String, String> getOptions() {
		return options;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
}
