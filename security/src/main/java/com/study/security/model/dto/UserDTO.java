package com.study.security.model.dto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class UserDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String username;
    private Set<AuthorityDTO> authorities;



    public UserDTO() {
    }

    public UserDTO(Long id, String username, Set<AuthorityDTO> authorities) {
        this.id = id;
        this.username = username;
        this.authorities = authorities;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AuthorityDTO> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserDTO)) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(username, userDTO.username) && Objects.equals(authorities, userDTO.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, authorities);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", authorities='" + getAuthorities() + "'" +
            "}";
    }
}
    
