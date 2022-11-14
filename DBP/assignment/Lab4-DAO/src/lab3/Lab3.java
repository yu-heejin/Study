package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lab3 {

	public Lab3() {	// 생성
		// JDBC 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}	
	}
	
	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@dblab.dongduk.ac.kr:1521:orcl";		
		String user = "dbpro";
		String passwd = "dbpro";

		// DBMS와의 연결 생성
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}	 
		return conn;
	}
	
	public static int printDeptInfo(String deptName) {
		String query = "SELECT deptno, manager, count(empno) AS numOfEmps "
				+ "FROM DEPT0989 JOIN EMP0989 USING (deptno) "
				+ "WHERE dname = ? "
				+ "GROUP BY deptno, manager";
		
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		int deptNo = 0;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setString(1, deptName);
			rs = pStmt.executeQuery();
			
			System.out.println("<부서정보>");
			if (rs.next()) {
				deptNo = rs.getInt("deptNo");
				int managerNo = rs.getInt("manager");
				int numOfEmps = rs.getInt("numOfEmps");
				
				System.out.println("부서번호: " + deptNo);
				System.out.println("부서명: " + deptName);
				System.out.println("관리자사번: " + managerNo);
				System.out.println("사원수: " + numOfEmps);
				System.out.println();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		// �ڿ� �ݳ�
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}	
		return deptNo;
	}
	
	public static void printEmployeesInDept(int deptNo) {
		String query = "SELECT empno, ename, job, sal, comm "
				+ "FROM DEPT0989 JOIN EMP0989 USING (deptno) "
				+ "WHERE deptno = ? ";
		
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, deptNo);
			rs = pStmt.executeQuery();
			
			System.out.println("사번		이름		직무		");
			System.out.println("-----------------------------------------");
			while (rs.next()) {
				int empNo = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				
				System.out.printf("%d\t\t%s,\t\t%s \n", empNo, ename, job);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
	
	public static void replaceManagerOfDept(int deptNo, int mgrNo, double mgrComm) {
		String updateQuery1 = "UPDATE DEPT0989 SET manager = ? WHERE deptNo = ?";
		String updateQuery2 = "UPDATE EMP0989 SET comm = comm + ? "   //nvl 함수를 이용해 comm이 null이면 0처리
								+ "WHERE empno = ?";
		
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = conn.prepareStatement(updateQuery1);
			pStmt.setInt(1, mgrNo);
			pStmt.setInt(2, deptNo);
			pStmt.executeUpdate();
			pStmt.close();
			
			pStmt = conn.prepareStatement(updateQuery2);
			pStmt.setDouble(1, mgrComm);   // 해당 파라미터 2개 채워보기
			pStmt.setInt(2, mgrNo);
			pStmt.executeUpdate();
			
			System.out.println("사번		이름		직무		");
			System.out.println("-----------------------------------------");
			while (rs.next()) {
				int empNo = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				
				System.out.printf("%d\t\t%s,\t\t%s \n", empNo, ename, job);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		
			if (rs != null) 
				try { 
					rs.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (pStmt != null) 
				try { 
					pStmt.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null) 
				try { 
					conn.close(); 
				} catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
	
	public static void printEmpInfo(int empNo) {
		// 프린트하는 메소드 작성
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);	
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		int deptNo;
		
		// printDeptInfo 호출
		deptNo = printDeptInfo(deptName);
		
		// printEmployeesInDept 호출
		printEmployeesInDept(deptNo);

		System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
			
		// replaceManagerOfDept 호출
		replaceManagerOfDept(deptNo, managerNo, commission);

		System.out.println("새 관리자 정보: ");

		// printEmpInfo 호출	
		
		scanner.close();
	}
}
