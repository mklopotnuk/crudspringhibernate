package testgroup.crud_spring_hibernate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private MySuccessHandler mySuccessHandler;
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(MySuccessHandler mySuccessHandler, UserDetailsService userDetailsService) {
        this.mySuccessHandler = mySuccessHandler;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/add").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("USER,ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(mySuccessHandler)
                .and()
                .logout()
                .and().csrf().disable();

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
