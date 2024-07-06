package com.naveenrestaurant.application.Controller;

import com.naveenrestaurant.application.Config.JwtProvider;
import com.naveenrestaurant.application.Repository.CartRepository;
import com.naveenrestaurant.application.Repository.UserRepository;
import com.naveenrestaurant.application.Response.AuthResponse;
import com.naveenrestaurant.application.Service.CustomUserDetailsService;
import com.naveenrestaurant.application.model.Cart;
import com.naveenrestaurant.application.model.User;
import com.naveenrestaurant.application.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userrepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService custom;

    @Autowired
    private CartRepository cartrepo;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws  Exception{

        User isemailexist = userrepo.findByEmail(user.getEmail());
         if(isemailexist != null){
             throw new RuntimeException("Email already used with another account"+user.getEmail());
         }

         User createUser = new User();
         createUser.setEmail(user.getEmail());
         createUser.setFullname(user.getFullname());
         createUser.setRole(user.getRole());
         createUser.setPassword(passwordEncoder.encode(user.getPassword()));

         User saveUser = userrepo.save(createUser);

          Cart cart = new Cart();
          cart.setCustomer(saveUser);
          cartrepo.save(cart);

        Authentication authentication =new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Sucessfully");
        authResponse.setRole(saveUser.getRole());



        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public ResponseEntity<AuthResponse> loginUp(@RequestBody User user) {
        String email = user.getEmail();
        String userPassword = user.getPassword();

        Authentication authentication = authenticate(email, userPassword);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);  // assuming you have a method to generate JWT
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role =authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("login Sucess");

        authResponse.setRole(UserRole.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String email, String userPassword) {
        UserDetails userDetails = custom.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(userPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }
        return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities());
    }


}
