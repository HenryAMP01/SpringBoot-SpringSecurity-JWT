package com.study.security.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class AuthorityDTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private Long id;
    private String authorityCode;
    private String name;

    public AuthorityDTO() {
    }

    public AuthorityDTO(Long id, String authorityCode, String name) {
        this.id = id;
        this.authorityCode = authorityCode;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityCode() {
        return this.authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthorityDTO)) {
            return false;
        }
        AuthorityDTO authorityDTO = (AuthorityDTO) o;
        return Objects.equals(id, authorityDTO.id) && Objects.equals(authorityCode, authorityDTO.authorityCode) && Objects.equals(name, authorityDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorityCode, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", authorityCode='" + getAuthorityCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
