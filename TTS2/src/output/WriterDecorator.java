package output;

import java.util.List;

public abstract class WriterDecorator implements DocumentWriter {
	private DocumentWriter documentWriter;
	
	public WriterDecorator(DocumentWriter documentWriter) {
		this.documentWriter = documentWriter;
	}
	
	@Override
	public abstract void write();
	
}
