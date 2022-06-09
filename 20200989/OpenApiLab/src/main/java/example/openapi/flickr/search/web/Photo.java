package example.openapi.flickr.search.web;

public class Photo {
	private String id;
	private String owner;
	private String secret;	
	private String title;	
	private String farm;
	private String server;
	private String pageUrl;
	private String thumbUrl;	
	
	public Photo(String id, String owner, String secret, String title, String farmId, String serverId, String pageUrl,
			String thumbUrl) {
		super();
		this.id = id;
		this.owner = owner;
		this.secret = secret;
		this.title = title;
		this.farm = farmId;
		this.server = serverId;
		this.pageUrl = pageUrl;
		this.thumbUrl = thumbUrl;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}	
}
