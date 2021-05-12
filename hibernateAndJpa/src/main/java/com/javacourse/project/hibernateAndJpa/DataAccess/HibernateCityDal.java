package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.Entities.City;

@Repository //Ben gelecekte Hibernate değil de başka birşey tercih edersem eğer Repository
			//yazısını alıyorum bu paketin altında başka bir teknolojiyi temsil eden class'a yazıyorum.
public class HibernateCityDal implements ICityDal{
		
		//Şimdi burada değişen durumlara uyum sağlamak önemli gelecekte farklı bir orm
		//kullanmam dahilinde ben yine bu paktetin altında o farklı orm'i
		//oluşturacağım ve o farklı orm tıpkı buradaki ICityDal'ı implemente 
		//edecek.
		
		//Şimdi biz hibernate'de bir sessionfactory ve bir session açıyorduk
		//Şimdi bu noktada bir standart devreye giriyor bu standart'ın ismi jpa
		//JPA ilk başta bir veri erişim tekniği orm gibi ortaya çıkmış fakat daha 
		//sonra java'da bir standart haline geliyor yani hibernate'de spring'de 
		//standart olarak bu jpa'yı implemente ediyor. Bunu implemente etmesinin
		//şöyle bir avantajı var ben hızlı bir şekilde hangi implemantasyonu istiyorsam
		//ona geçebiliyorum. İstersem hibernate'e istersem diğerine geçebiliyorum
		
		//Hibernate projesinde oluşturduğum sessionfactory ve session nesnesi otomatik olarak
		//jpa'yı kullanarak injection ile oluşacak. Yani şuan işler bizim için daha kolay.
		//Session transaction ayar dosyası vs. bunlarla uğraşmama gerek kalmadı.
		
		//Şimdi benim bir hibernate session nesnesine ihtiyacım var. Bu durumda bunu 
		//benim için implemente edecek bir ortam oluşturmam gerek. 
		//Bunuda constructor ve EntityManager objesi oluşturarak yapacağım.
		//Şimdi biz burada sessionfactory vs. otomatik enjekte edeceğiz ama bunu
		//nasıl yapacağız constructor üzerinde kullandığımız annotation ile.
	
	private EntityManager entityManager;
	
	@Autowired
	public HibernateCityDal(EntityManager entityManager) {
			
			this.entityManager = entityManager;
		}
	
	//Şimdi Autowired annotation'ı spring framework'den geliyor. Demekki
	//spring framework'de bize bu entity manager'ın implementasyonu nedir
	//diyerek gerekli olan injection'ı yapacak. Şu an için biz hibernate kullanıyoruz
	//hibernate injection gerçekleştirecek otomatik olarak bizim için.
	
	//Bu noktada bir constructor injection yaptık ve Autowired ile spring otomatik olarak
	//entityManager'ın karşılığının ne olduğunu gerekli paketlere bakarak anla dedik
	//bizde ki paketlerde zaten hibernate var , hibernate'e bakarak bu işlemi bizim için
	//gerçekleştirecek. 

	//Bu class EntityManager class'ı jpa'dan gelen session'nımıza karşılık gelen class'dır.
	//jpa->java persistance api anlamına geliyor bu arada
	
