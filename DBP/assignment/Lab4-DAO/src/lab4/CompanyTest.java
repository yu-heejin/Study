package lab4;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CompanyTest {

	private static CompanyDao cm = new CompanyDao();
	
	public static void main(String[] args) {
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		
		Department dept = cm.findDeptInfo(deptName);	
		
		// dept 객체의 필드 값 출력 ...
		
	    // List<Employee> empList = cm.findEmployeesInDept( /* 부서번호 */);  
	    
		// empList에 포함된 모든 객체들의 필드 값 출력 ...
		// empList에 포함된 각 emp 객체를 접근하기 위해 empList로부터 Iterator<Employee> 객체를 구해서 활용함
		
		System.out.print("새 관리자 사번과 관리자 보직수당을 입력하시오: ");
		int managerNo  = scanner.nextInt();
		double commission = scanner.nextDouble();
		
	    // cm.replaceManagerOfDept( /* 부서번호, 관리자사번, 보직수당 */); 
	    
	    // Employee manager = cm.getEmpInfo( /* 관리자사번 */);   
	    
		// manager 객체의 필드 값 출력 ...
	    // System.out.println(manager);
	    
		scanner.close();
	}
}
