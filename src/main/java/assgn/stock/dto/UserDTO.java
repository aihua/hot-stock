/**
 * 
 */
package assgn.stock.dto;

import java.io.Serializable;

import assgn.stock.utils.Role;


/**
 * @author Jogireddy Kotam
 *
 */
public class UserDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4917780333508956364L;
    
    private Long id;
    
    private String username;
    
    private Role role;

    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getUsername() {
        return username;
    }

    
    public void setUsername(String username) {
        this.username = username;
    }

    
    public Role getRole() {
        return role;
    }

    
    public void setRole(Role role) {
        this.role = role;
    }
    
    

}
