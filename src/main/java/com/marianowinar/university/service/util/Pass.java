package com.marianowinar.university.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Pass {
	
	public static <Autenticación> void main(String ...args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //El String que mandamos al metodo encode es el password que queremos encriptar.
        System.out.println(bCryptPasswordEncoder.encode("AB123JC"));
    }

}