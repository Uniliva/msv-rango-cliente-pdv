package br.com.unilito.msvrangoclientepdv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.uniliva.librangobase.mapper.RegistroPedidoMapper;

@EnableScheduling
@SpringBootApplication
public class MsvRangoClientePdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvRangoClientePdvApplication.class, args);
	}
	
	@Bean
	public RegistroPedidoMapper config() {
		return new RegistroPedidoMapper();
	}

}
