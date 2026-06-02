package org.eclipse.jakarta.infrastracture.repository;

import java.util.*;
import org.eclipse.jakarta.dto.ReportDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    private List<ReportDto> reports = new ArrayList<>();
    
    private Long idCounter = 1L;
    
    public List<ReportDto> findAll() {
        return reports;
    }

    public void create(ReportDto report) {
        report.setId(idCounter++);
    	reports.add(report);
    }
    
    public ReportDto findbyId(Long id) {
    	return reports.stream()
    			.filter(r -> r.getId().equals(id))
    			.findFirst()
    			.orElse(null);
    }
    
    public void update(Long id, String title, String detail) {
    	ReportDto report = findbyId(id);

        if (report != null) {
            report.setTitle(title);
            report.setDetail(detail);
        }
		
	}
    
    public void delete(Long id) {
    	reports.removeIf(r -> r.getId().equals(id));
    }

	
}