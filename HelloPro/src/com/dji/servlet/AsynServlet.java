package com.dji.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**   
 * @Title: AsynServlet.java 
 * @Package  
 * @Description: TODO
 * @author ynb  
 * @date 2014-7-24 下午5:07:00 
 * @version V1.0   
 */

/**
 * @author admin
 *
 */
@WebServlet(urlPatterns="/demo", asyncSupported=true)
public class AsynServlet extends HttpServlet {
	private static final long serialVersionUID = -8016328059808092454L;
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  	resp.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = resp.getWriter();
	        String str1="进入Servlet的时间：" + new Date() + ".";
	        out.write(str1);
	        System.out.println(str1);
	        out.flush();

	        //在子线程中执行业务调用，并由其负责输出响应，主线程退出
	        final AsyncContext ctx = req.startAsync();
	        ctx.setTimeout(200000);
	        new Work(ctx).start();
	        ctx.addListener(new AsynServletListener());
	        String str2="结束Servlet的时间：" + new Date() + ".";
	        out.println(str2);
	        out.flush();
	        System.out.println(str2);
	}
}

class Work extends Thread{
	private AsyncContext context;
	
	public Work(AsyncContext context){
		this.context = context;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(20000);//让线程休眠2s钟模拟超时操作
			PrintWriter wirter = context.getResponse().getWriter();			
			wirter.println("延迟输出");
			System.out.println("延迟输出");
			wirter.flush();
			context.complete();
		} catch (InterruptedException e) {
			
		} catch (IOException e) {
			
		}
	}
}
