/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class AccountDTO {
    private Integer accountId;
    private String username;
    private String password;
    private String role;
    private Date createAt;
    private Date updateAt;

    public AccountDTO() {
    }

    public AccountDTO(Integer accountId, String username, String password, String role) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountDTO(Integer accountId, String username, String password, String role, Date createAt, Date updateAt) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    
    
}
