package com.hibernate;


import java.util.List;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		
		//Hibernate sessionlar transactionlar vs. genel açıklamaları bu metodun içerisinde yaptım.
		//Bu metodun altında çeşitli select işlemleri uyguladım.
		select();
		
		//Bu metodun altında hibernate ile insert işlemleri uygulandı.
		//insert();
		
		//Bu metodun altında hibernate ile update işlemleri uygulandı.
		//update();
		
		//Bu metodun altında hibernate ile delete işlemleri uygulandı.
		delete();
		
		//Sonuç olarak bu çalışmamda bütün CRUD işlemlerimi uygulamış oldum.
		//CRUD C-> create/insert, R-> read/select, U-> update, D-> delete
	}
	
	public static void select() {
		
		//Hibernate'de session olayı diye birşey var
		//session olayı şudur biz hibernate'de veritabanına 
		//bir sorgu göndeririz. Sorgu yollarken bir tane session
		//açarız session oturum demek . Session içinde sorgumu 
		//yazıp o sorguyu veritabanına yollamak istiyorum
		//Aslında ben session'ının kendisini yolluyorum veritabanına
		//session'ının içindeki sorguda gidiyor bu şekilde peki neden
		//session yolluyorum ? transaction yönetimi için peki transaction 
		//yönetimi nedir ? ben mesela bir tane insert yaptım diyelim ki
		//arkasından bir tanede update yapıyorum eğer update'de bir sıkıntı olursa
		//önceden yaptığım insertde geri alınsın diye bunu yapıyorum
		//session mantığı bu
						
		//bizim bir session'a ihtiyacımız var peki biz session mimarisini
		//nasıl oluşturuyoruz , bir session fabrikasıyla yapıyoruz bu işi
						
		//Şimdi burası main olduğu için (static) uygulama her çalıştırıldığında 
		//bir kere burası çalışacak. Web uygulamalarında böyle olması lazım bir kere çalışsın
		//SessionFactory'nin olduğu kısım statik olsun bir daha 2.ye 3.ye vs. çalıştırmak
		//istemiyoruz web uygulamalarında.
				
		//Şuan biz Configuration diye bir class oluşturduk. Ayar class'ı ve onu SessionFactory'e atadık
		//Yani SessionFactory Configuration class'ının referansını tutuyor.SessionFactory base durumunda 
		//Configuration class'ı ise implementasyon.
				
		//configure metodu Configuration class'ının metodu. Ben burada ayar dosyamı bu metoda veriyorum.
		//Aslında configure metodu da bir nesne onun da kendi içinde bir metodu var. addAnnotatedClass
		//metodu ile ben bunu hangi veritabanı nesnem için yapıyorsam o veritabanı nesnesini 
		//addAnnotatedClass metoduna veriyorum. Bu nesne benim oluşturduğum City veritabanı nesnesi
		//Son durumda buildSessionFactory ile fabrikamı oluşturuyorum.
				
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
						.buildSessionFactory();
				
		//Sorgu yollayabilmem için fabrikadan oturum almam lazım.
				
		Session session = factory.getCurrentSession();
				
		//Session yolladığımda hata çıkarsa diye hata yakalamak için kod yazmam lazım.
				
		try {
			//addAnnotatedClass() metodu ile transaction'a başladığımı söylüyorum.
		 			
			session.beginTransaction();
					
			//world veritabanında City'e sorgu yolladığım zaman elde ettiğim verileri tutabilmek adına
			//bir adet ArrayList oluşturdum. Daha sonra City tablosuna sorgu yollayabilmek için 
			//createQuery metodundan faydalandım. Bu metodun içerisine hql(hibernate query language) sorguları yazılır.
			//hql sql'e göre biraz daha kolay örneğin sorgu içerisindeki from City sql'de select * from City ifadesine
			//karşılık gelmektedir. createQuery'Den dönen query'i arraylist'e aktaramıyoruz. Bu durumda hibernate ile
			//gelen getResultList()'i kullandık fakat bu durumda dönen ifade bir ArrayList değildir bir List'dir. Bundan
			//dolayı ArrayList kullanmak yerine List kullandık. Eğer ArrayList kullanma şansım olsaydı önceliğim ArrayList
			//olurdu.
					
			//List ArrayList Temel Fark
			//ArrayList, standart Java'da statik bir dizinin konusunu aşar; yani,List oluşturulduktan 
			//sonra dizinin boyutu büyüyemez. ArrayList kullanılarak bir dizi oluşturulduğunda, 
			//gerektiğinde büyüyüp küçültülebilecek bir dinamik dizi oluşturulur. Standart Koleksiyon 
			//sınıfı ArrayList, Liste arayüzünü genişletir. List'e göre kullanım olarak daha avantajlıdır.
					
			//Şimdi aşağıda sehir isimlerinin içerisinde kar geçen şehirleri  filtrelemeye çalıştım.
			//%kar% önünde arkasında ne olduğunun bir anlamı yok içerisinde kar geçen şehirleri sadece bana 
			//getir demek.
			
			//%kar önünde ne olduğunun önemi yok sonrasında kar olsun. kar% sonrasında ne olduğunun önemi yok 
			//önünde kar olsun demek. Yani %kar kar ile biten şehir isimlerini bana getir demek. kar% ise isimleri
			//kar ile başlayanları getir demek oluyor bu durumda. %kar%, %kar, kar% 3'ü farklı şeyler bunların. 
					
			//City c yazarak sorgunun devamında sürekli City yazmak yerine c yazıyorum artık c City yerine
			//geçiyor sorgunun devamında.
					
					
					
			List<City> cities = session.createQuery("from City c where c.name like '%kar%'").getResultList();
					
			//Döngü ile query sonucu elde ettiğim listenin içerisindeki şehirlerin isimlerine bakıyorum.
					
					
					
					
			//Bu arada başka sorgularda yazılabilir örneğin 
					
			//"from City c where c.countryCode='TUR' AND c.district='Ankara'"
			//ile country code'u 'TUR' olan Ankara bölgesinde ki şehirleri filtreleyebilirim.
					
			//"from City c order by c.name desc"
			//Bu sorgu ile verileri veritabanından çekerken  sıralama yapmak istiyorum
			//Bunu orderby ile yapıyoruz. Bunu yapmmamızın sebebi şu bir e ticaret sitesi düşün mesela
			//Fiyatların artandan azalana doğru gitmesi isteniliyor veya Alfabetik sırada olması isteniyor
			//ürünlerin vs. vs.
			//order by default olarak asc gelir yazsak da yazmasak da asc->ascending / yükselen
			//dec-> descending / alçalan -metinsel ifadeler z'den a'ya gider sıralama
					
			//"select c.countryCode from City c GROUP BY c.countryCode"
			//datalarımı belli bir grup ifadesine göre gruplamak istersem group by ifadesinden 
			//faydalanıyorum. select c.countryCode ile hangi kolonu çekmek istediğimi söylüyorum.
			//group by c.countryCode ile grupluyorum. Tek başına bir kolon çektiğim için artık listem
			//City nesnesi değil bir String oluyor tek kolonla uğraşıyorum o kolonun değerleri de String
			//Dikkat yani artık tüm tabloyu çekmiyorsam eğer tek bir kolon ile uğraşıyorsam o kolona
			//ait değerlerin türü ile bir liste oluşturmalıyım . Burada ne kadar countryCode çeşidi varsa
			//alıyorum yani TUR bir tane FRA bir tane USA bir tane. Zaten gorup bu kullanmamım da amacı bu
			//countryCodeları grupladım aslında .
					
			for (City city : cities) {
						
				System.out.println(city.getName());
			}
					
			//session.getTransaction().commit() ile gerçekleştirdiğim sorguların veritabanına işlenmesini sağlıyorum
					
			session.getTransaction().commit();
			}
			finally {
			//Yapmam gereken tüm işleri bittiği zaman veritabanı üzerinde çeşitli sorgular vs. fabrikayı kapatmam
			//gerek bu işlemi factory.close() ile yapıyorum.
					
			factory.close();
			}
					
			//hata yakalarken catch bloğunun olmadığına özellikle dikkat edilsin çünkü ortaya bir hata çıktığı zaman
			//bizim hata yakalamamıza gerek yok çünkü hibernate bizim için hatayı otomatik yakalıyor. Bundan dolayı
			//bu yapıda catch bloğunu kurduğumuzda daha kodlar derlenmeden hata alırız. Hibernate otomatik olarak 
			//hataları yakaladığı için, try bloğunun içersinde bir hata ortaya çıkarsa diye catch bloğu yazmamıza 
			//gerek yok.
		
	}
	
	public static void insert() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
			
		try {
	
			session.beginTransaction();
				
			 City city = new City(); 
			 
			 city.setName("Düzce 15"); city.setCountryCode("TUR");
			 city.setDistrict("Karadeniz"); city.setPopulation(112000);
			 
			//Bunları daha önceden yaptığımız gibi constructorlar ile de yapabilirdik bu arada
			//Ama bu şekilde daha esnek eğer veritabanında kolonlarım nullable ise yani null
			//olabiliyorlarsa istediğimi veririm istemediğimi vermem.
			
			//Bu session aslında unit of work tasarım desenini arka planda çalıştırıyor.
			session.save(city);
			
			//Session'a city'i kaydediyorum.
			//Ben burada 2. bir save oluşturablirim yani başka bir şehir nesnesini burada ilk şehirimi 
			//ekledikten sonra ekleyebilirim ama 2. save'imde hata alırsam şayet ilk
			//save'imde çalışmayacak çünkü bu bir transaction'da benim tüm işlemlerimde hatasız çalışmam şart
			
			//Gerçek hayat örneği kardeşime para yolladım başarılı ama kardeşimin bankasında sorun çıktı
			//2. save çalışmadı yani o zaman benim paramında gitmemesi lazım işte hibernate bunu sağlamış
			//oluyor bizim için.
						
			session.getTransaction().commit();
		}
		
		finally {
		
			factory.close();
		}
		
	}
	
	
	public static void update() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
			
		try {
	
			session.beginTransaction();
					
			//Şimdi update işlemini yapalım.
			//Birşeyi güncellemek için o session'daki datanın olması gerekir.
			//Dikkat edilirse insert'deki gibi yeni bir city nesnesi oluşturmuyorum
			//newleme işlemi yapmıyorum veritabanından session.get ile veri çekiyorum. 
			
			City city = session.get(City.class,4089);
			
			//Bunu daha çok güncelleme veya bir ürünün detayına gitmek amacıyla kullanıyoruz.
			//id'den istediğim şehrin bilgilerini çekiyorum ve City türünde city nesnesine atıyorum.
			
			//population'ı güncelledim. Bu işlemi yaparken setterlardan faydalanıyoruz.
			city.setPopulation(200000);
			
			//ama bu session'da yaptığım işleri kaydetmeyi unutmamalıyım
			session.save(city);
			
			//Daha önce yepyeni birşey oluşturduğum için insert kullanmıştım şimdi güncelleme yapıyorum
			//önce bir select yaptım bu select işlemini yukarıda session.get ile city id ile yaptım
			//daha sonra setter ile update ettim.
					
			session.getTransaction().commit();
		}
		
		finally {
		
			factory.close();
		}
		
	}
	public static void delete() {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
			
		try {
	
			session.beginTransaction();
					
			//Şimdi hibernate bana şunu söylüyor nasılki birşeyi güncellerken ne olduğunu belirtmem gerekiyorsa
			//Beni silme işlemi içinde aynı şeyi yapmam lazım neyi sileceğimi belirtmem lazım bu noktada 
			//Güncelle için güncelleme işlemi yapacağım veriyi id'sinden yakalamıştım silme için de yakalamalıyım
			
			City city = session.get(City.class, 4089);
			
			session.delete(city);
				
			session.getTransaction().commit();
		}
		
		finally {
		
			factory.close();
		}
		
	}

}
