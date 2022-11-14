package persistence.dao;

import java.util.List;

import service.dto.DeptDTO;

// 학과 정보를 관리하는 DAO
public interface DeptDAO {
	public List<DeptDTO> getDeptList();		// 모든 학과 정보 리스트 검색
	public int insertDept(DeptDTO dept);	// 학과 정보 삽입
	public int updateDept(DeptDTO dept);	// 학과 정보 수정
	public int deleteDept(int dCode);		// 학과 정보 삭제
	public DeptDTO getDeptByName(String dName);		// 학과 이름으로 학과 정보 검색
	public DeptDTO getDeptByCode(String dCode);		// 학과 코드로 학과 정보 검색
}
