package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named 
@RequestScoped
public class ReportViewBean {
    
    private Long id;
    private ReportDto report;

    @Inject
    private ReportRepository reportRepository;

    public void init() {
        if (id != null) {
            report = reportRepository.findbyId(id);
        }
    }

    public Long getId() { 
    	return id; 
    	}
    
    public void setId(Long id) { 
    	this.id = id; 
    	}
    
    public ReportDto getReport() { 
    	return report; 
    	}
    
    public void setReport(ReportDto report) { 
    	this.report = report; 
    	}
    
}