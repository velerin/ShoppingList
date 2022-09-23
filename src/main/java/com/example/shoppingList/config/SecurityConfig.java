package com.example.shoppingList.config;

import com.example.shoppingList.constants.Authorities;
import com.example.shoppingList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
     
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/register/*RegistrationForm").permitAll()
                .antMatchers("/products/*/**").hasRole(Authorities.USER.getShortName())
                .antMatchers("/shoppinglists/*/**").hasRole(Authorities.USER.getShortName())
                .antMatchers("/users/show*").hasAnyRole(Authorities.USER.getShortName(),Authorities.ADMIN.getShortName(),Authorities.MANAGER.getShortName())
                .antMatchers("/users/save*").hasRole( Authorities.USER.getShortName())
                .antMatchers("/{users|shoppinglists|products}/**").hasRole(Authorities.ADMIN.getShortName())
                .antMatchers("/{shoppinglists|products}/*/{show*|save}").hasRole(Authorities.MANAGER.getShortName())
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/").authenticated()
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =  new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }


}
