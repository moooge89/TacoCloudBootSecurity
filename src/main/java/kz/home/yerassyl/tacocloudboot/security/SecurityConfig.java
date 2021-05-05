package kz.home.yerassyl.tacocloudboot.security;

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
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    // in-memory
//    @Override
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("buzz").password("infinity").authorities("ROLE_USER")
//                .and().withUser("woody").password("bullseye").authorities("ROLE_USER");
//    }

    // jdbc
//    @Override
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                    "select username, password, enabled from Users " +
//                    "where username = ?")
//                .authoritiesByUsernameQuery(
//                    "select username, authority from UserAuthorities " +
//                    "where username = ?"
//                )
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//                //.passwordEncoder(new BCryptPasswordEncoder());
//    }

    // LDAP
//    @Override
//    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .passwordCompare()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .passwordAttribute("passcode");
//    }

    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    // Custom user details service
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/design", "/orders")
//                .hasRole("ROLE_USER")
//                .antMatchers("/", "/**").permitAll();
//    }


    // This does the same action, but with access().
    // access gives more flexibility
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")

        .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)

        .and()
            .logout()
            .logoutSuccessUrl("/");
//        .and()
//            .csrf()
//                .disable();
    }

    // Example of flexibility of access().
    // Only users, who was role "ROLE_USER" are permitted to buy tacos with condition that today is Tuesday
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/design", "/orders")
//                .access("hasRole('ROLE_USER') && " +
//                        "T(java.util.Calendar).getInstance().get("+
//                        "T(java.util.Calendar).DAY_OF_WEEK) == " +
//                        "T(java.util.Calendar).TUESDAY")
//                .antMatchers("/", "/**").access("permitAll")
//        ;
//    }
}
