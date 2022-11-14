package persistence.dao;

import java.util.List;

import service.dto.DeptDTO;

// �а� ������ �����ϴ� DAO
public interface DeptDAO {
	public List<DeptDTO> getDeptList();		// ��� �а� ���� ����Ʈ �˻�
	public int insertDept(DeptDTO dept);	// �а� ���� ����
	public int updateDept(DeptDTO dept);	// �а� ���� ����
	public int deleteDept(int dCode);		// �а� ���� ����
	public DeptDTO getDeptByName(String dName);		// �а� �̸����� �а� ���� �˻�
	public DeptDTO getDeptByCode(String dCode);		// �а� �ڵ�� �а� ���� �˻�
}
