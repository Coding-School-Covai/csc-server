package com.csc.project.batch.entity;

import java.time.LocalTime;

import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "slots")
@EqualsAndHashCode(callSuper = true)
public class Slot extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="name")
	@NotBlank
	@Size(max = 30)
	private String name;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;
	
	@Column(name = "isActive")
	private Boolean isActive;
}
