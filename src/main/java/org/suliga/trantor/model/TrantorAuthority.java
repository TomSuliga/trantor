package org.suliga.trantor.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class TrantorAuthority {
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	private String authority;
	
}
