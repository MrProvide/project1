package javau9.ca.db.abc.finalprojectalternativehf.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface SimpleUserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
