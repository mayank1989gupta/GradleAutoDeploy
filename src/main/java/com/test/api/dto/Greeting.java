/**
 * 
 */
package com.test.api.dto;

/**
 * @author MGupta
 *
 */
public class Greeting {

    private long id;//ID
    private String content;//content
	
	/**
	 * @param id
	 * @param content
	 */
	public Greeting(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
}