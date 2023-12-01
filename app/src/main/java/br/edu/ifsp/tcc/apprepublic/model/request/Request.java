package br.edu.ifsp.tcc.apprepublic.model.request;

import java.util.Objects;

public class Request {
    private Long id;
    private Long userProp;
    private Long homeId;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserProp() {
        return userProp;
    }

    public void setUserProp(Long userProp) {
        this.userProp = userProp;
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long homeId) {
        this.homeId = homeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Request other = (Request) obj;
        return Objects.equals(id, other.id);
    }

    public Request() {
    }

    public Request(Long id, Long userProp, Long homeId, Long userId) {
        this.id = id;
        this.userProp = userProp;
        this.homeId = homeId;
        this.userId = userId;
    }
}
