package com.hibernate;

import javax.persistence.*;

//@Entity ile Şuan bu city class'ı bir entity'dir yani veritabanı nesnesidir diyorum.
@Entity
//@Table(name="city") ifadesi ile Madem bu bir veritabanı nesnesi bu hangi tabloya karşılık geliyor
//City tablosuna karşılık geliyor diyorum.
@Table(name="city")
public class City {
	
	//Yukarıda tabloyu belirledik şimdi kolonları da belirlemek gerekiyor.
	
	//@Id parametresi veri tabanında id'ye karşılık gelen özellik için kullanılır.
	//@Column(name="ID") ifadesi ile de kolonları belirliyorum. @Id parametresi 
	//Id kolonunun olduğu özelliğe ait sadece. @Column(name="ID") parametrelerini
	//vermezsem otomatik olarak özellik adları MySql'de tabloda ki kolonlara karşılık
	//gelecektir. Eğer özelliklerin isimleri ile tabloda ki kolon isimleri birebir
	//aynı ise @Column(name="ID") parametresi kolon isimleri girmeye gerek yok.
	//@Column(name="ID") kullanmazsam özellik ismiyle veritabanında arar.
	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="countryCode")
	private String countryCode;
	
	@Column(name="district")
	private String district;
	
	@Column(name="population")
	private int population;
	
	//Ozelliklere ait getter ve setterlar olusturdum. setterlar araciligi ile update ve insert islemleri 
	//yapacagiz. constructor ile de yapilabilirdi bu arada ama kullanıcı hangi kolonu güncelleyecek
	//update edecek vs. setterla daha esnek olur diye dusundum. Yani bazı kolonları kullanıcı tabloda null bırakabilir
	//değer girmez vs. Constructorda bu esnekliği sağlayabilmek adına bir sürü constructor oluşturmak gerekirdi 
	//setterlar varken böyle birşey ile uğraşmak çok mantıksız olurdu.
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}

}
