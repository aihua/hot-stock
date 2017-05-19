/**
 * 
 */
package assgn.stock.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import assgn.stock.utils.Role;

/**
 * @author Jogireddy Kotam
 *
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1818812841386963292L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = false)
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
