package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.MemberBean;
import util.DBmanager;

public class MemberDAOImpl implements CommonDAO {
 
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    //String sql = "";
	    MemberBean bean = new MemberBean();
	    /*
	     * 지금 보시는 내용이 싱글톤 + DBCP 정석입니다. 
	     다만, 이것만 해서는 단위별 서버 실행에서 
	     DB접속이 안됩니다. 그래서
	     * DBmanager 를 만들었고 당분간은 DBmanager 를 
	     사용하다가 프로젝트가 완성되면 철거하는 방식으로 하겠습니다.
	     */
	    private static MemberDAOImpl memberDAO = new MemberDAOImpl();
	 
	    private MemberDAOImpl() {
	        // 단위 테스트가 끝나고 프로젝트가 완성되면 걷어 낼 부분
	        conn = DBmanager.getConnection();
	    }
	 
	    public static MemberDAOImpl getInstance() {
	        return memberDAO;
	    }
	 
	    // 현재는 작동하지 않지만 위 DBmanager 를 걷어내는 순간
	    // 작동함. 미리 설정함.
	    public Connection getConnection() throws Exception {
	        Connection conn = null;
	        Context initContext = new InitialContext();
	        Context envContext = (Context) initContext.lookup("java:/comp/env");
	        DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
	        conn = ds.getConnection();
	        return conn;
	    }
	    public int join(MemberBean bean){
	        int result = 0;
	        String sql 
	            = "insert into member(MEMBERID,PASSWORD,NAME,EMAIL,AGE)"
	                +" values( ? , ? , ? , ? , ? )";
	        try{
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, bean.getId());
	            pstmt.setString(2, bean.getPassword());
	            pstmt.setString(3, bean.getName());
	            pstmt.setString(4, bean.getEmail());
	            pstmt.setString(5, bean.getAge());
	            result = pstmt.executeUpdate();
	        }catch(Exception ex){
	            ex.printStackTrace();
	            System.out.println("MemberDAO 에서 에러가 발생 !!");
	        }
	        return result;
	    }
	    
	    public List<MemberBean> getList() {
	        List<MemberBean> list = new ArrayList<MemberBean>();
	        String sql = "select * from member";
	        try {
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                bean.setId(rs.getString("MEMBERID"));
	                bean.setAge(rs.getString("AGE"));
	                bean.setPassword(rs.getString("PASSWORD"));
	                bean.setName(rs.getString("NAME"));
	                bean.setEmail(rs.getString("EMAIL"));
	 
	                list.add(bean);
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	 
	            try {
	                rs.close();
	                stmt.close();
	                conn.close();
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	 
	        }
	        return list;
	 
	    }
	   // 여기서 부터 07-02 일 인터페이스로 받으는 메소드들..
	    

		@Override
		public int insert(Object obj) {
			   int result = 0;
		        String sql 
		            = "insert into member(MEMBERID,PASSWORD,NAME,EMAIL,AGE)"
		                +" values( ? , ? , ? , ? , ? )";
		        try{
		        	/*
		        	 * SQL 문이 insert 라면 물음표가 많을 것입니다..
		        	 * 이것을 리터럴 타입으로 만들려면 , 작업이 많을 것 같다.. (라면...)
		        	 * values("+a+") 이걸 수십번 해야하지 않습니까!! 그래서 
		        	 * preparestate 가 없을때에는 일일히 인설트 해야했엇어야했다..
		        	 * 그런데 pstmt 로 이용한다면 다중값을 입력할수가 있다..
		        	 *  stmt = conn.createStatement();
		        	 *     pstmt = conn.prepareStatement(sql);
		        	 * 
		        	 * 
		        	 */
		        	
		            pstmt = conn.prepareStatement(sql);
		            
		            pstmt.setString(1, bean.getId());
		            pstmt.setString(2, bean.getPassword());
		            pstmt.setString(3, bean.getName());
		            pstmt.setString(4, bean.getEmail());
		            pstmt.setString(5, bean.getAge());
		            result = pstmt.executeUpdate();
		        }catch(Exception ex){
		            ex.printStackTrace();
		            System.out.println("MemberDAO 에서 에러가 발생 !!");
		        }
		        return result;
		}

		@Override
		public int count() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getElementById(String Id) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Object> getElementsByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Object> list() {
			 List<Object> list = new ArrayList<Object>();
		        try {
		            stmt = conn.createStatement();
		            String sql = "select * from member";
		            rs = stmt.executeQuery(sql);
		            while (rs.next()) {
		                bean.setId(rs.getString("MEMBERID"));
		                bean.setAge(rs.getString("AGE"));
		                bean.setPassword(rs.getString("PASSWORD"));
		                bean.setName(rs.getString("NAME"));
		                bean.setEmail(rs.getString("EMAIL"));
		 
		                list.add(bean);
		            }
		        } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } finally {
		 
		            try {
		                rs.close();
		                stmt.close();
		                conn.close();
		            } catch (SQLException e) {
		                // TODO Auto-generated catch block
		                e.printStackTrace();
		            }
		 
		        }
		        return list;
		}

		@Override
		public int update(Object obj) {
			
			
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int delete(String id) {
			// TODO Auto-generated method stub
			return 0;
		}
	 
	 /*   public int join(MemberBean bean2) {
	        int result = 0;
	        sql = "insert into member (MEMBERID, PASSWORD, NAME, EMAIL, AGE)"+ 
	                "values ('kang','kang','강감찬','kang@naver.com','20')";
	        return result;
	    }*/
}
