package za.co.bigsim.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.bigsim.domain.User;
import za.co.bigsim.payload.JWTLoginSucessResponse;
import za.co.bigsim.payload.LoginRequest;
import za.co.bigsim.security.JwtTokenProvider;
import za.co.bigsim.services.MapValidationErrorService;
import za.co.bigsim.services.UserService;
import za.co.bigsim.validator.UserValidator;

import static za.co.bigsim.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {
 
	@Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
    	  ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
          if(errorMap != null)return errorMap;
          
          Authentication auth = authenticationManager.authenticate(
        		  new UsernamePasswordAuthenticationToken(
        				  loginRequest.getUsername(), 
        				  loginRequest.getPassword()
        				  )
        		  );
          
          SecurityContextHolder.getContext().setAuthentication(auth);
          
          String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(auth);
          
          return ResponseEntity.ok(new JWTLoginSucessResponse(true, jwt));
    }
}
