package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
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
		
		return 0;
	}
	
	public static void printEmployeesInDept(int deptNo) {
		
	}
	
	public static void replaceManagerOfDept(int deptNo, int mgrNo, double mgrComm) {
	
	}
	
	public static void printEmpInfo(int empNo) {
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);	
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		int deptNo;
		
		// printDeptInfo 호출
		
		// printEmployeesInDept 호출

		System.out.print("새 관리자 사번과 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
			
		// replaceManagerOfDept 호출

		System.out.println("새 관리자 정보: ");

		// printEmpInfo 호출	
		
		scanner.close();
	}
}
