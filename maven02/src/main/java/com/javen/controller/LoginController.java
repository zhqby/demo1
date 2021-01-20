package com.javen.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.javan.util.IsNull;
import com.javan.util.ObjtoLayJson;
import com.javen.model.Login;
import com.javen.service.ILoginService;

@Controller  //返回指定页面  ajax 不能接受到页面的返回 ，所以说
@RequestMapping("/login") 
public class LoginController {  
	
	private static Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@Resource  
	private ILoginService loginService;     
    
    // /user/test?id=1
    @RequestMapping(value="/test", method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        return "back"; 
    }
    
  //管理员登录
    @ResponseBody
    @RequestMapping(value="/selectByManName", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String selectByManName(HttpServletRequest request) throws Exception{  
    	//String  idString = request.getParameter("id");
    	//Integer idInteger = Integer.valueOf(idString);
    	String userName = request.getParameter("userName");
    	String password = request.getParameter("password");
    	Login login = loginService.selectByManName(userName);
    	//System.out.println(login.toString());
    	
    	String[] s1= {userName,password};
    	boolean bool=IsNull.isNull(s1);
    	if(bool==false) {
    		String data = "[{\"status\":1}, {\"message\": \"失败\" }, {\"count\": 1000},"
        			+ "{\"rows\":{\"item\":[{\"userName\":\"null\"},{\"password\":\"null\"}]}}]"; 
        	//System.out.println(data);
            return data;
    	}
    	
    	if(password.equals(login.getPassword())) {
    		HttpSession session=request.getSession();
    		session.setAttribute("userName",userName);
        	String[] colums= {"id","userName","password"};
        	String data=ObjtoLayJson.toJson(login, colums);
        	//System.out.println("11111111111");
            return data;
    	}
    	String data = "[{\"status\":2}, {\"message\": \"失败\" }, {\"count\": 1000},"
    			+ "{\"rows\":{\"item\":[{\"userName\":\"null\"},{\"password\":\"null\"}]}}]"; 
    	//System.out.println(data);
        return data;
         
    }
    
  //用户登录
    @ResponseBody
    @RequestMapping(value="/selectByUserName", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String selectByUserName(HttpServletRequest request) throws Exception{  
    	//String  idString = request.getParameter("id");
    	//Integer idInteger = Integer.valueOf(idString);
    	String userName = request.getParameter("userName");
    	String password = request.getParameter("password");
    	
    	String[] s1= {userName,password};
    	boolean bool=IsNull.isNull(s1);
    	if(bool==false) {
    		String data="{\"data\":2}";
            return data;
    	}
    		
    	Login login = loginService.selectByUserName(userName);
    	//System.out.println(login.toString());
    	if(password.equals(login.getPassword())) {
    		HttpSession session=request.getSession();
    		session.setAttribute("userName",userName);
        	String[] colums= {"id","userName","password"};
        	String data=ObjtoLayJson.toJson(login, colums);
        	//System.out.println("11111111111");
        	String data1="{\"data\":1}";
            return data1;
    	}
    	String data = "{\"data\":0}"; 
    	System.out.println(data);
        return data; 
     }
    

  
    
  //检查管理员登录
    @ResponseBody
    @RequestMapping(value="/checkState", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String checkState(HttpServletRequest request,Model model) throws Exception{  
    	HttpSession session=request.getSession();
    	//System.out.println(session.getAttribute("userName"));
    	if(session.getAttribute("userName")!=null) {
			return "{\"message\":66}";
		}else {
			return "{\"message\":99}";
		}
    }
    
    
  //检查用户登录
    @ResponseBody
    @RequestMapping(value="/checkState1", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String checkState1(HttpServletRequest request,Model model) throws Exception{  
    	HttpSession session=request.getSession();
    	//System.out.println(session.getAttribute("userName"));
    	if(session.getAttribute("userName")!=null) {
			return "{\"message\":66}";
		}else {
			return "{\"message\":99}";
		}
    }
   
    
    
    //没用
    @ResponseBody
    @RequestMapping(value="/deleteByPrimaryKey", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String deleteByPrimaryKey(HttpServletRequest request) {
    	String  idString = request.getParameter("id");
    	Integer idInteger = Integer.valueOf(idString);
    	loginService.deleteByPrimaryKey(idInteger);
    	String data = "{\"data\":\"返回成功\"}"; 
        return data; 
    }
    
    @ResponseBody
    @RequestMapping(value="/insert", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String insert(HttpServletRequest request) {
    	//插入数据库
    	String usernameString = request.getParameter("username");
    	String passwordString = request.getParameter("password");
    	
    	Login login = new Login();
    	login.setPassword(passwordString);
    	login.setUserName(usernameString);
    	
    	loginService.insert(login);
    	
    	
    	//给前台返回的东西
    	String data = "{\"data\":\"返回成功\"}"; 
        return data; 
    }
    
    @ResponseBody
    @RequestMapping(value="/update", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String update(HttpServletRequest request) {
    	//插入数据库
    	String idString = request.getParameter("id");
    	String usernameString = request.getParameter("username");
    	String passwordString = request.getParameter("password");
    	Integer idInteger = Integer.valueOf(idString);
    	
    	Login login = new Login();
    	login.setId(idInteger);
    	login.setPassword(passwordString);
    	login.setUserName(usernameString);
    	
    	loginService.updateByPrimaryKey(login);
    	
    	
    	//给前台返回的东西
    	String data = "{\"data\":\"返回成功\"}"; 
        return data; 
    }
    
    //返回字符串
    @ResponseBody
    @RequestMapping(value="/selectAll", method=RequestMethod.GET,produces = "text/plain;charset=utf-8")  
    public String selectAll(HttpServletRequest request) throws Exception{  	
    	request.setCharacterEncoding("utf-8");  
    	String pageString = request.getParameter("page");
    	String limitString = request.getParameter("limit");
    	System.out.println(pageString + " "+limitString);
    	List<Login> logins = loginService.selectAll();
      	String[] colums = {"id","userName","password"};
    	String data = ObjtoLayJson.ListtoJson(logins, colums);
    	System.out.println(data);
        return data; 
    }
   
}  









