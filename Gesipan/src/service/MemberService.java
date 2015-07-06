package service;

import java.util.List;

import bean.MemberBean;

public interface MemberService {
	 /*INSERT : 회원가입*/
    public int join(MemberBean bean);
    /*count : 회원수*/
    /*getElementById : 회원의 상세정보*/
    public MemberBean memberDetail(String id);
    /*getElementByName : 검색어로 회원 검색*/
    public List<MemberBean> serchByKeyword(String keyword);
    /*list : 회원목록*/
    public List<Object> memberList();
    /*update : 회원정보 수정*/
    public int updateMember(MemberBean bean);
    /*delete : 회원탈퇴*/
    public int deleteMember(String id);   
    
     //  로그인     //과감하게 삭제한다. -> 다시 복구한다.
    public String login(String id, String password);
	
}
