package kr.or.ddit.mvc.annotation;

import lombok.Getter;
import lombok.ToString;
/**
 * 클라이언트의 요청 정보(커맨드 핸들러 검색을 위한)를 캡슐화
 *
 */
@Getter
public class RequestMappingCondition {
	private String url;
	private RequestMethod method;
	public RequestMappingCondition(String url, RequestMethod method) {
		super();
		this.url = url;
		this.method = method;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestMappingCondition other = (RequestMappingCondition) obj;
		if (method != other.method)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Request[url=%s, method=%s]", url, method);
	}
}
