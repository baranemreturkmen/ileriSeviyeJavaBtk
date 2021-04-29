package com.springIocAnnotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration ile bu class'ımın konfigürasyon sınıfı oldugunu belirtiyorum.
@Configuration
//@ComponentScan ile asagida database metounda hangi ICustomerDal objesi return edilecekse onu git 
//diger classların arasından bul diyoruz.
@ComponentScan("com.springIocAnnotation")
//@PropertySource ile values.property'nin dosya yolunu elde ediyoruz.
@PropertySource("classpath:values.properties")
public class IocConfig {
	
	
	//Benim database metodum @Bean ifadesiyle xml dosyalarındaki bean'e karsilik geliyor
	//Metod ismi xml dosyalarindaki id'ye karsilik gelir. Baska bir class bu metoda ulasmak
	//context.getBean metodunda parametre olarak database vermeliyim. Bu metod bize bir adet
	//ICustomerDal objesi donecek.
	@Bean
	public ICustomerDal database() {
		return new OracleCustomerDal();
	}
	
	//Benim database metodum @Bean ifadesiyle xml dosyalarındaki bean'e karsilik geliyor
	//Metod ismi xml dosyalarindaki id'ye karsilik gelir. Baska bir class bu metoda ulasmak
	//context.getBean metodunda parametre olarak database vermeliyim. Bu metod bize bir adet
	//ICustomerService objesi donecek ki donmeli de. Mainde kodlari yazarken context.getBean
	//metoduyla ICustomerService objesi donuyorum. Parametre olarak CustomerManger class'ı
	//constructor'ında bir ICustomerDal objesi aldigi icin ve database metodu da bir adet
	//ICustomerDal objesi dondugu icin parametre olarak direkt database metodunu verdim.
	@Bean
	public ICustomerService service() {
		return new CustomerManager(database());
	}
	

}
