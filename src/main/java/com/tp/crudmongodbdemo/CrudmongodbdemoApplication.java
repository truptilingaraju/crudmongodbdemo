package com.tp.crudmongodbdemo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudmongodbdemoApplication {

//	private final EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(CrudmongodbdemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper()
	{
		var modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setCollectionsMergeEnabled(false);
		return modelMapper;
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public  void sendEmail(){
//		senderService.sendEmail("javeedjune7@gmail.com", "CRUDMONGODBDEMO" ,
//				"This is Test Email from CRUDMONGODBDEMO");
//	}

}









