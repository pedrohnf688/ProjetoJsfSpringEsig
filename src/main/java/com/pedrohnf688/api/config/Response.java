package com.pedrohnf688.api.config;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private List<String> erros;

	public Response() {
	}

	public T getData() {
		//testes
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getErros() {
		if (this.erros == null) {
			this.erros = new ArrayList<String>();
		}

		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

}
