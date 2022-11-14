package persistence.dao;

import java.util.List;

import service.dto.ProfDTO;

// 교수 정보를 관리하는 DAO
public interface ProfDAO {
	public List<ProfDTO> getProfList();				// 모든 교수 정보 리스트 검색 
	public int insertProf(ProfDTO dept);			// 교수 정보 삽입
	public int updateProf(ProfDTO dept);			// 교수 정보 수정
	public int deleteProf(int pCode);				// 교수 정보 삭제
	public ProfDTO getProfByName(String name);		// 교수 이름으로 교수 정보 검색
	public ProfDTO getProfByCode(String pCode);		// 학과 코드로 교수 정보 검색
}
