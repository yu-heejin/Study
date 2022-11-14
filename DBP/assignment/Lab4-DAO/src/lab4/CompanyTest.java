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
          
          Department dept = cm.findDeptInfo(deptName);     // 값이 null 일수도있음 -> 예외처리 필요
          
          if (dept != null) {
               // dept 객체의 필드 값 출력 ...
               // Lab3 출력문 참고  -> getter 사용
               System.out.println("부서번호: " + dept.getDeptNo());
               System.out.println("부서명: " + deptName);
               System.out.println("관리자사번: " + dept.getMgrNo());
               System.out.println("사원수: " + dept.getNumOfEmps());
               System.out.println();
          } else {
               System.out.println("부서를 찾지 못했습니다.");
               return;
          }
          
         List<Employee> empList = cm.findEmployeesInDept(dept.getDeptNo());  
         System.out.println("사번       이름       직무       급여       수당");
        System.out.println("------------------------------------------------------");
        for (int i = 0; i < empList.size(); i++) {
             Employee emp = empList.get(i);
             int empNo = emp.getEmpNo();
            String ename = emp.getEname();
            String job = emp.getJob();
            double sal = emp.getSal();
            double comm = emp.getComm();
            
            System.out.printf("%d\t%s\t%s\t%.2f\t%.2f", empNo, ename, job, sal, comm);
            System.out.println();
        }
        System.out.println();
          // empList에 포함된 모든 객체들의 필드 값 출력 ...
          // empList에 포함된 각 emp 객체를 접근하기 위해 empList로부터 Iterator<Employee> 객체를 구해서 활용함
          
          System.out.print("새 관리자 사번과 관리자 보직수당을 입력하시오: ");
          int managerNo  = scanner.nextInt();
          double commission = scanner.nextDouble();
          
         cm.replaceManagerOfDept(dept.getDeptNo(), managerNo, commission); 
         
         Employee manager = cm.findEmpInfo(managerNo);   
         
         if (manager != null) {
              // manager 객체의 필드 값 출력 ...
              System.out.println("새 관리자 정보: ");
              System.out.println(manager);
         } else {
               System.out.println("관리자를 찾지 못했습니다.");
          }
         
         
          scanner.close();
     }
}
