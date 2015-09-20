package callcenter.backend.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Employee implements Serializable {

    private static final long serialVersionUID = -8791680236736408999L;
    @XmlTransient
    private Integer id;
    private String lastName;
    private String firstName;
    private String type;//TODO type to define
    private Date createDt;
    private Date updateDt;
    private Employee supervisor;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public Employee getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDt == null) ? 0 : createDt.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((supervisor == null) ? 0 : supervisor.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Employee other = (Employee) obj;
        if (createDt == null) {
            if (other.createDt != null)
                return false;
        } else if (!createDt.equals(other.createDt))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (supervisor == null) {
            if (other.supervisor != null)
                return false;
        } else if (!supervisor.equals(other.supervisor))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
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
        return "Employee [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", type=" + type
                        + ", createDt=" + createDt + ", updateDt=" + updateDt + ", supervisor=" + supervisor + "]";
    }
    
}
