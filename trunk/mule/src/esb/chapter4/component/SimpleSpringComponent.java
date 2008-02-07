package esb.chapter4.component;

public class SimpleSpringComponent {
	
	private ResponseOptionsIF responseOptions;
	
	public String generateResponse(String word) {
		return responseOptions.getResponseOption(word);
	}

	public void setResponseOptions(ResponseOptionsIF responseOptions) {
		this.responseOptions = responseOptions;
	}
}
