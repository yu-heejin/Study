package lab4;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

public class CompanyDao {
	private JDBCUtil jdbcUtil = null;	// JDBCUtil 참조 변수 선언
		
	public CompanyDao() {	// 생성자 
		jdbcUtil = new JDBCUtil();		// JDBCUtil 객체 생성 및 활용
		
/*	JDBCUtil 이용 시 불필요
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
*/
	}

/*	JDBCUtil 이용 시 불필요
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
    	return null;
    } 

    public List<Employee> findEmployeesInDept(int deptNo) {
    	// printEmployeesInDept()를 변형
    	// ArrayList<Employee> 객체를 생성하고, 검색된 각 사원 정보에 대해 Employee 객체를 생성 및 저장하고, 그 객체를 ArrayList에 추가함
    	return null;
    }

    public void replaceManagerOfDept(int deptNo, int managerNo, double commission) {
        // 기존과 동일
    }
    
    public Employee findEmpInfo(int empNo) {
        // printEmpInfo()를 변형
    	// Employee 객체를 생성하고 검색 결과를 저장해서 return함
        return null;
    }
} 

