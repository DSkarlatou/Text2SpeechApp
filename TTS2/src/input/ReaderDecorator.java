package input;

import java.util.List;

public abstract class ReaderDecorator implements DocumentReader {
	private DocumentReader documentReader;
	
	public ReaderDecorator(DocumentReader documentReader) {
		this.documentReader = documentReader;
	}
	
	@Override
	public abstract List<String> read();
	
}
