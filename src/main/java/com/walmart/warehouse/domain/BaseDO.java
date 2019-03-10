package com.walmart.warehouse.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * 
 * @author a0k016d
 *
 */
@MappedSuperclass
public abstract class BaseDO {

	@Id
	@Type(type = "uuid-char")
	@Column(name = "ID")
	protected UUID id = UUID.randomUUID();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETS", updatable = false)
	private Date createTs;

	@Column(name = "CREATEUSERID", updatable = false)
	private String createUserId;

	@Column(name = "CREATEPROGID", updatable = false)
	private String createProgId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFYTS")
	private Date modifyTs;

	@Column(name = "MODIFYUSERID")
	private String modifyuserId;

	@Column(name = "MODIFYPROGID")
	private String modifyProgId;

	@Version
	@Column(name = "DB_LOCK_VERSION")
	private Long version;

	@Transient
	private String lock;

	public void setId(String id) {
		this.id = UUID.fromString(id);
	}

	
	public String getId() {
		return id.toString();
	}

	
	public Long getVersion() {
		return version;
	}

	
	public String getLock() {
		return lock;
	}

	
	public void setVersion(Long version) {
		this.version = version;
	}

	
	public void setLock(String lock) {
		this.lock = lock;
	}

	
	public Date getCreateTs() {
		return createTs;
	}

	
	public String getCreateUserId() {
		return createUserId;
	}

	
	public String getCreateProgId() {
		return createProgId;
	}

	
	public Date getModifyTs() {
		return modifyTs;
	}

	
	public String getModifyUserId() {
		return modifyuserId;
	}

	
	public String getModifyProgId() {
		return modifyProgId;
	}

	
	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	
	public void setCreateProgId(String createProgId) {
		this.createProgId = createProgId;
	}

	
	public void setModifyTs(Date modifyTs) {
		this.modifyTs = modifyTs;
	}

	
	public void setModifyUserId(String modifyUserId) {
		this.modifyuserId = modifyUserId;
	}

	
	public void setModifyProgId(String modifyProgId) {
		this.modifyProgId = modifyProgId;
	}

	@PrePersist
	public void setCreateDefaults() {
		Date current = new Date();
		String userId = "app";
		String progId = "system";
		this.createTs = current;
		this.modifyTs = current;
		this.createUserId = (getCreateUserId() != null) ? getCreateUserId() : userId;
		this.modifyuserId = (getCreateUserId() != null) ? getCreateUserId() : userId;
		this.createProgId = (getCreateProgId() != null) ? getCreateProgId() : progId;
		this.modifyProgId = (getCreateProgId() != null) ? getCreateProgId() : progId;
	}

	@PreUpdate
	public void setUpdateDefaults() {
		this.modifyTs = new Date();
		this.modifyuserId = (getModifyUserId() != null) ? getModifyUserId() : "api-client";
		this.modifyProgId = (getModifyProgId() != null) ? getModifyProgId() : "api";
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDO other = (BaseDO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
