package lab4;

public class Employee {
	private int empNo;
	private String ename;
	private String job;
	private String dname;
	
	
	
	public Employee(int empNo, String ename, String job, String dname) {
		super();
		this.empNo = empNo;
		this.ename = ename;
		this.job = job;
		this.dname = dname;
	}
	
	public int getEmpNo() {
		return empNo;
	}
	
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	public String getDname() {
		return dname;
	}
	
	public void setDname(String dname) {
		this.dname = dname;
	}
	
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", ename=" + ename + ", job=" + job + ", dname=" + dname + "]";
	}
}
