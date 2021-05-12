package com.javacourse.project.hibernateAndJpa.restApi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacourse.project.hibernateAndJpa.Business.ICityService;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@RestController //Bu Class'ın bir api controller yani bir rest controller
//olabilmesi adına bu annotation'a ihtiyaç var.

//Şimdi şöyle bir olay daha var apilerde bir standart vardır .
//domain ismi localhost/api diye başlar kullanıcı da bunun bir api
//olduğunu anlar bu standarttır tüm dünyada bu standarta uyuyormuş
@RequestMapping("/api")

//Bu class'daki bütün herşey api ile başlayacak buradan bunu anlıyoruz.
//Şimdi burada benim bağımlılığım business'a .
public class CityController {
	
	//Şimdi backend noktasında benim bu paketim artık son nokta
	//Artık bu paketlerin üzerine her ne kullanılacaksa eklenip
	//Frontend çalışmalarına başlanabilir.
	
	//Artık ben son noktada burada restful yapımı kuruyorum.
	
	private ICityService cityService;
	
	@Autowired
	public CityController(ICityService cityService) {
		
		this.cityService = cityService;
	}
	
	//Şunu çok iyi biliyorum CityService'i sadece CustomerManager implemente
	//ediyor o da Autowired'da bunu bizim için otomatik bulacaktır.
	//Autowired'da spring kullanıyor aslında bizim için bu işleri spring yapıyor
	//spring otomatik olarak injection'ı buluyor , yani CityService'i CustomerManager'in
	//implemente etmesini. 
	
	@GetMapping("/cities") //Şimdi bu şu anlamda localhos/api/cities dendiğinde şehirlerimi json formatında göreceğim. 
	public List<City> get(){	//api var arada çünkü bu bir isimlendirme standardı diye class'ın üstüne yazdık api'yi.
		return cityService.getAll();
	}
	
	//Ben bu city'i nasıl alıyorum burada ?
	//interfaceler aracılığı ile alıyorum yani zaten işin güzel yanı da bu
	//hiçbir somut class'a bağımlı değilim soyut interfaceler aracılığı ile katmanlar arası
	//bağlantıyı sağlıyorum süreç şimdi şöyle işliyor cityService.getAll ile ICityService 
	//interface'inin referansını tutan özellik sayesinde CityManager'da ki
	//metodu kullanabiliyorum. Çünkü CityManager ICityService'i implemente etmişti.
	//Daha sonra CityManager içerisinde ICityDal interface'inin referansını tutan cityDal özelliğini kullanmıştım 
	//bu özellik ile de HibernateCityDal'daki getAll metoduna ulaşıyorum çünkü HibernateCityDal ICityDal'ı implemente
	//etmişti sonuç olarak aslında herşey birbiri ile bağlantılı. ICityDal'daki imzalarıma da zaten city tablosunu 
	//tutan City class'ını parametre olarak vermiştim bu bağlantılar sayesinde City class'ı bu class'a kadar erişilebilir
	//çeşitli metodlar uygulanabilir hale geldi.
	
	//Şu an tüm bağlantılar tamam artık biz bütün operasyonlarımızı çağırabilecek durumdayız.
	
	//Yukarıda yapılan bu kocaman yorum bu class'ın altında ki diğer tüm metodlar için de geçerli bu arada
	
	@PostMapping("/add")
	public void add(@RequestBody City city) {
		//tabiki burada bir city parametresine ihtiyac var unutma!
		//Yukarıda getAll için gerek yoktu çünkü HibernateCity içerisinde
		//getAll metodunun altında Tüm şehirleri query ile elde ediyorduk
		//yani orada parametreye gerek yoktu query'den istediğimi alıyordum dolayısıyla
		//buradaki getAll'da da parametre yok.
		//ama HibernateCity içersinde add metoduna baktığında orada senden 
		//bir city parametresi istiyor tüm katmanlarda bu metodlar birbirine bağlı
		//olduğuna göre orada bir city parametresi istiyorsa add burada da isteyecektir
		cityService.add(city);
		
		//Bak burada da tıpkı getAll'da olduğu gibi service üzerinden gidiyorum
		//yukarıda getAll için yaptığım büyük yorum burada ki diğer fonksiyonlar için de
		//geçerli
		
		//Bu arada add, update ,delete için vs. tıpkı getAll'da olduğu gibi 
		//GetMapping kullanamazsın çünkü sen GetMapping ile herhangi bir ekleme
		//güncelleme silme yapmazsın sadece data talep edersin bana data ver dersin vs.
		//Herhangi bir değişiklik yapmak istersen eğer bu işler için PostMapping yaparsın
		//Zaten baktığın zaman post yani gönderi durumu söz konusu ismen gayet mantıklı
		
		//post ile ilgili metodların parametrelerinde annotation var bunun sebebi şu
		//RequestBody annotasyonu ile POST veya PUT request'leri handle edilir. 
		//Genelde JSON veya XML formatında bir request'i nesneye dönüştürmek için kullanılır.
	}
	
	@PostMapping("/update")
	public void update(@RequestBody City city) {
		cityService.update(city);
	}
	
	@PostMapping("/delete")
	public void delete(@RequestBody City city) {
		cityService.delete(city);
	}
	
	@GetMapping("/cities/{id}")
	public City getById(@PathVariable int id){
		return cityService.getById(id);
		
		//cities'e parametre yollamam lazım. id için 1 3 5 vs. vs. 
		//ama bu dinamik birşey olduğundan ve ben istediğim herhangi bir şehri çekmek
		//istediğimden /cities/{id} yolu tercih ediyorum ve adı üstünde parametre olarak
		//PathVariable veriyorum benim fonksiyonum da pathvariable sayesinde {id}'yi alıyor 
		//int id'ye koyuyorum.
		
	}
}
