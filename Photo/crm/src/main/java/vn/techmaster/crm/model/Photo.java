package vn.techmaster.crm.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity(name = "photo")
@Table(name = "photo")
public class Photo {
  @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String name;

  @Column
  private String url;

  @Column
  private String decription;

  public Photo(String name, String url, String decription) {
    this.name = name;
    this.url = url;
    this.decription = decription;
  }
  

}
