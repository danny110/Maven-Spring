package cn.live.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AuthenticationFilter
 * @Description: TODO 登陆过滤器
 * @author FOAMVALUE FOAMVALUE@LIVE.CN
 * @date 2014年6月22日 上午7:31:25
 *
 */
public class AuthenticationFilter implements Filter {
	
	/**
	 * @Fields _LOGINCODE : 用户帐号标识
	 */
	public static final String _LOGINCODE = "_LOGINCODE";
	
	/* (non-Javadoc)
	 * <p>Title: init</p> 
	 * <p>Description: </p> 
	 * @param filterConfig
	 * @throws ServletException 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig) 
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * <p>Title: doFilter</p> 
	 * <p>Description: </p> 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain) 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		boolean isLoginAction = httpServletRequest.getRequestURI().indexOf("/index") != -1
				|| httpServletRequest.getRequestURI().indexOf("/login") != -1
				|| httpServletRequest.getRequestURI().indexOf("/logout") != -1
				|| httpServletRequest.getRequestURI().indexOf("/resources") != -1;
		boolean isAdminFun = httpServletRequest.getRequestURI().indexOf("/admin/") != -1;
		String logincode = (String) httpServletRequest.getSession().getAttribute(_LOGINCODE);
		if (!isLoginAction && isAdminFun && logincode == null) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin/index");
		}
		chain.doFilter(httpServletRequest, response);
	}

	/* (non-Javadoc)
	 * <p>Title: destroy</p> 
	 * <p>Description: </p>  
	 * @see javax.servlet.Filter#destroy() 
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
