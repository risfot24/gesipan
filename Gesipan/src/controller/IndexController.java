package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexController
 */
@WebServlet({"/home/main.do","/bang/index.do","/dap/index.do"})
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);//메소드 리펙토링
	}

	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String path = request.getServletPath().substring(+1); //여기때문에 아래의 if문이 jquer
		 //이거는 path 다음경로까지 잡아 먹겠다는 겁니다..
		 
		 System.out.println("process : " +path);
		 String url = "";
		 
		 switch (path) {
		 //해당 챕터에 해당 index.html 경로로 가게 된다..
	
		case "home/main.do": url="/view/home/main.jsp"; break;
		case "bang/index.do": url="..."; break;
		case "dap/index.do": url="..."; break;
		
      
		
		
		default:
			break;
		}
		 if(path.substring(0,5).equals("jquer")){
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/jquery/"+url+"/index.jsp");
			 dispatcher.forward(request, response);
			 
		 }else{
			RequestDispatcher dispatcher =
		        request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			 
		 }
		 
	}
	

}
