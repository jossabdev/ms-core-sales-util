package io.jscode.util;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(content = Include.NON_NULL)
@Data
public class ResponseListGenerico <T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code = 0;
	private String status = "OK";
	private String message = "Transacción realizada correctamente";
	private List<T> data;
}
