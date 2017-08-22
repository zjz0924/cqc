package cn.wow.common.domain;

import java.io.Serializable;

public class Role implements Serializable, MybatisVO{
	
	private static final long serialVersionUID = 5632083155878693998L;

	private Long id;

    private String name;

    public Role(){
    	
    }
    
    public Role(String name){
    	this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}