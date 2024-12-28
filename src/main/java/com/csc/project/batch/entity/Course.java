package com.csc.project.batch.entity;

import java.util.List;

import com.csc.project.common.jpa.audit.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "course")
@EqualsAndHashCode(callSuper = true)
public class Course extends Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "duration", nullable = false)
	private double duration;

	@Column(name = "fees", nullable = false)
	private double fees;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "level", nullable = false)
	private String level;

	
	
    @ManyToMany
    @JoinTable(
        name = "course_sub_courses",
        joinColumns = @JoinColumn(name = "course_id"),
        inverseJoinColumns = @JoinColumn(name = "sub_course_id")
    )
    private List<SubCourse> subCourses;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@PrePersist
	protected void onCreate() {
		this.isActive = true;
	}

}
