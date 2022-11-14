package lab4;

public class Employee {
     private int empNo;
     private String ename;
     private String job;
     private double sal;
     private double comm;
     private String dname;
     
     public Employee(int empNo, String ename, String job, double sal, double comm, String dname) {
          super();
          this.empNo = empNo;
          this.ename = ename;
          this.job = job;
          this.sal = sal;
          this.comm = comm;
          this.dname = dname;
     }
     
     public Employee(int empNo, String ename, String job, double sal, double comm) {
          super();
          this.empNo = empNo;
          this.ename = ename;
          this.job = job;
          this.sal = sal;
          this.comm = comm;
     }
     
     public Employee(String ename, String job, double sal, double comm, String dname) {
          super();
          this.ename = ename;
          this.job = job;
          this.sal = sal;
          this.comm = comm;
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
     
     public double getSal() {
          return sal;
     }
     
     public void setSal(double sal) {
          this.sal = sal;
     }
     
     public double getComm() {
          return comm;
     }
     
     public void setComm(double comm) {
          this.comm = comm;
     }
     
     public String getDname() {
          return dname;
     }
     
     public void setDname(String dname) {
          this.dname = dname;
     }

     @Override
     public String toString() {
          return ename + "\t" + job + "\t" + sal + "\t" + comm
                    + "\t" + dname;
     }
     
     
     
}
