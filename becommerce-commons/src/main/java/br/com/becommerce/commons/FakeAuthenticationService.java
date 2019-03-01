package br.com.becommerce.commons;

import br.com.becommerce.commons.exception.NotAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FakeAuthenticationService {

	private static final String TOKEN = "Gdu2vkyfKrzb0OdZuoPP";


	public boolean validateToken(String token) throws NotAuthorizedException {

		log.info(String.format("m=validateToken, token=%s", token));

		if(!TOKEN.equals(token)){
			throw new NotAuthorizedException();
		}

		return true;
	}
}


