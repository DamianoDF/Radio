package it.failutti.damiano.presentation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.failutti.damiano.businessLogic.UtenteService;
import it.failutti.damiano.model.login.LoginRequest;
import it.failutti.damiano.model.login.LoginResponse;
import it.failutti.damiano.persistence.UtenteRepository;
import it.failutti.damiano.security.JwtUtils;
import it.failutti.damiano.security.RegistrazioneUtente;
import it.failutti.damiano.security.service.UserDetailsImpl;



@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UtenteRepository repoU;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UtenteService utenteService;
	
	@PostMapping("/registraUtente")
	public RegistrazioneUtente registraUtente(@RequestBody RegistrazioneUtente registrazioneUtente ) {
		
	utenteService.registraUtente(registrazioneUtente);
		
		return registrazioneUtente;
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new 
				UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		authentication.getAuthorities();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String>roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));
		
		
		
		
	
	}
	
	
	
	
	
	

}
