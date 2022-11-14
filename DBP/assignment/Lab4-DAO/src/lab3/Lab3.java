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
		return deptNo;
	}
	
	public static void printEmployeesInDept(int deptNo) {
		String query = "SELECT empno, ename, job, sal, NVL(comm, 0) AS comm "
				+ "FROM DEPT0989 JOIN EMP0989 USING (deptno) "
				+ "WHERE deptno = ? ";
		
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, deptNo);
			rs = pStmt.executeQuery();
			
			System.out.println("사번	이름	직무	급여	수당");
			System.out.println("------------------------------------------------------");
			while (rs.next()) {
				int empNo = rs.getInt("empno");
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				
				System.out.printf("%d\t%s\t%s\t%.2f\t%.2f", empNo, ename, job, sal, comm);
				System.out.println();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {		
			System.out.println();
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
		String updateQuery2 = "UPDATE EMP0989 SET comm = NVL(comm, 0) + ? "   //nvl 함수를 이용해 comm이 null이면 0처리
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
		String query = "SELECT E.ename, E.job, E.sal, NVL(E.comm, 0) AS comm, D.dname "
				+ "FROM EMP0989 E, DEPT0989 D "
				+ "WHERE E.deptNo = D.deptNo AND E.empNo = ? ";
		
		Connection conn = getConnection();
		PreparedStatement pStmt = null;
		ResultSet rs = null;
		
		try {
			pStmt = conn.prepareStatement(query);
			pStmt.setInt(1, empNo);
			rs = pStmt.executeQuery();
			
			if (rs.next()) {
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				double sal = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				String deptName = rs.getString("dname");
				
				System.out.printf("%s\t%s\t%.2f\t%.2f\t%s", ename, job, sal, comm, deptName);
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
		printEmpInfo(managerNo);
		
		scanner.close();
	}
}
