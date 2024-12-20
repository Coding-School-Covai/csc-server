package com.csc.project.common.jpa.audit;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {
	
	@Column(name = "created_by")
	@CreatedBy
	protected Long createdBy;
	
	@Column(name = "created_dt")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;
	
	@Column(name = "updated_by")
	@LastModifiedBy
	protected Long updatedBy;

	@Column(name = "updated_dt")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedDate;

}
