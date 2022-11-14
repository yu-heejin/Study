package lab4;

public class Department {
     private int deptNo;
     private String deptName;
     private int mgrNo;
     private int numOfEmps;
     
     public Department(int deptNo, String deptName, int mgrNo, int numOfEmps) {
          super();
          this.deptNo = deptNo;
          this.deptName = deptName;
          this.mgrNo = mgrNo;
          this.numOfEmps = numOfEmps;
     }

     public int getDeptNo() {
          return deptNo;
     }
     
     public void setDeptNo(int deptNo) {
          this.deptNo = deptNo;
     }
     
     public String getDeptName() {
          return deptName;
     }
     
     public void setDeptName(String deptName) {
          this.deptName = deptName;
     }
     
     public int getMgrNo() {
          return mgrNo;
     }
     
     public void setMgrNo(int mgrNo) {
          this.mgrNo = mgrNo;
     }
     
     public int getNumOfEmps() {
          return numOfEmps;
     }
     
     public void setNumOfEmps(int numOfEmps) {
          this.numOfEmps = numOfEmps;
     }
     
     
     
}
