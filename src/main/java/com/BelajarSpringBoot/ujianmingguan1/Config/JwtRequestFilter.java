package com.BelajarSpringBoot.ujianmingguan1.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.BelajarSpringBoot.ujianmingguan1.Service.JwtPenumpangDetailService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	JwtPenumpangDetailService jwtdetailservice;
	
	@Autowired
	JwtTokenutil jwttokenutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		// bearer alamat token //Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiSnVhcmEgQ29kaW5nIn0.nFgKqog3l5cnZ4gB7V8FG9IriHrt67hq-JhClqnfkrU

		String jwtToken = null;
		String username = null;
		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearir")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwttokenutil.getUsernameFromToken(jwtToken);
			}catch (IllegalArgumentException e) {
				System.out.println("Ga bisa dapetin jwt token"); //unable to get JWt token
			}catch(ExpiredJwtException e) {
				System.out.println("JWT TOKEN anda expired , tolong generate lagi"); // jwt token has expired
			}
		}else {
			logger.warn("jwt anda ga mulai dari bearer"); // jwt token does not begin with bearer string
		}
		
		// ketika mendapatlan token , kita validasi di code bawah ini
	
		if(username != null && SecurityContextHolder.getContext().getAuthentication()== null) {
			UserDetails userDetails = jwtdetailservice.loadUserByUsername(username);
			
			// jika token valid maka kita akan melakukan config
			if(jwttokenutil.validateToken(jwtToken,userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(
				userDetails,null, userDetails.getAuthorities());
				
				//setelah kita spesifikasikan authentikasi kita
				//kita memasukanya kedalam konfigurasi context atau security context
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
		}
		filterChain.doFilter(request, response);
	}

	
}
