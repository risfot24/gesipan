package dao;

import java.util.List;


//  C R U D 의 성질을 체크 하면서 하겟다. 인터페이스에 관련된
public interface CommonDAO {
	/* C 의성질
	 * 추가 CREATE (데이터 값넣을)
	 * 
	 */
	public int insert(Object obj);
	/* 
	 * 전체 요소의 갯수
	 */
	public int count();
	/* R의 성질
	 * ID 로 중복값 없이 추출
	 */
	public Object getElementById(String id);
	/*  R의 성질
	 * Name 으로 중복값 허용하며 추출
	 */
	public List<Object> getElementsByName(String name);
	/* R의 성질
	 * 전체 목록
	 */
	public List<Object> list();
	//수정
	public int update(Object obj);
	//삭제
    public int delete(String id);	
	

}
