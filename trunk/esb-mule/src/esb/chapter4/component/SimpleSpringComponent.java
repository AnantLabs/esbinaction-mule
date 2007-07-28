package esb.chapter4.component;

public class SimpleSpringComponent {
	
	private ResponseOptionsIF responseOptions;
	
	public String generateResponse(String word) {
		return responseOptions.getResponseOption(word);
	}

	public ResponseOptionsIF getResponseOptions() {
		return responseOptions;
	}

	public void setResponseOptions(ResponseOptionsIF responseOptions) {
		this.responseOptions = responseOptions;
	}
}
