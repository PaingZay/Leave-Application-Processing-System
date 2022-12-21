package sg.nus.iss.team6.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import sg.nus.iss.team6.util.ApplicationStatus;
import sg.nus.iss.team6.util.dayEnum;

@Data
@Entity
public class OvertimeChit {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime otStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDateTime otEnd;
	
	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
	
    @Column(nullable = false)
	private boolean active;
    
	public OvertimeChit() {	}

	//Jason added- autogen Id
	public OvertimeChit(LocalDateTime starTime, LocalDateTime endTime,
			ApplicationStatus status) {
		this.otStart = starTime;
		this.otEnd = endTime;
		this.status = status;
		this.active = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getOtStart() {
		return otStart;
	}

	public void setOtStart(LocalDateTime otStart) {
		this.otStart = otStart;
	}

	public LocalDateTime getOtEnd() {
		return otEnd;
	}

	public void setOtEnd(LocalDateTime otEnd) {
		this.otEnd = otEnd;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "OvertimeChit [overtimeChitId=" + id + ", starTime="
				+ otStart + ", endTime=" + otEnd + ", status=" + status + ", active=" + active + "]";
	}

}