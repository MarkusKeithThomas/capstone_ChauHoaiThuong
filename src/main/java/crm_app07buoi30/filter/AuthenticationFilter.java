package crm_app07buoi30.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "authenFilter",urlPatterns = {"/login"})
public class AuthenticationFilter implements Filter {
	
		@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
				throws IOException, ServletException {
			System.out.println("Filter actived");
			chain.doFilter(request, response);			
		}
}
