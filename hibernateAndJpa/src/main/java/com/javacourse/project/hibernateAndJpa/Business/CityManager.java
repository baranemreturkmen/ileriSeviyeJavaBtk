package com.javacourse.project.hibernateAndJpa.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.DataAccess.ICityDal;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@Service //Diyorum ki katmanlar karışmasın iş katmanı burası
		 //Spring framework'de bulunan Service anotasyonu sayesinde
		 //Burasının iş katmanı olduğunu söyleyebiliyorum.
public class CityManager implements ICityService{
	
	
	//Ben DataAccess katmanında veri tabanı işleri için 
	//HibernateCityDal diye bir class oluşturmuştum 
	//ve bu class ICityDal'ı implemente ediyordu aslında
	//benzer yapı burada da geçerli ICityService'i oluşturdum
	//ve bu interface'in metodlarını dolduracak olan iş katmanı 
	//sınıfım CityManager. İş kodlarım bu class'da . Manager iş kodu
	//anlamında genelde kullanılır. 
	
	//ÇOK ÖNEMLİ
	//Şimdi burası business katmanı ve business katmanı data access
	//katmanı ile iletişim içerisinde olmalı. Şimdi biz bu iletişimi
	//bağımlılık yaşamamak adına hiçbir zaman somut classlar ile yapmayacağız
	//bunun yerine data access katmaında soyut olarak bekleyen ICityDal
	//interface'imden bir field oluşturup aynen yoluma devam edeceğim
	
	//CityManager'da verilen ICityDal nesnesinin çözümlenmesi gerekiyor
	//aynı şeyi Autowired ile hibernate tarafında data access tarafında yapmıştık
	//entityManager için entity katmanından geliyordu o da.
	
	//Autowired ile şunu diyorum otomatik olarak git bak ICityDal'a uygun birşey varsa
	//onu ver demek aslında. Şu an sadece hibernate olduğu için direk onu bize veriyor 
	//olacak.
	
	private ICityDal cityDal;
	
	@Autowired
	public CityManager(ICityDal cityDal) {

		this.cityDal = cityDal;
	}

	@Override
	@Transactional
	public List<City> getAll() {
		// TODO Auto-generated method stub
		
		//Data Access'De vardı bu metod zaten neden bir daha yapıyorum
		//Data Access'de data access için yapıyordu . Ama business'da
		//business'a özel farklı işlemler de yapılabilir.
		
		//Transactional'ı buradada kullanıyorum. Başka bir katmandayım
		//ama constructor injection sayesinde cityDal üzerinden sessionlara
		//erişiyorum bu yüzden bu katmandaki sessionlara eriştiğim metodlarda
		//transactional anotasyonu şart.
		
		return this.cityDal.getAll();
		
		//Dikkat edilirse şuan HibernateCity class'ına bağımlı değilim
		//Şu an sadece interface'i verdim.
		
		//ICityDal interface'ini bizim için Autowired, hibernate sadece
		//olduğu için bize hibernate üzeriden çözümleme yapacak.
	}

	@Override
	@Transactional
	public void add(City city) {
		// TODO Auto-generated method stub
		//Sanki hep aynı şeyi yapıyoruz burası ara işlemden başka birşey değilmiş
		//gibi görünüyor ama öyle değil burada business ile ilgili ekstra şeyler de
		//olabilir. Aynı durum diğer metodlar içinde geçerli...
		//Burada business ile alakalı çeşitli kodlar da olabilir.
		//Buralar iş gereksinimlerine göre yazılı business katmanındayım.
		//Müşterinin taleplerine göre şekillenir burası.
		this.cityDal.add(city);
	}

	@Override
	@Transactional
	public void update(City city) {
		// TODO Auto-generated method stub
		this.cityDal.update(city);
	}

	@Override
	@Transactional
	public void delete(City city) {
		// TODO Auto-generated method stub
		this.cityDal.delete(city);
	}

	@Override
	@Transactional
	public City getById(int id) {
		// TODO Auto-generated method stub
		return this.cityDal.getById(id);
	}
	
	
	
	

}
