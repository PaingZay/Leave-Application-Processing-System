package sg.nus.iss.team6.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Course class
 *
 * @version $Revision: 1.0
 * @author Suria
 * 
 */

@Entity
@Table(name = "role")
public class Role implements Serializable {
  private static final long serialVersionUID = 6529685098267757690L;
  
  @Id
  @Column(name = "roleid")
  private int id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "description")
  private String description;
  
  @Column(name = "hierarchyValue")
  private int hierarchyValue;
  
  public Role() {}
  
  public Role(int roleId) {
    this.id = roleId;
  }
  
  public Role(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  
  
  public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getRoleId() {
    return id;
  }

  public void setRoleId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Role other = (Role) obj;
    return Objects.equals(id, other.id);
  }

public int getHierarchyValue() {
	return hierarchyValue;
}

public void setHierarchyValue(int hierarchyValue) {
	this.hierarchyValue = hierarchyValue;
}
}