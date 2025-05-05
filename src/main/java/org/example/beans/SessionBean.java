package org.example.beans;

import org.example.InvalidParameterException;

public class SessionBean {
    private String sid;
    private String role;
    private String username;

    public SessionBean(String sid, String role) {
        if (isSidValid(sid) && isRoleValid(role)) {
            this.sid = sid;
            this.role = role;
        } else {
            throw new InvalidParameterException("Invalid Parameters");
        }
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null) {
            this.username = username;
        }
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        if (isSidValid(sid)) {
            this.sid = sid;
        }
    }

    private boolean isSidValid(String sid) {
        return (sid != null && !sid.isBlank());
    }

    private boolean isRoleValid(String role) {
        return (role != null && !role.isBlank());
    }
}
