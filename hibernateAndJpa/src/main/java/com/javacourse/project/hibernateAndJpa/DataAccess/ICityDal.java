package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;
//import com.javacourse.project.hibernateAndJpa.Entities;
//Entities eyvallah ama Entities altında ne ?
import com.javacourse.project.hibernateAndJpa.Entities.City;

public interface ICityDal {
	
	//Şimdi ben bu interface'imin içerisinde gerekli olan operasyonları yazacağım. 
	
	//Şehirleri listelemek istiyorum
	
	List<City> getAll();
	//Tüm şehirleri listeleyecek City türünde bir List dönecek bir metod tasarladım
	
	void add(City city);
	void update(City city);
	void delete(City city);
	//klasik add update delete için imzalar oluşturuldu.
	
	City getById(int id);
	//donüş değeri bir City objesi
	//parametre olarak id veriyorum ki istediğim City objesini çekebileyim.
	
	//Bu arada City'i burada göremez çünkü City başka bir pakette dolayısıyla
	//City class'ını import etmek gerek.
	
	//Temel metotlarımın olduğu interface'im hazır şuan
	
	//Durumu özetlemek gerekirse benim 
	//DataAccess katmanım hazır 
	//package com.javacourse.project.hibernateAndJpa.Entities; altında
	//bir City sınıfı oluşturdum ve onu MySql'e bağladım aslında,
	//ben java özelinde veri tabanlarıma bağlayacağım nesneleri içeren 
	//bir Entities katmanı oluşturdum.
	//Şimdi bu interface ile ben Business katmanı ile DataAccess katmanım 
	//arasında bir köprü oluşturacağım. Bu interface'imin DataAccess katmanında
	//olduğuna dikkat edilsin. Zaten DataAccess dediğim şey adı üstünde veri erişimi
	//demek ben veri erişimini yani veri tabanı nesnesi ile business katmanı arasındaki
	//köprüyü DataAccess katmanı ile sağlayacağım ve DataAccess katmanıda ICityDal
	//interface'inde ki metotlar ile bu bağlantıyı sağlıyor.
	
	
	//Biz şimdi interface'imizi yazdık ama bu interface'i hibernate kodlarını kullanarak
	//doldurmamız gerekiyor. Şimdi benim hibernate kodlarını kullanacağım class yine bu pakette
	//olacak ve bu Interface'i implemente edecek.
	
	
	
}
