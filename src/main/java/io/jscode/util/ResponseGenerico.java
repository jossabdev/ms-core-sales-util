package io.jscode.util;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseGenerico <T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int code = 0;
	private String status = "OK";
	private String message = "Transacción realizada correctamente";
	private T data;
	
}