	@Override
	@Transactional
	public List<City> getAll() {
		//Şimdi burada benim şehirleri döndürmem lazım
		//Bu noktada entityManager'ı kullanacağız.
		
		Session session = entityManager.unwrap(Session.class);
		//Burada bana bir tane session nesnesi ver diyorum.
		//Session.class'ı seçerken hibernate olanı seçmem lazım 
		//Sonuçta bana hibernate session'ı lazım. Başka kütüphanelerde
		//var bununla ilgili karışmasın.
		
		//Jpa burada bizim için hibernate ile ilgili olan bütün enjeksiyonları 
		//gerçekleştiriyor. 
		
		//Buradan bize bir session nesnesi dönüyor.
		//Şimdi session açma kapama için falan vs. unit of work tasarım deseni
		//hibernate ile arka planda çalışıyordu bunun içinde bir annotation var 
		//bu arada Transactional. Transactional bu operasyonun başında ve sonunda
		//bizim için bir transaction açıyor. Bu olay aop olarak geçiyor.
		//AOP -> aspect oriented programming
		//benim kodum build olduğunda bu metodun önüne ve arkasına transaction yani
		//session açma ve kapama kodlarını otomatik olarak koyuyor.  Sadece hibernate
		//ile çalışırken ben yazıyordum açma ve kapama kodlarını burada gerek kalmadı.
		//Transaction işleri spring framework ile daha kolay.
		
		//Artık session'ı oluşturduk. Bir query'e ihtiyacımız var
		
		//Dönmesi gereken şey List of Cities objesi yani List<City> türünde bir nesne 
		//Dolayısıyla bir liste oluşturdum atama işlemini gerçekleştirmek ve query sonucunda
		//verileri almak adına 
		
		List<City> cities = session.createQuery("from City",City.class).getResultList();
		//2. parametreyi hangi tipe map edeceğini belirtmek amacıyla yazdık.
		//Benim şu an üzerinde çalışmak istediğim veritabanı objem City class'ı.
		return cities;
		
		//Hibernate jpa kullanarak hibernate'i çok hızlı bir şekilde kodlamış olduk.
		//Aynı zamanda transactional ile yani spring ile işleri daha da kolaylaştırdık
		//ilk defa hibernate çalışırken session aç kapa vs. uğraşmadık.
		
		//Kodlar daha kısa hale geldi ve artık getAll operasyonu çağırıldığı zaman bütün şehirleri
		//elde edebileceğiz.
		
		//Bu arada veri erişim katmanı kodlandı sıra business katmanına geldi.
	}

	@Override
	@Transactional
	public void add(City city) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		//Nasıl ki yukarıda getAll metodu için bir session'a ihtiyacım varsa , burada da 
		//bir session'a ihtiyacın var unutma!!!
		
		session.saveOrUpdate(city);
		//daha önce bu save'i hem update hem add hemde delete için kullanmıştık ama
		//gördüğün üzere böyle de bir seçenek varmış saveorupdate gibi
		
		//Şimdi bu saveorupdate'in farkı şu id vermezsek saveorupdate içersinde
		//ekleme yapacak , id verirsek de güncelleme yapacak olayı bu.
		
		//Peki neden tek operasyon yapmıyorsun ???
		//Şimdi eklemesi lazım programcının ama id alanını da görünce kafa
		//karışıyor hemen bir id yazıyor ekleme yapacağına güncelleme yapıyor
		//hata yapıyor vs. vs.
		
		//yukarıda basit bir if kontrolü ile id'nin sıfırdan farklı olup olmadığını
		//kontrol edebilirim. hiç id verilmediyse değer sıfır olur integer'larda
		//default değer 0'dı. bu şekilde tek metodda halledebilirsin.
	}

	@Override
	@Transactional
	public void update(City city) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(city);
	}

	@Override
	@Transactional
	public void delete(City city) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		
		City cityToDelete = session.get(City.class,city.getId());
		
		session.delete(cityToDelete);
		
		//Veritabanında silmek istediğim nesneye ulaşmam lazım. Neyi sileceğini
		//söylemeliyim adam silme işlemini gerçekleştirmeden önce. Bundan dolayı
		//get metoduna birinci parametrede veritabanıma karşılık gelen class'ı veriyorum
		//2. parametrede ise silmek istediğim verinin id'sini veriyorum. Id'ye de City classından
		//id özelliğinin getter'ı sayesinde ulaşıyorum.
		
	}

	@Override
	@Transactional
	public City getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		
		//Şimdi bizim veri tabanında ki ilgili id'deki şehir değerine ulaşmam gerekiyor.
		City city = session.get(City.class, id);
		//City nesnesi ve id değeri verip istediğim city'i id'sinden çekip alıyorum.
		return city;
	}
	
	//Transactional'ı tüm metodlarına koymayı unutma koymadığın metodlar çalışmaz sonra.
	
}
