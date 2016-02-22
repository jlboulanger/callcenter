package callcenter.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ContactInfo implements Serializable {

    private static final long serialVersionUID = -1670634250915466082L;
    private Integer id;
    private Integer clientId;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String code;
    private String phone;
    private Date createDt;
    private Date updateDt;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getClientId() {
        return clientId;
    }
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getCreateDt() {
        return createDt;
    }
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }
    public Date getUpdateDt() {
        return updateDt;
    }
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address1 == null) ? 0 : address1.hashCode());
        result = prime * result + ((address2 == null) ? 0 : address2.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((clientId == null) ? 0 : clientId.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((createDt == null) ? 0 : createDt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((updateDt == null) ? 0 : updateDt.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ContactInfo other = (ContactInfo) obj;
        if (address1 == null) {
            if (other.address1 != null)
                return false;
        } else if (!address1.equals(other.address1))
            return false;
        if (address2 == null) {
            if (other.address2 != null)
                return false;
        } else if (!address2.equals(other.address2))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (clientId == null) {
            if (other.clientId != null)
                return false;
        } else if (!clientId.equals(other.clientId))
            return false;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (createDt == null) {
            if (other.createDt != null)
                return false;
        } else if (!createDt.equals(other.createDt))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (updateDt == null) {
            if (other.updateDt != null)
                return false;
        } else if (!updateDt.equals(other.updateDt))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "ContactInfo [id=" + id + ", clientId=" + clientId + ", address1=" + address1 + ", address2=" + address2
                        + ", city=" + city + ", country=" + country + ", code=" + code + ", phone=" + phone
                        + ", createDt=" + createDt + ", updateDt=" + updateDt + "]";
    }
    
    
}
