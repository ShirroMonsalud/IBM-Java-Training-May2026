package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named 
@RequestScoped
public class ReportDeleteBean {

    @Inject
    private ReportRepository reportRepository;

    public String delete(Long id) {
        reportRepository.delete(id);
        return "/reportList.xhtml?faces-redirect=true";
    }
}