package com.javacourse.project.hibernateAndJpa.Entities;

import javax.persistence.*;

@Entity   //bu City class'nın bir veri tabanınesnesi oldugunu anlatan Entity annotation'a ihtiyacimiz var
@Table(name="city")
public class City {
	
	//Şimdi benim resources diye bir klasörüm var
	//Adı üstünde kaynaklarımızı içeriyor . properties uzantılı bir dosya
	//var, bu dosyada ben connection String'i enjekte etmek için emaili enjekte 
	//etmek için falan vs. bu dosyayı kullanıyorum. Bu dosyayı metinler dosyası 
	//ayarlar dosyası olarak düşünebiliriz. Şu an bu projede emaili enjekte etmiyoruz
	//ama başka projelerde ihtiyaç duyulabilir. mysql için url username ve password var bu dosyada.
	
	//SOLID prensiblerine uymak için projenin yapısal görünümünü paketlere ayırmakta fayda var
	//Veri tabanı nesnesini entity katmanına koyarız.  
	//Benim world veri tabanından city tablom var bununla çalışacağım dolayısıyla
	//bir city nesnesine ihtiyaç var ve bu city nesnesi de City class'ıdır.
	
	//Şimdi tıpkı daha önceden olduğu gibi ben kolonlarımı burada özellik olarak 
	//belirtiyorum ve bu kolonlara ait getter ve setter'lar oluşturuyorum.
	
	//Şimdi ben bu nesnemi veri tabanı ile bağlamak istiyorum
	//Bizim bu class'a sen bir veritabanı tablosusun dememiz gerekiyordu
	//City class'ımın üzerinde bir annotation daha vermem lazım. Bu durumu
	//gerçekleştirmek adına Table anotasyonunu kullanıyorum.
	
	//Şimdi benim kolonlara da annotation vermem gerekiyor.
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//Bu parametre ile id kolonunun bir identity olduğunu yani otomatik artacağını söylüyorum
	//Şimdi bu oluşturulucak yani üretilecek bir alan peki bunu üretme stratejisi ne olacak ?
	//staragety ile üretme stratejisini belirliyorum. Oracle da bu IDENTITY SEQUENCE olacaktır vs. vs. 
	
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="countrycode")
	private String countryCode;
	
	@Column(name="district")
	private String district;
	
	@Column(name="population")
	private int population;
	
	public City(int id, String name, String countryCode, String district, int population) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}
	
	public City() {
		
	}

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
