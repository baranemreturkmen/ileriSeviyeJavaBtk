package springIoc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	public static void main(String[] args) {
		
		
		//ClassPathXmlApplicationContext ile applicationContext.xml dosyama ulasiyorum ve context degiskeni uzerine atiyorum
		//applicationContext.xml ile ilgili islemler bu degisken uzerinden gerceklestirilecek.
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//ICustomerService tipinde bir sınıf olusturdum ve bu sinifa applicationContext.xml'de kullandigim ICustomerService tipinde ki
		//class'i atadim. context.getBean metodu ile applicationContext.xml dosyasindaki beanlerime ulasiyorum. service paremetresi
		//applicationContext.xml dosyasinda hangi bean'e gidecegimi soyluyor.ICustomerService.class parametresi ise bu metod sonucu 
		//donmesi gereken class'in tipinin ICustomerService biciminde olmasi gerektigini soyluyor.Yani ben applicationContext.xml
		//dosyasinda service id'sine sahip bean'de ICustomerService biciminde bir class kullanacagimi soylersem ve burada farkli
		//tipte bir class donecegini soylersem mesela ICustomerDal.class gibi baska bir interface girersem buraya kodlar derlenmeden
		//hata verecektir.
	    ICustomerService customerService = context.getBean("service",ICustomerService.class);
		
	    //CustomerManager ve benzeri classlar bu add metodu sayesinde CustomerDal tipinde classların veri tabani islemleri gerceklestiren
	    //metoduna buradaki add metodu sayesinde ulasacaktir bir nevi bu metod kodlarimin Main'den veri tabani islemlerinin yapildigi yere 
	    //dallanmasini sagliyor.
		customerService.add();
		
	}

}
