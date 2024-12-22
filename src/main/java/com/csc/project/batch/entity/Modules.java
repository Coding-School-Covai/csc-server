package com.csc.project.batch.entity;

import java.time.LocalDate;
import java.util.List;

import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "modules")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Modules extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cloudFilePath;
	private int duration;
	@ManyToOne
	@JoinColumn(name = "sub_course_id", nullable = false)
	private SubCourse subCourse;

	@ElementCollection
	private List<String> topics; 

}
