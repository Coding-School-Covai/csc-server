package com.csc.project.batch.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SlotDTO {

	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Size(max = 30, message = "Name cannot exceed 30 characters")
	private String name;

	@Size(max = 10, message = "Duration cannot exceed 10 characters")
	private String duration;

	private LocalTime startTime;

	private LocalTime endTime;

	private Boolean isActive;

}
