package com.onlineAcademy.CodingSchool.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "sub_courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@PrePersist
	protected void onCreate() {
		this.isActive = true;
	}
}