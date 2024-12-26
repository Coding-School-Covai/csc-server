package com.csc.project.batch.service;

import com.csc.project.batch.dto.InstallmentDTO;
import com.csc.project.batch.dto.InstallmentFilter;
import com.csc.project.batch.dto.InstallmentPageResponse;

public interface InstallmentService {
	void addInstallment(InstallmentDTO installmentDTO);

	InstallmentPageResponse getInstallments(InstallmentFilter installmentFilter);

	InstallmentDTO getInstallmentById(Long installmentId);

	void updateInstallment(Long installmentId, InstallmentDTO installmentDTO);

}
