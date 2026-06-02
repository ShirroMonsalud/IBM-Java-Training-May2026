package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

@Named
@RequestScoped
public class ReportUpdateBean {
	private Long id;
	
	@NotBlank(message = "You cannot leave the title blank.")
	private String title;
	private String detail;
	
	@Inject
	private ReportRepository reportRepository;
	
	public void init() {
		if (id != null) {
			ReportDto report = reportRepository.findbyId(id);
			if (report != null) {
				this.title = report.getTitle();
				this.detail = report.getDetail();
			}
		}
	}
	
	public String update() {
		reportRepository.update(id, title, detail);
		return "/reportList.xhtml?faces-redirect=true";
	}
	
	public Long getId() {
		return id;
		}
	
    public void setId(Long id) { 
    	this.id = id; 
    	}
    
    public String getTitle() { 
    	return title; 
    	}
    
    public void setTitle(String title){
    	this.title = title;
    	}
    
    public String getDetail(){ 
    	return detail; 
    	}
    
    public void setDetail(String detail) { 
    	this.detail = detail;
    	}
}
