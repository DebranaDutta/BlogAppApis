package com.springboot.blog.Payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 4, message = "User name must minimum of 4 characters!!")
	private String name;
	@Email(message = "Email id is not valid!!")
	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "It allows numeric values from 0 to 9.\r\n"
			+ "Both uppercase and lowercase letters from a to z are allowed.\r\n" + "Allowed are underscore “_”, hyphen “-“, and dot “.”\r\n"
			+ "Dot isn't allowed at the start and end of the local part.\r\n" + "Consecutive dots aren't allowed.")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must of minimum 3 characters and maximum of 10 characters")
	private String password;
	@NotBlank
	private String about;
}

//MethodArgumentNotValidException