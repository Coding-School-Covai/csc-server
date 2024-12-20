package com.csc.project.batch.dto;

import java.time.LocalDate;
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
public class BatchDTO {

	private Long id;

	private String classLink;

	private LocalDate startDate;

	private LocalDate endDate;
	
	@Size(max = 15, message = "Language cannot exceed 10 characters")
	private String language;

	private Boolean isActive;
	
	private Long slotId;
	
	private String slotName;

}
