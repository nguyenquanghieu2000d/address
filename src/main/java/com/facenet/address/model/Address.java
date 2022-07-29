package com.facenet.address.model;

import com.facenet.address.dto.Generation;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import java.io.Serializable;

@SqlResultSetMapping(
        name = "AddressMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Generation.class,
                        columns = {
                                @ColumnResult(name = "child_id"),
                                @ColumnResult(name = "child_address_id"),
                                @ColumnResult(name = "child_name"),
                                @ColumnResult(name = "child_type"),
                                @ColumnResult(name = "generation_number", type = Integer.class),
                                @ColumnResult(name = "parent_address_id"),
                                @ColumnResult(name = "parent_name"),
                                @ColumnResult(name = "parent_type"),
                        }
                )
        }
)
@NamedNativeQuery(
        name = "sp_district_recursive",
        query = "CALL district_recursive(?1);",
        resultSetMapping = "AddressMapping"
)
@Entity
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "address_id", length = 100)
    private String addressId;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "parent_id", length = 100)
    private String parentId;

    @Column(name = "root_parent_id", length = 100)
    private String rootParentId;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "child_id", length = 100)
    private String childId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRootParentId() {
        return rootParentId;
    }

    public void setRootParentId(String rootParentId) {
        this.rootParentId = rootParentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

}