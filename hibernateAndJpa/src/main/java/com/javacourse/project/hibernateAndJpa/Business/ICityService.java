package com.javacourse.project.hibernateAndJpa.Business;

import java.util.List;

import com.javacourse.project.hibernateAndJpa.Entities.City;

public interface ICityService {
	//Paketde business yerine service'de kullanılabiliyor
	//ama business daha doğru service çünkü soap rest katmanı
	//için vs'de kullanılabiliyor.
	
	//Burada ICityDal'daki aynı operasyonları kullanıyorsak eğer
	//neden DataAccess paketinde ki ICityDal'ı kullanmıyoruz ???
	
	//Biz şuan temel operasyonları yapıyoruz ama zamanla sırf iş kurallarını
	//kontrol emtek için gereken metotlar gerekebilir veya şuan ICityDal ile
	//aynı olan metodlarımız farklı parametrelere ihtyaç duyabilir vs.
	
	//bu imzalar zamanla değişebilir veya farklı iş katmanına özel imzalar metodlar 
	//eklenebilir bu yüzden birbirinden ayırıyoruz.
	List<City> getAll();
	
	void add(City city);
	void update(City city);
	void delete(City city);
	City getById(int id);
}
