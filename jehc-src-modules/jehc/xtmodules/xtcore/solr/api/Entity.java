package jehc.xtmodules.xtcore.solr.api;
import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;
public class Entity implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
 
    private String id;
    private String title;
    private String[] content;
 
    public Entity() {
        super();
    }
 
    public Entity(String id, String title, String[] content) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
    }
 
    public String getId() {
        return id;
    }
 
    @Field("id")
    public void setId(String id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    @Field("title")
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String[] getContent() {
        return content;
    }
 
    @Field("content")
    public void setContent(String[] content) {
        this.content = content;
    }
}
