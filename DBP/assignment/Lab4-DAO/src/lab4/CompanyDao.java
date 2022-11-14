package lab4;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class CompanyDao {
     private JDBCUtil jdbcUtil = null;     // JDBCUtil 참조 변수 선언
          
     public CompanyDao() {     // 생성자 
          jdbcUtil = new JDBCUtil();          // JDBCUtil 객체 생성 및 활용
          
/*     JDBCUtil 이용 시 불필요
          try {
               Class.forName("oracle.jdbc.driver.OracleDriver");     
          } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
          }
*/
     }

/*     JDBCUtil 이용 시 불필요
     private Connection getConnection() {
          String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";          
          String user = "dbpro";
          String passwd = "dbpro";

          // DBMS와의 연결 획득
          Connection conn = null;
          try {
               conn = DriverManager.getConnection(url, user, passwd);
          } catch (SQLException e) {
               e.printStackTrace();
          }      
          return conn;
     }
*/
     
    public Department findDeptInfo(String deptName) {
         // printDeptInfo()를 변형 
         // Department 객체를 생성하고 검색 결과를 저장해서 return함
         // LAB3 printDeptInfo 참고
         String query = "SELECT deptno, manager, count(empno) AS numOfEmps "
                    + "FROM DEPT0989 JOIN EMP0989 USING (deptno) "
                    + "WHERE dname = ? "
                    + "GROUP BY deptno, manager";
          
          ResultSet rs = null;
          Department department = null;
          jdbcUtil.setSqlAndParameters(query, new Object[] { deptName });
          
          try {
               rs = jdbcUtil.executeQuery();
               
               if (rs.next()) {
                    int deptNo = rs.getInt("deptNo");
                    int managerNo = rs.getInt("manager");
                    int numOfEmps = rs.getInt("numOfEmps");
                    
                    department = new Department(deptNo, deptName, managerNo, numOfEmps);
                    return department;
               }
          } catch (SQLException ex) {
               ex.printStackTrace();
          } finally {          
               jdbcUtil.close();
          }     
          
         return department;
    } 

    public List<Employee> findEmployeesInDept(int deptNo) {
         // printEmployeesInDept()를 변형
         // ArrayList<Employee> 객체를 생성하고, 검색된 각 사원 정보에 대해 Employee 객체를 생성 및 저장하고, 그 객체를 ArrayList에 추가함
         String query = "SELECT empno, ename, job, sal, NVL(comm, 0) AS comm "
                + "FROM DEPT0989 JOIN EMP0989 USING (deptno) "
                + "WHERE deptno = ? ";
         
         List<Employee> emps = new ArrayList<Employee>();
         ResultSet rs = null;
          jdbcUtil.setSqlAndParameters(query, new Object[] { deptNo });
         try {
               rs = jdbcUtil.executeQuery();
               
               while (rs.next()) {
                    int empNo = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                double sal = rs.getDouble("sal");
                double comm = rs.getDouble("comm");
                
                    Employee emp = new Employee(empNo, ename, job, sal, comm);
                    
                    emps.add(emp);
               }
               
               return emps;
          } catch (SQLException ex) {
               ex.printStackTrace();
          } finally {          
               jdbcUtil.close();
          }     
         
         return emps;
    }

    public void replaceManagerOfDept(int deptNo, int managerNo, double commission) {
        // 기존과 동일
         String updateQuery1 = "UPDATE DEPT0989 SET manager = ? WHERE deptNo = ?";
        String updateQuery2 = "UPDATE EMP0989 SET comm = NVL(comm, 0) + ? "   //nvl 함수를 이용해 comm이 null이면 0처리
                                                  + "WHERE empno = ?";
        try {
             jdbcUtil.setSqlAndParameters(updateQuery1, new Object[] { managerNo, deptNo });
             jdbcUtil.executeUpdate();
             
             jdbcUtil.setSqlAndParameters(updateQuery2, new Object[] { commission, managerNo });
             jdbcUtil.executeUpdate();
            
        } catch (SQLException ex) {
               ex.printStackTrace();
        } catch (Exception e) {
               e.printStackTrace();
          } finally {              
               jdbcUtil.commit();
             jdbcUtil.close();
        }
    }
    
    public Employee findEmpInfo(int empNo) {
        // printEmpInfo()를 변형
         // Employee 객체를 생성하고 검색 결과를 저장해서 return함
         String query = "SELECT E.ename, E.job, E.sal, NVL(E.comm, 0) AS comm, D.dname "
                + "FROM EMP0989 E, DEPT0989 D "
                + "WHERE E.deptNo = D.deptNo AND E.empNo = ? ";
  
            ResultSet rs = null;
            Employee emp = null;
            
            try {
                 jdbcUtil.setSqlAndParameters(query, new Object[] { empNo });
                 rs = jdbcUtil.executeQuery();
                   
                if (rs.next()) {
                    String ename = rs.getString("ename");
                    String job = rs.getString("job");
                    double sal = rs.getDouble("sal");
                    double comm = rs.getDouble("comm");
                    String deptName = rs.getString("dname");
                    
                    emp = new Employee(ename, job, sal, comm, deptName);
                    }
            } catch (SQLException ex) {
                   ex.printStackTrace();
            } finally {              
                 jdbcUtil.close();
            } 
        return emp;
    }
} 

