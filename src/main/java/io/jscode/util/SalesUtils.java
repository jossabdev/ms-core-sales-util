package io.jscode.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class SalesUtils {

	private HttpServletRequest request;

	private final String[] IP_HEADERS = { "X-Real-IP", "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"

	};

	public <T> T mapper(Object request, Class<T> clase) throws ExcepcionGenerica {
		T response;
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		//mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));		
		String jsonStr;

		try {
			jsonStr = mapper.writeValueAsString(request);
			response = mapper.readValue(jsonStr, clase);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ExcepcionGenerica(e);
		}
		return response;
	}

	public <T> List<T> mapperList(Object request, Class<T> clase) throws ExcepcionGenerica {
		List<T> response;
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		//mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));	
		CollectionType listType = mapper
		        .getTypeFactory()
				.constructCollectionType(ArrayList.class, clase);
		try {
			String jsonStr = mapper.writeValueAsString(request);
			response = mapper.readValue(jsonStr, listType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcepcionGenerica(e);
		}
		return response;
	}

	public String getClientIp() {
		String remoteAddr = Constantes.IP_DEFAULT;
		try {
			for (String header : IP_HEADERS) {
				String value = request.getHeader(header);
				if (value == null || value.isEmpty()) {
					continue;
				}
				String[] parts = value.split("\\s*,\\s*");
				return parts[0];
			}
			remoteAddr = request.getRemoteAddr();
		} catch (Exception e) {

		}
		return remoteAddr;
	}
	
	public String convertObjectAsString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.writeValueAsString(object);
	}
}
