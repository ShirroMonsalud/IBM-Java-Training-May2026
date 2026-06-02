package org.eclipse.jakarta.backingbean;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportListBeanTest {

	private ReportRepository repository;
    private ReportDto testReport1;
    private ReportDto testReport2;

    @BeforeEach
    void setUp() {
        repository = new ReportRepository();

        testReport1 = new ReportDto();
        testReport1.setTitle("Test Report 1");
        testReport1.setDetail("This is the first test report");

        testReport2 = new ReportDto();
        testReport2.setTitle("Test Report 2");
        testReport2.setDetail("This is the second test report");

        System.out.println("@BeforeEach: Test setup completed");
    }

    @AfterEach
    void tearDown() {
    	repository = null;
        testReport1 = null;
        testReport2 = null;
        System.out.println("@AfterEach: Test cleanup completed");
    }

    @Test
    @DisplayName("Should create a new report with auto-generated ID")
    void testCreate() {
        repository.create(testReport1);

        assertNotNull(testReport1.getId(), "Report ID should be auto-generated");
        assertEquals(1L, testReport1.getId(), "First report should have ID 1");
        assertEquals(1, repository.findAll().size(), "Repository should contain 1 report");
    }
    
    @Test
    @DisplayName("Should show the content of the chosen report")
    void testView() {
        repository.create(testReport1);

        ReportDto chosenReport = repository.findbyId(testReport1.getId());
                
        assertEquals("Test Report 1", chosenReport.getTitle(), "First report should have the title 'Test Report 1'");
        assertEquals("This is the first test report", chosenReport.getDetail(), "Repository should contain 'This is the first test report'");
    }
    
    @Test
    @DisplayName("Should update the content of the chosen report")
    void testUpdate() {
        repository.create(testReport1);
        
        repository.update(testReport1.getId(), "New Title 1", "New Detail 1");
        
        ReportDto chosenReport = repository.findbyId(testReport1.getId());
                
        assertEquals("New Title 1", chosenReport.getTitle(), "This report should have the title 'New Title'");
        assertEquals("New Detail 1", chosenReport.getDetail(), "Repository should contain 'New Detail'");
    }
    
    @Test
    @DisplayName("Should delete the chosen report")
    void testDelete() {
        repository.create(testReport1);
        
        repository.delete(testReport1.getId());
        
        ReportDto chosenReport = repository.findbyId(testReport1.getId());
                
        assertNull(chosenReport);
    }
    
    @Test
    @DisplayName("Should return all reports")
    void testList() {
        repository.create(testReport1);
        repository.create(testReport2);
        
        List<ReportDto> allReports = repository.findAll();
        
        assertNotNull(allReports, "The returned list should not be null");
        assertEquals(2, allReports.size(), "Repository should contain exactly 2 reports");
        
        assertTrue(allReports.contains(testReport1), "The list should contain the first report");
        assertTrue(allReports.contains(testReport2), "The list should contain the second report");
    }
    

}
