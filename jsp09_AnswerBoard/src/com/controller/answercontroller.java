package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.answer.dao.dao;
import com.answer.dto.dto;
import com.my.dao.LoginBoardDao;
import com.my.dto.LoginBoardDto;

@WebServlet("/answer.do")
public class answercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public answercontroller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.printf("{%s}\n", command);

		dao dao = new dao();
		LoginBoardDao dao1 = new LoginBoardDao();
		HttpSession session = request.getSession();
		if (command.equals("list")) {
			List<dto> list = dao.selectAll();
			request.setAttribute("list", list);
			dispatch("boardlist.jsp", request, response);
		} else if (command.equals("detail")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));

			dto dto = dao.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("boarddetail.jsp", request, response);
		} else if (command.equals("writeform")) {
			response.sendRedirect("boardwrite.jsp");
		} else if (command.equals("boardwrite")) {
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			dto dto = new dto(writer, title, content);

			int res = dao.insert(dto);
			if (res > 0) {
				jsResponse("글 작성 성공", "answer.do?command=list", response);
			} else {
				dispatch("answer.do?command=writeform", request, response);
			}
		} else if (command.equals("update")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));

			dto dto = dao.selectOne(boardno);
			request.setAttribute("dto", dto);
			dispatch("boardupdate.jsp", request, response);
		} else if (command.equals("update_res")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			dto dto = new dto(boardno, title, content);

			int update_res = dao.update(dto);
			if (update_res > 0) {

				jsResponse("글 수정 성공", "answer.do?command=detail&boardno=" + boardno, response);
			} else {

				dispatch("boarddetail.jsp", request, response);
			}
		} else if (command.equals("delete")) {
			int boardno = Integer.parseInt(request.getParameter("boardno"));

			int delete_res = dao.delete(boardno);
			if (delete_res > 0) {
				jsResponse("글 삭제 성공", "answer.do?command=list", response);
			} else {
				dispatch("index.jsp", request, response);
			}
		
		}else if (command.equals("answerform")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			
			dto dto = dao.selectOne(parentboardno);
			request.setAttribute("parent", dto);
			dispatch("answerwrite.jsp", request, response);
		}else if(command.equals("answerwrite")) {
			int parentboardno = Integer.parseInt(request.getParameter("parentboardno"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			
			//update groupsq를 밀어내기 
			dto parent = dao.selectOne(parentboardno);
			
			int parentgroupno = parent.getGroupno();
			int parentgroupsq = parent.getGroupsq();
			int parenttitletab = parent.getTitletab();
			
			int updateRes= dao.updateAnswer(parentgroupno, parentgroupsq);
			
			if(updateRes >0) {
				System.out.println("순서변경 성공");
			}else {
				System.out.println("순서 변경 실패 or 변경 할 글 없음");
			}
			
			//insert
			dto dto = new dto(0,parentgroupno,parentgroupsq,parenttitletab,title,content,writer,null,null);
			
			int res= dao.insertAnswer(dto);
			if(res>0) {
				response.sendRedirect("answer.do?command=list");
			}else {
				response.sendRedirect("answer.?command=detail&");
			}
		}else if (command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");

			LoginBoardDto dto1 = dao1.Login(id, pw);

			if (dto1.getMyid() != null) {
				// session scope
				session.setAttribute("dto1", dto1);
				// session 담긴 객체가 살아있는 시간

				// default :30분 ,음수 : 무제한
					
				if (dto1.getMyrole().equals("ADMIN")) {
					dispatch("adminmain.jsp", request, response);
				} else if (dto1.getMyrole().equals("USER")) {

					dispatch("usermain.jsp", request, response);

				}
			} else {
				dispatch("index.jsp", request, response);
			}
		}else if(command.equals("logout")){
	        response.sendRedirect("index.jsp");
		}else if(command.equals("userinformation")){
	 		int myno = Integer.parseInt(request.getParameter("myno"));
			
	 		LoginBoardDto dto = dao1.selectOne(myno);
	 		request.setAttribute("dto1", dto);
			dispatch("userinformation.jsp", request, response);

	     }else if(command.equals("userinformation_res")) {
		 int myno = Integer.parseInt(request.getParameter("myno"));
		 
		 
		 LoginBoardDto dto = dao1.selectOne(myno);
		 request.setAttribute("dto1", dto);
	   	 dispatch("userinformation.jsp", request, response);

	     }
	
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url + "';"
				+ "</script>";

		PrintWriter out = response.getWriter();
		out.print(s);

	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		doGet(request, response);
	}

}
