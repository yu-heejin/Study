package ch10;


public class UnparsedEntityDecl {
	public String name;
	public String publicId;
	public String systemId;
	public String notationName;

	public UnparsedEntityDecl(String name, String publicId, String systemId, String notationName) {
		this.name = name;
		this.publicId = publicId;
		this.systemId = systemId;
		this.notationName = notationName;
	}
}

