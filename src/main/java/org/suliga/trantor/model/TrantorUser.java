package org.suliga.trantor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class TrantorUser {
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String password;
	
	private boolean enabled;
}
