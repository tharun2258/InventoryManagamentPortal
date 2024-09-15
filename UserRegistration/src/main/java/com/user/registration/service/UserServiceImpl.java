package com.user.registration.service;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.registration.entity.Role;
import com.user.registration.entity.User;
import com.user.registration.jwt.JwtService;
import com.user.registration.repository.RoleRepository;
import com.user.registration.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private JwtService jwtservice;
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;

	
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
//
//    private static volatile boolean initialized = false;
//    
//    public void init() {
//        if (!initialized) {
//        	initRoleAndUser();
//            initialized = true;
//        }
//    }
    
    private static final String FLAG_FILE_PATH = "./initialization.flag";
    
	public void initRoleAndUser() throws java.io.IOException {


		        File flagFile = new File(FLAG_FILE_PATH);
		        if (!flagFile.exists()) {
		            // Perform your initialization logic
		        	
		        	Role adminRole = new Role();
		    		adminRole.setRolename("Admin");
		    		roleRepo.save(adminRole);

		    		Role userRole = new Role();
		    		userRole.setRolename("User");
		    		roleRepo.save(userRole);
		    		
		    		    
		    		    User adminUser = new User();
		    	        adminUser.setUsername("tharun");
		    	        adminUser.setPassword(passwordEncoder.encode("Tharun@123"));
		    	        adminUser.setConfirmpassword(passwordEncoder.encode("Tharun@123"));
		    	        adminUser.setEmailid("tharun@gmail.com");
		    	        adminUser.setMobilenum(8978988988l);
//		    	        adminUser.setUserRole("Admin");
		    	        Set<Role> adminRoles = new HashSet<>();
		    	        adminRoles.add(adminRole);
		    	        adminUser.setRole(adminRoles);
		    	        userRepo.save(adminUser);

		            // Create the flag file
		            try {
		                flagFile.createNewFile();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		
	        
	

	
    boolean flag;
	@Override
	public User createUser(User userinfo) {
	    List<User> userlist = userRepo.findAll();
		if(userlist != null) {
			for(User user: userlist) {
				if(user.getUsername().equals(userinfo.getUsername()))
					flag = true;
//				user.setUserRole("User");
				
				
			}
			if(flag) {
				System.out.println("username exists!!!");
				return null;
				
			}else {
				Role role = roleRepo.findByRolename("User");
				userinfo.setUsername(userinfo.getUsername());
				userinfo.setEmailid(userinfo.getEmailid());
				userinfo.setMobilenum(userinfo.getMobilenum());
				userinfo.setPassword(passwordEncoder.encode(userinfo.getPassword()));
				userinfo.setConfirmpassword(passwordEncoder.encode(userinfo.getPassword()));
				Set<Role> userRoles = new HashSet<>();
				userRoles.add(role);
				userinfo.setRole(userRoles);
				return userRepo.save(userinfo);
			}
		}
		return userinfo;
		
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getByUserId(int userid) {
		User user = userRepo.findById(userid).get();
		return user;
		
	}

	@Override
	public void deleteUserById(int userid) {
       userRepo.deleteById(userid);		
	}

	@Override
	public User updateUser(User user, int userid) {
		User user2 = userRepo.findById(userid).get();
		if(user2!= null) {
		user2.setUsername(user.getUsername());
		user2.setPassword(passwordEncoder.encode(user.getPassword()));
		user2.setConfirmpassword(passwordEncoder.encode(user.getConfirmpassword()));
		user2.setEmailid(user.getEmailid());
		user2.setMobilenum(user.getMobilenum());
		return userRepo.save(user2);
		}
		return null;
		
	}

	
		
	
	
	
//	public String generateToken(String  newusername, String newpassword) throws ServletException, Exception {
//		
//
//		if (newusername == null || newpassword == null) {
//			throw new ServletException("Please enter valid username and password");
//		}
//
//		else {
//			
//		String loginUser = loginUser(newusername, newpassword);
//
//			String jwtToken = Jwts.builder().setSubject(newusername).setIssuedAt(new Date())
//					.setExpiration(new Date(System.currentTimeMillis() + 3000000))
//					.signWith(SignatureAlgorithm.HS256, "secret key").compact();
//			
//
//		
//
//		return loginUser;
//		}
//	}

	@Override
	public Map<String, Object> loginUser(String username, String password) {
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		if(authenticate.isAuthenticated()) {
			String token= jwtservice.generateToken(username);
			 UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

	            // Create a map to hold the response data
	            Map<String, Object> response = new HashMap<>();
	            response.put("token", token);
	            response.put("userDetails", userDetails);
	            response.put("username",userDetails.getUsername());
	            
	            User user = userRepo.findByUsername(username);
	            
	            response.put("userid",user.getUserid());
	            response.put("role", userDetails.getAuthorities().toString());
	            return response;
		}
		return null;
		}

	@Override
	public User getByUserName(String username) {
		User user = userRepo.findByUsername(username);
		return user;
	}

	@Override
	public User forgotPassword(int userid, User user) {
		User user2 = userRepo.findById(userid).get();
		if (user2 != null) {
			user2.setPassword(passwordEncoder.encode(user.getPassword()));
			user2.setConfirmpassword(passwordEncoder.encode(user.getConfirmpassword()));
			
		}
		return null;
	}

	@Override
	public User getByUsernameAndEmailId(String username, String emailId) {
		User user = userRepo.findByUsernameAndEmailid(username, emailId);
		if (user !=  null) {
		return user;
		}
		else {
			return null;
		}
	}
	
	
}
