package org.eclipse.jakarta.infrastracture.repository;

import java.sql.*;
import java.util.*;
import org.eclipse.jakarta.dto.ReportDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    
	/*
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
    */
	
	private static final String URL = "jdbc:postgresql://localhost:5432/reportDB";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";
	
	public List<ReportDto> findAll() {
		List<ReportDto> reports = new ArrayList<>();
		String sql = "SELECT * FROM reports ORDER BY id ASC";
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				ReportDto dto = new ReportDto();
				dto.setId(rs.getLong("id"));
				dto.setTitle(rs.getString("title"));
				dto.setDetail(rs.getString("detail"));
				reports.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Database Error: " + e.getMessage());
		}
		return reports;
	}
	
	public void create(ReportDto report) {
        String sql = "INSERT INTO reports (title, detail) VALUES (?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             
            ps.setString(1, report.getTitle());
            ps.setString(2, report.getDetail());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage()); 
        }
    }
	
	public ReportDto findbyId(Long id) {
        ReportDto report = null;
        String sql = "SELECT * FROM reports WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    report = new ReportDto();
                    report.setId(rs.getLong("id"));
                    report.setTitle(rs.getString("title"));
                    report.setDetail(rs.getString("detail"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage()); 
        }
        return report;
    }

    public void update(Long id, String title, String detail) {
        String sql = "UPDATE reports SET title = ?, detail = ? WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             
            ps.setString(1, title);
            ps.setString(2, detail);
            ps.setLong(3, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage()); 
        }
    }

    public void delete(Long id) {
        String sql = "DELETE FROM reports WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
             
            ps.setLong(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage()); 
        }
    }

	
}