package com.csc.project.batch.entity;

import java.time.LocalDate;

import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "batches")
@EqualsAndHashCode(callSuper = true)
public class Batch extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="class_link")
	private String classLink;
	
	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "isActive")
	private Boolean isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "slot_id")
	private Slot slot;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "course_id")
//	private Course course;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "staff_id")
//	private Staff staff;

}
