package com.study.security.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 3)
    @Column(nullable = false)
    private String password;

    @ManyToMany
    @NotNull
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name ="user_id"), inverseJoinColumns = @JoinColumn(name ="authority_id"))
    private Set<Authority> authorities;

    private Instant createAt;

    private Instant updateAt;

    public User() {
        this.createAt = Instant.now();
        this.updateAt = Instant.now();
    }

    public User(Long id, String username, String password, Set<Authority> authorities, Instant createAt, Instant updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.createAt = Instant.now();
        this.updateAt = Instant.now();
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Authority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Instant getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(authorities, user.authorities) && Objects.equals(createAt, user.createAt) && Objects.equals(updateAt, user.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorities, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", authorities='" + getAuthorities() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
    
}
